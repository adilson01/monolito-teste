package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import br.com.bradesco.web.ib.view.components.app.UIComprovante.dados.Comprovante;

public class ComprovantePendenteBean {

	Comprovante comprovante;
	private static final String NAV_EMISSAO_COMPROVANTE = "comprovante";

	public String init(Comprovante comprovante) {
		this.comprovante = comprovante;

		return NAV_EMISSAO_COMPROVANTE;
	}

	public Comprovante getComprovante() {
		return comprovante;
	}

	public void setComprovante(Comprovante comprovante) {
		this.comprovante = comprovante;
	}

}
