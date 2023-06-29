package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class ReceberChaveModel implements Serializable {
	
	private String id;
	private String chave;

	private static final long serialVersionUID = 5394882447958125078L;

	public ReceberChaveModel() {
		super();
	}
	
	public ReceberChaveModel(String id, String chave) {
		this.setId(id);
		this.setChave(chave);
	}
	
	

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}