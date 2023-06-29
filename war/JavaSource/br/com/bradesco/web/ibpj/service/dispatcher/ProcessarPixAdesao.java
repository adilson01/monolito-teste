package br.com.bradesco.web.ibpj.service.dispatcher;

import com.google.gson.Gson;

import br.com.bradesco.web.ibpj.pix.service.PixServicesFactory;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.DadosCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.CriaChaveUnicaResponse;
import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.pix.service.utils.PixCoreConstantes;
import br.com.bradesco.web.ibpj.pix.service.utils.TipoChave;
import br.com.bradesco.web.ibpj.pix.service.utils.Utils;
import br.com.bradesco.web.ibpj.pix.transacao.util.PixTransacaoUtil;
import br.com.bradesco.web.ibpj.pix.transacao.util.TransacaoConstantes;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.bean.StatusProcessamento;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class ProcessarPixAdesao extends ProcessadorBase implements IAdesaoServicoConstantes {

	@Override
	public StatusProcessamento efetuarProcessamentoAprovacao(Transacao transacao) {

		StatusProcessamento statusProcessamento = new StatusProcessamento();

		CriaChaveUnicaRequest request = new CriaChaveUnicaRequest();

		try {

			DadosCadastroChaveRequest chave = PixTransacaoUtil.recuperarChaveDadosPost(transacao.getDadosPost());
			Conta conta = PixTransacaoUtil.recuperarDadosConta(transacao.getDadosPost());
			super.popularDadosSessao(null, "922551647970807050");
			
			request.setAgencia(conta.getAgencia());
			request.setConta(conta.getNumConta());
			request.setDac(String.valueOf(conta.obterContaDig()));
			request.setTitularidade("0");
			request.setChave(chave);

			CriaChaveUnicaResponse chaveCriada = PixServicesFactory.getInstanceForChaveService().criarChaveUnica(request);

			if (chave.getTipoChave() == TipoChave.EVP) {
				chave.setChave(chaveCriada.getChave());
				transacao.getDadosPost().setVariavel(TransacaoConstantes.CHAVE, Utils.objectToJson(chave));
			}

			// chave cadastrada com sucesso no Bradesco
			transacao.getDadosPost().setVariavel(TransacaoConstantes.STATUS_FINAL_ADESAO,TransacaoConstantes.STATUS_CHAVE_CADASTRADA);

			statusProcessamento.setCodigo(STATUS_TRANSACAO_COM_COMPROVANTE);
			statusProcessamento.setMensagem("Transação realizada com sucesso");
			statusProcessamento.setPossuiComprovante(true);
			statusProcessamento.setPossuiErro(false);

		} catch (PixApiException pix) {
			String codigoErroBacen = pix.getCodigoBacen().trim();

			if (codigoErroBacen != null && codigoErroBacen != "") {
				if (codigoErroBacen.equalsIgnoreCase(PixCoreConstantes.ERRO_CHAVE_DADOS_INVALIDOS)) {
					statusProcessamento.setMensagem("Dados inválidos ao tentar criar nova chave");

				} else if (codigoErroBacen.equalsIgnoreCase(PixCoreConstantes.ERRO_CHAVE_JA_EXISTENTE_MESMO_BANCO_E_DONO)) {
					statusProcessamento.setMensagem("Já existe vínculo para essa chave com o mesmo participante e dono.");
					logManager.info("Chave Pix já está associada a mesma empresa e banco");

				} else if (codigoErroBacen.equalsIgnoreCase(PixCoreConstantes.ERRO_CHAVE_PERTENCE_A_OUTRA_PESSOA)) {
					statusProcessamento.setMensagem("Jé existe vínculo para essa chave com outra pessoa");
					logManager.info("Chave Pix está associada a outra conta");

				} else if (codigoErroBacen.equalsIgnoreCase(PixCoreConstantes.ERRO_CHAVE_PERTENCE_OUTRO_BANCO)) {
					statusProcessamento.setMensagem("Já existe vínculo para essa chave com o mesmo dono, mas ela encontra-se associada a outro participante");
					logManager.info("Chave Pix está associada a outro PSP");
					
				} else if (codigoErroBacen.equalsIgnoreCase("EntryLimitExceeded")) {
					statusProcessamento.setMensagem("Numero de vínculos associados a conta excedeu o limite máximo");
					logManager.info("Numero de vínculos associados a conta excedeu o limite máximo");
					
				} else {
					statusProcessamento.setMensagem("Erro acionando serviço para criar chave.");
				}
				
			} else {
				if (pix.getCode().equals("500")) {
					statusProcessamento.setMensagem("Erro acionando serviço para criar chave.");
				} else {
					statusProcessamento.setMensagem(pix.getLocalizedMessage());
				}
			}
			logManager.error(this, pix);
		
			statusProcessamento.setPossuiErro(true);
			
			statusProcessamento.setCodigo(STATUS_TRANSACAO_NAO_EFETUADA);
			
			statusProcessamento.setPossuiComprovante(true);

			

		} catch (Exception var) {
	
			statusProcessamento.setPossuiErro(true);
			statusProcessamento.setCodigo(STATUS_SEM_PREVISAO_TERMINO);
			statusProcessamento.setMensagem("Erro acionando serviço para criar chave.");
			statusProcessamento.setPossuiComprovante(true);

			logManager.error(this, var);
		}

		return statusProcessamento;
	}

	@Override
	public StatusProcessamento efetuarProcessamentoPendente(Transacao transacao) {
		return super.efetuarProcessamentoPendente(transacao);
	}

	@Override
	public StatusProcessamento efetuarProcessamentoConsulta(Transacao transacao) {

		return super.efetuarProcessamentoConsulta(transacao);
	}

	@Override
	public StatusProcessamento efetuarProcessamentoRecusa(Transacao transacao) {

		return super.efetuarProcessamentoRecusa(transacao);
	}

}
