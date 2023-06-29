package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
 

public class InserirValorBean {
    
    private static final String NAV_INSERIR_VALOR = "inserirValor";
    private TransferenciaForm transform;

    private boolean flagErro = false;
    
	public String init() {
		this.transform.setValor("");
		this.transform.setDescricaoValor("");
        return NAV_INSERIR_VALOR;
    }

    public String recarregarTela() {
    	return NAV_INSERIR_VALOR;
    }

    public String continuar() {
        
        return NavegacaoUtils.getTransferenciaConfirmacaoDadosBean().init();
    }
    
    public String voltar() {
    	if (Constantes.COD_AG_CONTA.equals(this.getTransform().getChaveSelecionada())) {
    		 return NavegacaoUtils.getTransferenciaAgenciaContaBean().recarregarPagina();
		}
        return NavegacaoUtils.getSelecionarChaveBean().recarregarPagina();
    }

	public boolean isFlagErro() {
		return flagErro;
	}

	public void setFlagErro(boolean flagErro) {
		this.flagErro = flagErro;
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}
}