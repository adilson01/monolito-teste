package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class TermoDeUsoBean {
	
private static final String NAV_TERMO_DE_USO = "termoDeUso";

	private AdesaoForm form;
	
	public String init() {
		return NAV_TERMO_DE_USO;
	}
	
	public String confirmar() {
		
		return NavegacaoUtils.getConfirmacaoDadosBean().confirmarTermoUso();
		
	}
	
	public String cancelar() {
		return NavegacaoUtils.getConfirmacaoDadosBean().recarregarPagina();
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}

}
