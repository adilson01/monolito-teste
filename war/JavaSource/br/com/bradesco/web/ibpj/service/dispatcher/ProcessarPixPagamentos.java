package br.com.bradesco.web.ibpj.service.dispatcher;

import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.bean.StatusProcessamento;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;

public class ProcessarPixPagamentos extends ProcessadorBase {

	@Override
	public StatusProcessamento efetuarProcessamentoAprovacao(Transacao transacao) {

		StatusProcessamento status = new StatusProcessamento();

		try {
			transacao = TransacaoFactory.getTransacaoService().obterTransacao(transacao.getNumeroTransacao());
			DadosPost dadosPost = TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao());
			status.setCodigo(0);
			status.setMensagem("Sucesso");
		} catch (Exception e) {
			status.setCodigo(-1);
		}

		return status;
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
