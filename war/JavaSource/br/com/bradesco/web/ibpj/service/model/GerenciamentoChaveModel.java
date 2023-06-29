package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.SituacaoChaveEnum;

public class GerenciamentoChaveModel implements Serializable {

	public GerenciamentoChaveModel() {
		super();
	}
	
	public GerenciamentoChaveModel(int tipoChave, String chave, int nordChaveApldo, String iapldoAdsaoSpi,
			int idCorrPoup, int statusChave, String idChavePrinc, String hinclReg, int seqChave, int agencia, long conta, String digConta) {
		super();
		this.tipoChave = tipoChave;
		this.chave = chave;
		this.nordChaveApldo = nordChaveApldo;
		this.iapldoAdsaoSpi = iapldoAdsaoSpi;
		this.idCorrPoup = idCorrPoup;
		this.statusChave = statusChave;
		this.idChavePrinc = idChavePrinc;
		this.hinclReg = hinclReg;
		this.seqChave = seqChave;
		this.agencia = agencia;
		this.conta = conta;
		this.digConta = digConta;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309858718703854935L;

	
	private int tipoChave;
	
	private String chave;
	
	private int nordChaveApldo;
	
	private String iapldoAdsaoSpi;
	
	private int idCorrPoup;
	
	private int statusChave;
	
	private String idChavePrinc;
	
	private String hinclReg;
	
	private int seqChave;
	
	private int agencia;
	
	private long conta;
	
	private String digConta;

	public int getTipoChave() {
		return tipoChave;
	}
	
	public String getDescricaoStatus() {
		return SituacaoChaveEnum.getByCodigo(this.getStatusChave()).getDescricao();
	}
	
	public String getDescricaoTipoChave() {
		if (this.tipoChave == 1) {
			return "E-mail";
		} else if (this.tipoChave == 2){
			return "Celular";
		} else if (this.tipoChave == 3) {
			return "CNPJ";
		} else if(this.tipoChave == 5){
			return "Chave aleatória";
		} else {
			return "";
		}
	}

	public String getChaveFormatada() {
		if (this.tipoChave == 3) {
			return SiteUtil.formatarCnpj(chave);
		} else if (this.tipoChave == 2) {
			return SiteUtil.formatarCelular(chave);
		} else {
			return chave;
		}
	}
	
	public String getChave() {
		return chave;
	}

	public int getNordChaveApldo() {
		return nordChaveApldo;
	}

	public String getIapldoAdsaoSpi() {
		return iapldoAdsaoSpi;
	}

	public int getIdCorrPoup() {
		return idCorrPoup;
	}

	public int getStatusChave() {
		return statusChave;
	}

	public String getIdChavePrinc() {
		return idChavePrinc;
	}

	public String getHinclReg() {
		return hinclReg;
	}

	public int getSeqChave() {
		return seqChave;
	}

	public int getAgencia() {
		return agencia;
	}

	public long getConta() {
		return conta;
	}

	public String getDigConta() {
		return digConta;
	}
	
}
