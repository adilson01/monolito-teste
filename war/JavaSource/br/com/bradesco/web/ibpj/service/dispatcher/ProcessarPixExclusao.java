package br.com.bradesco.web.ibpj.service.dispatcher;

import com.google.gson.Gson;

import br.com.bradesco.web.ibpj.pix.service.PixServicesFactory;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ExluirChaveEnderecamentoRequest;
import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.service.business.gerenciamento.IGerenciamentoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.bean.StatusProcessamento;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;

public class ProcessarPixExclusao extends ProcessadorBase implements IGerenciamentoServicoConstantes {

	@Override
	public StatusProcessamento efetuarProcessamentoAprovacao(Transacao transacao) {
		StatusProcessamento statusProcessamento = new StatusProcessamento();
		try {
			transacao = TransacaoFactory.getTransacaoService().obterTransacao(transacao.getNumeroTransacao());
			DadosPost dadosPost = TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao());

			Gson gson = new Gson();

			ExluirChaveEnderecamentoRequest dadosExclusao = gson.fromJson(dadosPost.getVariavel(DP_DADOS_EXCLUSAO),
					ExluirChaveEnderecamentoRequest.class);

			PixServicesFactory.getInstanceForChaveService().excluirChave(dadosExclusao);

			statusProcessamento.setPossuiErro(false);
			statusProcessamento.setCodigo(STATUS_TRANSACAO_COM_COMPROVANTE);
			statusProcessamento.setMensagem("Chave excluída com sucesso!");
			statusProcessamento.setPossuiComprovante(true);

		} catch (PixApiException var4) {

			statusProcessamento.setPossuiErro(true);
			statusProcessamento.setCodigo(STATUS_TRANSACAO_NAO_EFETUADA);
			statusProcessamento.setMensagem("Erro acionando serviço para exclusão chave.");
			statusProcessamento.setPossuiComprovante(true);
			
			logManager.error(this, var4);

		} catch (Exception var5) {

			statusProcessamento.setPossuiErro(true);
			statusProcessamento.setCodigo(STATUS_TRANSACAO_NAO_EFETUADA);
			statusProcessamento.setMensagem("Erro acionando serviço para exclusão chave.");
			statusProcessamento.setPossuiComprovante(true);
			
			logManager.error(this, var5);
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
