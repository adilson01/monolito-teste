package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class DadosCadastro implements Serializable{

	public DadosCadastro() {
		this.chave = new Chave();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer agencia;
	
	private Chave chave;
	
	private Integer conta;
	
	private String dac;
	
	private String titularidade;
	
	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Chave getChave() {
		return chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public String getDac() {
		return dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	public String getTitularidade() {
		return titularidade;
	}

	public void setTitularidade(String titularidade) {
		this.titularidade = titularidade;
	}

}
