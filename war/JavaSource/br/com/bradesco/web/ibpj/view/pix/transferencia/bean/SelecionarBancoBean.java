package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.bradesco.web.ib.service.business.banco.bean.Banco;
import br.com.bradesco.web.ibpj.service.model.BancoModel;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;


public class SelecionarBancoBean {
	
	private static final String NAV_SELECIONAR_BANCO = "selecionarBanco";
	
	private List<BancoModel> listaBancos;
	
	private TransferenciaForm transform;
	
	private boolean botaoSelecionarBanco = true;
	
	public String init() {		
	   
	   return NAV_SELECIONAR_BANCO;
	}
	
	public String initTransf() {		
		getListaBancos();	  
	   
	   return NAV_SELECIONAR_BANCO;
	}

	public List<BancoModel> getListaBancos() {
		
		 listaBancos = new ArrayList<BancoModel>();
		 
		 listaBancos.add(new BancoModel("01", "104 - Caixa Econômoca Federal"));		 
		 listaBancos.add(new BancoModel("02", "001 - Banco do Brasil S.A"));
		 listaBancos.add(new BancoModel("03", "003 - Banco da Amazônial S.A"));
		 listaBancos.add(new BancoModel("04", "044 - Banco do BVA S.A"));
		 listaBancos.add(new BancoModel("05", "083 - Banco da China Brasil S.A"));
		 listaBancos.add(new BancoModel("06", "237 - Banco do Bradesco S.A"));
		
		return listaBancos;
	}	
	
	public String selecionarBanco() {
		this.transform.getBancoSelecionado();	
			return NavegacaoUtils.getSelecionarChaveBean().recarregarPagina();	
	}
	
	public void ativarBtnSelecionarBanco(ActionEvent e) {
		ativaBotaoSelecionar();
	}
	
	public void ativaBotaoSelecionar() {
		this.setBotaoSelecionarBanco(false);
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

	public void setListaBancos(List<BancoModel> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public boolean isBotaoSelecionarBanco() {
		return botaoSelecionarBanco;
	}

	public void setBotaoSelecionarBanco(boolean botaoSelecionarBanco) {
		this.botaoSelecionarBanco = botaoSelecionarBanco;
	}


}
