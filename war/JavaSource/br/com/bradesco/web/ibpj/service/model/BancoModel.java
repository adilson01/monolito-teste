package br.com.bradesco.web.ibpj.service.model;

public class BancoModel {
	
	private String id;
	
	private String nome;

	public BancoModel(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(String id) {
		this.id = id;
	}

}
