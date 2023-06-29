package br.com.bradesco.web.ibpj.service.model;

public class ComprovanteTransferenciaModel {

	private String tipoChave;

	private String idTransacao;
	private String valor;
	private String dataDaOperacao;
	private String cpfCnpj;

	// adicional celular
	private String horaDaOperacao;
	private String numCelular;

	// adicional Agencia e Conta
	private String agenciaDoDestinatario;
	private String contaDoDestinatarioSemDigito;

	public String getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(String tipoChave) {
		this.tipoChave = tipoChave;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataDaOperacao() {
		return dataDaOperacao;
	}

	public void setDataDaOperacao(String dataDaOperacao) {
		this.dataDaOperacao = dataDaOperacao;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getHoraDaOperacao() {
		return horaDaOperacao;
	}

	public void setHoraDaOperacao(String horaDaOperacao) {
		this.horaDaOperacao = horaDaOperacao;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getAgenciaDoDestinatario() {
		return agenciaDoDestinatario;
	}

	public void setAgenciaDoDestinatario(String agenciaDoDestinatario) {
		this.agenciaDoDestinatario = agenciaDoDestinatario;
	}

	public String getContaDoDestinatarioSemDigito() {
		return contaDoDestinatarioSemDigito;
	}

	public void setContaDoDestinatarioSemDigito(String contaDoDestinatarioSemDigito) {
		this.contaDoDestinatarioSemDigito = contaDoDestinatarioSemDigito;
	}

}
