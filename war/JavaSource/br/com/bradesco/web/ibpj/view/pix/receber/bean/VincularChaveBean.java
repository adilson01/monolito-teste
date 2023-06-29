package br.com.bradesco.web.ibpj.view.pix.receber.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.web.ibpj.service.model.ReceberChaveModel;

public class VincularChaveBean {	
	
	private List<ReceberChaveModel> listachaves;	
	
	
	private static final String NAV_VINCULAR_CHAVE = "vincularChave";

	public String init() {	
		
		return NAV_VINCULAR_CHAVE;
	}

	public List<ReceberChaveModel> getListachaves() {
		
		listachaves = new ArrayList<ReceberChaveModel>();
		
		listachaves.add(new ReceberChaveModel("01", "maria@gmail.com"));	
		listachaves.add(new ReceberChaveModel("01", "71991344556"));	
		listachaves.add(new ReceberChaveModel("01", "joaopereira@gmail.com"));	
		listachaves.add(new ReceberChaveModel("01", "aparecida"));			
		
		return listachaves;
	}
	


	public void setListachaves(List<ReceberChaveModel> listachaves) {
		this.listachaves = listachaves;
	}

	
}
