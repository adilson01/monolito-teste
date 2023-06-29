package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.ActionEvent;

import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class TransferenciaComprovanteBean {

	private String dtOperacao = "13/07/2020";
	private String horarioOperacao = "10h32";
	private String tipoOperacao = "transferência";
	private String cntrl = "000000000000000000";
	private String numDocumento = "0000000";
	private boolean habilitaDadosRecebedor;
	private HtmlPanelGroup dadosEmpresa;
	private HtmlPanelGroup dadosRecebedor;
	private HtmlPanelGroup dadosTransacao;
	private HtmlPanelGroup dadosAutenticacao;
	private static final String NAV_EMISSAO_COMPROVANTE = "transferenciaComprovante";

	public String init(HtmlPanelGroup dadosEmpresa, HtmlPanelGroup dadosRecebedor, HtmlPanelGroup dadosTransacao,
			HtmlPanelGroup dadosAutenticacao,Transacao transacao,boolean habilitaDadosRecebedor) {
		this.dadosEmpresa = dadosEmpresa;
		this.dadosRecebedor = dadosRecebedor;
		this.dadosTransacao = dadosTransacao;
		this.dadosAutenticacao = dadosAutenticacao;
		this.habilitaDadosRecebedor = habilitaDadosRecebedor;
		
		

		return NAV_EMISSAO_COMPROVANTE;
	}


	public String transferido() {
		return NavegacaoUtils.getSelecaoAgenciaContaTransferenciaBean().initTransferencia();

	}
	





	public HtmlPanelGroup getDadosAutenticacao() {
		return dadosAutenticacao;
	}


	public void setDadosAutenticacao(HtmlPanelGroup dadosAutenticacao) {
		this.dadosAutenticacao = dadosAutenticacao;
	}


	public boolean isHabilitaDadosRecebedor() {
		return habilitaDadosRecebedor;
	}

	public void setHabilitaDadosRecebedor(boolean habilitaDadosRecebedor) {
		this.habilitaDadosRecebedor = habilitaDadosRecebedor;
	}

	public HtmlPanelGroup getDadosEmpresa() {
		return dadosEmpresa;
	}

	public void setDadosEmpresa(HtmlPanelGroup dadosEmpresa) {
		this.dadosEmpresa = dadosEmpresa;
	}

	public HtmlPanelGroup getDadosRecebedor() {
		return dadosRecebedor;
	}

	public void setDadosRecebedor(HtmlPanelGroup dadosRecebedor) {
		this.dadosRecebedor = dadosRecebedor;
	}

	public HtmlPanelGroup getDadosTransacao() {
		return dadosTransacao;
	}

	public void setDadosTransacao(HtmlPanelGroup dadosTransacao) {
		this.dadosTransacao = dadosTransacao;
	}

	public String getDtOperacao() {
		return dtOperacao;
	}

	public void setDtOperacao(String dtOperacao) {
		this.dtOperacao = dtOperacao;
	}

	public String getHorarioOperacao() {
		return horarioOperacao;
	}

	public void setHorarioOperacao(String horarioOperacao) {
		this.horarioOperacao = horarioOperacao;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getCntrl() {
		return cntrl;
	}

	public void setCntrl(String cntrl) {
		this.cntrl = cntrl;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

}
