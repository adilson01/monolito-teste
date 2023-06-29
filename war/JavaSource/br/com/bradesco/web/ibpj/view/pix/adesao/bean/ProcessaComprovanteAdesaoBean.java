package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.bradesco.web.ib.view.components.app.UIComprovante.dados.Comprovante;
import br.com.bradesco.web.ibpj.service.business.operacao.comprovante.OperacaoComprovanteFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class ProcessaComprovanteAdesaoBean {

	public String processaComprovantePendente() {

		/* Obter a transação a partir da url */
		 HttpServletRequest request = (HttpServletRequest)
		 FacesContext.getCurrentInstance().getExternalContext()
		 .getRequest();
		
		 Transacao transacao = TransacaoFactory.getTransacaoService()
		 .obterTransacao(Long.parseLong(request.getParameter("nutransacao")));

		// implementando

//		Transacao transacao = TransacaoFactory.getTransacaoService().obterTransacao(100741253);

		Comprovante comprovante = (OperacaoComprovanteFactory.getInstance().obterComprovanteTransacao(transacao));

		return NavegacaoUtils.getComprovantePendenteBean().init(comprovante);
	}
}
