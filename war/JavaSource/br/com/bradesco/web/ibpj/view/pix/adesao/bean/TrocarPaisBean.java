package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.bradesco.web.ibpj.pix.service.PixServicesFactory;
import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.pix.service.paises.IPaisesService;
import br.com.bradesco.web.ibpj.pix.service.paises.model.response.ListaDdiResponse;
import br.com.bradesco.web.ibpj.pix.service.paises.model.response.PaisResponse;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.TransferenciaForm;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import java.util.Comparator;

public class TrocarPaisBean {
	
	public TrocarPaisBean(){
		this.iPaisesService = PixServicesFactory.getInstanceForPaisesService();
	}

	private static final String NAV_TROCAR_PAIS = "trocarPais";
	
	private boolean botaoTrocarPais = true;
	
	private AdesaoForm form;
	
	private IPaisesService iPaisesService;
	
	private TransferenciaForm transform;
	
	private String ddi = "";
	
	
	public String init() {
		this.setDdi("");
		this.form.setListaPaises(new ArrayList<PaisResponse>());
	   try {
		   listarPaises();
	   } catch (PixApiException e) {
		   e.printStackTrace();
	   }
	   
	   return NAV_TROCAR_PAIS;
	}
	
	public String initTransf() {
		this.setDdi("");
		this.form.setListaPaises(new ArrayList<PaisResponse>());
	   try {
		   listarPaises();
	   } catch (PixApiException e) {
		   e.printStackTrace();
	   }
	   
	   return NAV_TROCAR_PAIS;
	}

	@SuppressWarnings("unchecked")
	private void listarPaises() {
		//CHAMADA AO MICROSERVIÇO LISTAR PAISES
		   ListaDdiResponse obterListaDdi = PixServicesFactory.getInstanceForPaisesService().obterListaDdi();
		   List<PaisResponse> listpais = new ArrayList<PaisResponse>(obterListaDdi.getPaises());
		   this.form.getListaPaises().addAll(listpais);
		   
			Collections.sort((List)  this.form.getListaPaises(), new Comparator () {
				public int compare(Object arg0, Object arg1) {
					PaisResponse pais1   = (PaisResponse) arg0;
					PaisResponse pais2 = (PaisResponse) arg1;


					return pais1.getNome().compareTo(pais2.getNome());
				}
			});  
	}
	
	public String trocarPais() {
		if (this.transform.isAcessoTransferencia()) {
			this.transform.setDdi(this.getDdi());
			return NavegacaoUtils.getSelecionarChaveBean().recarregarPagina();
		}
		this.form.setDdi(this.getDdi());
		return NavegacaoUtils.getCadastroCelularBean().recarregarTela();
	}
	
	public String cancelar() {
		if (this.transform.isAcessoTransferencia()) {
			return NavegacaoUtils.getSelecionarChaveBean().recarregarPagina();
		}
		return NavegacaoUtils.getCadastroCelularBean().recarregarTela();
	}
	
	public void selecionarPais(ActionEvent e) {
		ativaBotaoContinuar();
	}
	
	public void ativaBotaoContinuar() {
		this.setBotaoTrocarPais(false);
	}
	
	public boolean isBotaoTrocarPais() {
		return botaoTrocarPais;
	}

	public void setBotaoTrocarPais(boolean botaoTrocarPais) {
		this.botaoTrocarPais = botaoTrocarPais;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}
	
	public IPaisesService getiPaisesService() {
		return iPaisesService;
	}

	public void setiPaisesService(IPaisesService iPaisesService) {
		this.iPaisesService = iPaisesService;
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}
}
