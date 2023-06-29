package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class CadastroChaveOcultaBean {

	private static final String NAV_CADASTRO_CHAVE_OCULTA= "cadastroChaveOculta";
	
	private AdesaoForm form;
	
	public String init() {
		this.form.limparChaveIniciarTela();
		return NAV_CADASTRO_CHAVE_OCULTA;
	}

	public String continuar() {
		if (this.form.getChave().trim().equals(Constantes.VAZIO)) {
			this.form.setChave(Constantes.DESC_CHAVE_ALEATORIA);
		}
		
		return NavegacaoUtils.getConfirmacaoDadosBean().init();
	}
	
	public String voltar() {
		return NavegacaoUtils.getCadastrarChaveBean().recarregarPagina();
	}
	
	public String recarregarTela() {
		return NAV_CADASTRO_CHAVE_OCULTA;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}	
	
}
