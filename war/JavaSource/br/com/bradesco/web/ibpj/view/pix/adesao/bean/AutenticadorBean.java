package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.bean.StatusProcessamento;
import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.impl.AutenticadorServiceBase;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;

public class AutenticadorBean extends AutenticadorServiceBase {
	
	@Override
	public StatusProcessamento efetuarProcessamentoAprovacao(Transacao transacao) {
		// TODO Auto-generated method stub
		return super.efetuarProcessamentoAprovacao(transacao);
	}
	
	@Override
	public StatusProcessamento efetuarProcessamentoPendente(Transacao transacao) {
		// TODO Auto-generated method stub
		return super.efetuarProcessamentoPendente(transacao);
	}
	
	@Override
	public StatusProcessamento efetuarProcessamentoRecusa(Transacao transacao) {
		// TODO Auto-generated method stub
		return super.efetuarProcessamentoRecusa(transacao);
	}
	
	@Override
	public StatusProcessamento efetuarProcessamentoConsulta(Transacao transacao) {
		// TODO Auto-generated method stub
		return super.efetuarProcessamentoConsulta(transacao);
	}
}
