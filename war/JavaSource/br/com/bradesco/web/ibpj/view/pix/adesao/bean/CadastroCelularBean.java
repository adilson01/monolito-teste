package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.view.utils.Constantes;

//import com.google.i18n.phonenumbers.AsYouTypeFormatter;
//import com.google.i18n.phonenumbers.PhoneNumberUtil;

//import br.com.bradesco.web.ibpj.pix.service.PixServicesFactory;
//import br.com.bradesco.web.ibpj.pix.service.exception.PixException;
//import br.com.bradesco.web.ibpj.pix.service.paises.IPaisesService;
//import br.com.bradesco.web.ibpj.pix.service.paises.model.response.ObterPaisesResponse;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

 

public class CadastroCelularBean {
	
    
	public CadastroCelularBean() {
	}
	
    private static final String NAV_CADASTRO_CELULAR = "cadastroCelular";
    
    private AdesaoForm form;
    
	private IBspiService bspiService;

	public String init() {
    	this.form.limparChaveIniciarTela();
        this.form.setDdi("55");
        
        return NAV_CADASTRO_CELULAR;
    }

    public String continuar() {
		try {
			this.form.setPosse(this.bspiService.validaPosse(this.form.getConta(),
					 this.form.getEmpresa(),
					 this.form.getChaveEnderecamento(),
					 Constantes.VAZIO,
					 this.form.getDdi(),
					 this.form.getChave(),//mandar a chave como celular
					 Constantes.VAZIO));
			
		} catch (PixApiException e) {
			System.out.println(e.getMessage());
			this.form.setErroEnviarCodValidaPosse(true);
		}
    	
		return NavegacaoUtils.getValidacaoPosseBean().init();
    }
    
    public String recarregarTela() {
        return NAV_CADASTRO_CELULAR;
    }
    
    public String voltar() {
        return NavegacaoUtils.getCadastrarChaveBean().recarregarPagina();
    }
    
    public String trocarPais() {
        return NavegacaoUtils.getTrocarPaisBean().init();
    }

    public AdesaoForm getForm() {
        return form;
    }

    public void setForm(AdesaoForm form) {
        this.form = form;
    }

    public void mascaraCelular() {
		
    	//PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		//String codigoPais = phoneUtil.getRegionCodeForCountryCode(Integer.parseInt(this.form.getDdi()));
		
		//AsYouTypeFormatter formatter = phoneUtil.getAsYouTypeFormatter(codigoPais);
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String celular = request.getParameter("frmCadastroCelular:celular");
		
		for(int i = 0 ; i < celular.length() ; i++) {

			char c = celular.charAt(i);
			//this.form.setChave(formatter.inputDigit(c));
		}
    }

	public IBspiService getBspiService() {
		return bspiService;
	}

	public void setBspiService(IBspiService bspiService) {
		this.bspiService = bspiService;
	}

//	public IPaisesService getiPaisesService() {
//		return iPaisesService;
//	}
//
//
//
//	public void setiPaisesService(IPaisesService iPaisesService) {
//		this.iPaisesService = iPaisesService;
//	}
    
}