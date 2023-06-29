package br.com.bradesco.web.ibpj.service.business.exceptions;

public class ErroModel {

	private Integer codigo;
	private String mensagemAmigavel;
	private String mensagemTecnica;

	public ErroModel() {
	}

	public ErroModel(String mensagemTecnica) {
		this.mensagemTecnica = mensagemTecnica;
	}
	
	public ErroModel(Integer codigo, String mensagemAmigavel, String mensagemTecnica) {
		this.codigo = codigo;
		this.mensagemAmigavel = mensagemAmigavel;
		this.mensagemTecnica = mensagemTecnica;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagemAmigavel() {
		return mensagemAmigavel;
	}

	public void setMensagemAmigavel(String mensagemAmigavel) {
		this.mensagemAmigavel = mensagemAmigavel;
	}

	public String getMensagemTecnica() {
		return mensagemTecnica;
	}

	public void setMensagemTecnica(String mensagemTecnica) {
		this.mensagemTecnica = mensagemTecnica;
	}
}