package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ddi;
	
	private String nome;

	public Pais(String nome, String ddi) {
		super();
		this.ddi = ddi;
		this.nome = nome;
	}
	
	public Pais() {}

	public String getDdi(){
		return ddi;
	}

	public void setDdi(String ddi){
		this.ddi = ddi;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getPaisFormatado() {
		return getNome() + " (+" + getDdi() + ")";
	}

}
