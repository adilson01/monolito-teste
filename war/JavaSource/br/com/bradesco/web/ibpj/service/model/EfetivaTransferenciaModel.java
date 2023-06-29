package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class EfetivaTransferenciaModel implements Serializable{

	/**
	 * 
	 */

	public EfetivaTransferenciaModel() {
		this.chave = new ChaveModel();
	}
	
	public EfetivaTransferenciaModel(Integer agencia, ChaveModel chave, Long conta, String dac, String titularidade) {
		this.agencia = agencia;
		this.chave = chave;
		this.conta = conta;
		this.dac = dac;
		this.titularidade = titularidade;
	}
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2263175607156303281L;

	private Integer agencia;
	
	private ChaveModel chave;
	
	private Long conta;
	
	private String dac;
	
	private String titularidade;
	
	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public ChaveModel getChave() {
		return chave;
	}

	public void setChave(ChaveModel chave) {
		this.chave = chave;
	}

	public Long getConta() {
		return conta;
	}

	public void setConta(Long conta) {
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

	@Override
	public String toString() {
		return "EfetivaTransferenciaModel {"
				+ "agencia=" + agencia + ","
				+ chave.toString()+","
				+ " conta=" + conta + ","
				+ " dac='" + dac+ "\',"
			   + " titularidade='" + titularidade + "\'}";
	}

	
}
