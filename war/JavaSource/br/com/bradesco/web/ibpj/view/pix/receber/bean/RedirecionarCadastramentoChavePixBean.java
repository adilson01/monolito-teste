package br.com.bradesco.web.ibpj.view.pix.receber.bean;

import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class RedirecionarCadastramentoChavePixBean {

	public static final String NAV_REDIRECIONAR_CADASTRAMENTO_CHAVE_PIX = "redirecionarCadastramentoChavePix";


	
	private Boolean botaoConfirmar = true;
	
	public String init() {
		return NAV_REDIRECIONAR_CADASTRAMENTO_CHAVE_PIX;
	}
	
	
	public String botaoConfirmar() {
		
		return "legal";
		
	}
	
	public String cadastrarNovaChavePix() {
		return NavegacaoUtils.getSelecaoAgenciaContaBean().initAdesao();
	}
	


	public Boolean getBotaoConfirmar() {
		return botaoConfirmar;
	}


	public void setBotaoConfirmar(Boolean botaoConfirmar) {
		this.botaoConfirmar = botaoConfirmar;
	}
	
	


}
