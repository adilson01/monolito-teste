package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class TransferenciaAgenciaContaBean {
	
	private static final String NAV_TRANSFERENCIA_AGENCIA_CONTA = "transferenciaAgenciaConta";

	private TransferenciaForm transform;

	public String init() {
		this.transform.iniciarvaloresAgenciaConta();
		
		if ("1".equals(this.transform.getTitularidade())) {
			this.transform.setCpfCnpjMesmaTitularidade(true);
			this.transform.setCpfCnpjTransferenciaSemChave(this.transform.getEmpresa().getCnpj().getCnpjFormatado());
		} else {
			this.transform.setCpfCnpjMesmaTitularidade(false);
			this.transform.setCpfCnpjTransferenciaSemChave("");
		}
		
		return NAV_TRANSFERENCIA_AGENCIA_CONTA;
	}
	
	public String voltar() {
		return NavegacaoUtils.getSelecionarChaveBean().recarregarPagina();
	}
	
	public String continuar() {
		return NavegacaoUtils.getInserirValorBean().init();
	}
	
	public String recarregarPagina() {
		return NAV_TRANSFERENCIA_AGENCIA_CONTA;
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

}
