package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;

public class ValidacaoPosseBean {
	
	private static final String NAV_VALIDACAO_POSSE_CELULAR = "validacaoPosse";
	
	private static final String NAV_VALIDACAO_POSSE_EMAIL = "validacaoPosseEmail";

	private boolean exibirBanner = false;
	
	private boolean lnkReenviar;
	
	private String codigo;	
	
	private AdesaoForm form;
	
	private boolean flagErro = false;
	
	private boolean ico_checked_azul = false;
	
	private IBspiService bspiService;

	public String init() {
		this.form.setSenhaPosse("");
		this.form.setCodigoErradoPosse(false);
		
		if(this.form.getChaveEnderecamento().getCodigo() == TipoChaveEnum.CELULAR.getCodigo() ) {
			return NAV_VALIDACAO_POSSE_CELULAR;
		}else {
			return NAV_VALIDACAO_POSSE_EMAIL;
		}	  
	}
	
	public String voltar() {
		
		if (this.form.getChaveEnderecamento().getCodigo() == TipoChaveEnum.CELULAR.getCodigo()) {
			return NavegacaoUtils.getCadastroCelularBean().recarregarTela();
		} else {
			return NavegacaoUtils.getCadastroEmailBean().recarregarTela();
		}
	}	

	public void reenviarCodigo() {	
		this.form.setCodigoErradoPosse(false);
		this.form.setErroEnviarCodValidaPosse(false);
		this.setExibirBanner(true);	
		
		switch (this.form.getChaveEnderecamento().getCodigo()) {
		
			case 1:
				try {
					this.form.setPosse(this.bspiService.validaPosse(this.form.getConta(),
							 this.form.getEmpresa(),
							 this.form.getChaveEnderecamento(),
							 Constantes.VAZIO,
							 this.form.getDdi(),
							 this.form.getChave(), //mandar a chave celular
							 Constantes.VAZIO));
					
				} catch (PixApiException e) {
					System.out.println(e.getMessage());
					this.form.setErroEnviarCodValidaPosse(true);
					this.setExibirBanner(false);	
				}
				break;
				
			case 2:
				try {
					this.form.setPosse(this.bspiService.validaPosse(this.form.getConta(),
							 this.form.getEmpresa(),
							 this.form.getChaveEnderecamento(),
							 Constantes.VAZIO,
							 Constantes.VAZIO,
							 Constantes.VAZIO, 
			    			 this.form.getChave())); //mandar a chave email
				} catch (PixApiException e) {
					System.out.println(e.getMessage());
					this.form.setErroEnviarCodValidaPosse(true);
					this.setExibirBanner(false);
				}
				break;
		}
		
	}	
	
	public String continuar() {
		this.form.setErroEnviarCodValidaPosse(false);
		this.form.setCodigoErradoPosse(false);
		
		try {
			this.form.setPosse(this.bspiService.validaPosse(this.form.getConta(),
					 this.form.getEmpresa(),
					 this.form.getChaveEnderecamento(),
					 this.form.getSenhaPosse(),
					 Constantes.VAZIO,
					 Constantes.VAZIO,//mandar a chave como celular
					 Constantes.VAZIO));
			
		} catch (PixApiException e) {
			System.out.println(e.getMessage());
			this.form.setCodigoErradoPosse(true);
			return "";
		}
		return NavegacaoUtils.getConfirmacaoDadosBean().init();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public boolean isLnkReenviar() {
		return lnkReenviar;
	}


	public void setLnkReenviar(boolean lnkReenviar) {
		this.lnkReenviar = lnkReenviar;
	}

	public boolean isExibirBanner() {
		return exibirBanner;
	}

	public void setExibirBanner(boolean exibirBanner) {
		this.exibirBanner = exibirBanner;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}


	public boolean isFlagErro() {
		return flagErro;
	}


	public void setFlagErro(boolean flagErro) {
		this.flagErro = flagErro;
	}


	public boolean isIco_checked_azul() {
		return ico_checked_azul;
	}


	public void setIco_checked_azul(boolean ico_checked_azul) {
		this.ico_checked_azul = ico_checked_azul;
	}

	public IBspiService getBspiService() {
		return bspiService;
	}

	public void setBspiService(IBspiService bspiService) {
		this.bspiService = bspiService;
	}

}
