package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class CadastroEmailBean {

	private static final String NAV_CADASTRO_EMAIL = "cadastroEmail";
	
	private AdesaoForm form;
	
	private IBspiService bspiService;
	
	public String init() {
		this.form.limparChaveIniciarTela();
		return NAV_CADASTRO_EMAIL;
	}

	public String continuar() {
		try {
			this.form.setPosse(this.bspiService.validaPosse(this.form.getConta(),
					 this.form.getEmpresa(),
					 this.form.getChaveEnderecamento(),
					 Constantes.VAZIO,
					 Constantes.VAZIO,
					 Constantes.VAZIO, 
	    			 this.form.getChave())); //mandar a chave como email
			
		} catch (PixApiException e) {
			System.out.println(e.getMessage());
			this.form.setErroEnviarCodValidaPosse(true);
		}
    	
		return NavegacaoUtils.getValidacaoPosseBean().init();
	}
	
	public String voltar() {
		
		return NavegacaoUtils.getCadastrarChaveBean().recarregarPagina();
	}
	
	public String recarregarTela() {
		return NAV_CADASTRO_EMAIL;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}

	public IBspiService getBspiService() {
		return bspiService;
	}

	public void setBspiService(IBspiService bspiService) {
		this.bspiService = bspiService;
	}	
	
}
