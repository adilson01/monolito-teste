package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class HorariosSaldoLimiteModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2576708888181863179L;

	private String saldo;
	
	private String limiteDiario;
	
	private String limiteDisponivel;
	
	private boolean ocultarSaldo = false;
	
	public String getSaldoSemFormatacao() {
		try {
			return this.saldo.replace(".", "").replace(",", ".");
		} catch (Exception e) {
			return this.saldo;
		}
	}
	
	public String getLimiteDiarioFormatacao() {
		try {
			return this.limiteDiario.replace(".", "").replace(",", ".");
		} catch (Exception e) {
			return this.limiteDiario;
		}
	}
	
	public String getLimiteDisponivelSemFormatacao() {
		try {
			return this.limiteDisponivel.replace(".", "").replace(",", ".");
		} catch (Exception e) {
			return this.limiteDisponivel;
		}
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(String limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public String getLimiteDisponivel() {
		return limiteDisponivel;
	}

	public void setLimiteDisponivel(String limiteDisponivel) {
		this.limiteDisponivel = limiteDisponivel;
	}

	public boolean isOcultarSaldo() {
		return ocultarSaldo;
	}

	public void setOcultarSaldo(boolean ocultarSaldo) {
		this.ocultarSaldo = ocultarSaldo;
	}
	
}
