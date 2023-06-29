package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class SelecionarChaveBean {
	
	private static final String NAV_SELECIONAR_CHAVE = "selecionarChave";
	
	private boolean botaoContinuar = false;
	private boolean exibirCpfCnpj = false;
	private boolean exibirNumCelular = false;
	private boolean exibirEmail = false;
	private boolean exibirAgConta = false;
	private boolean flagErro = false;	
	private String bancoSelecionado;
	private boolean titularidade = false;
	private String chave;
	
	private TransferenciaForm transform;
	
	public String init() {	
		this.transform.inciarCamposChave();
		this.transform.setChaveSelecionada(Constantes.VAZIO);
		this.transform.setDdi("55");
		return NAV_SELECIONAR_CHAVE;
	}
	
	public String recarregarPagina() {
		return NAV_SELECIONAR_CHAVE;
	}
	 
	 public void exibirTitularidade(ActionEvent e) {	 
					 ativarTitularidade();		 
	 }
	 
	 public String bancoSelecionado(){
			return this.getBancoSelecionado();
	 }
	 	
	 public void limparBancoSelecionado() {
		 this.setBancoSelecionado(null);
	 }
	 
	 public String trocarPais() {
		 this.getTransform().setAcessoTransferencia(true);
		 return NavegacaoUtils.getTrocarPaisBean().initTransf();
	 }
	 
	 public String selecionarBanco() {
		 return NavegacaoUtils.getSelecionarBancoBean().initTransf();
	 }
	 
	 public void ativarTitularidade() {
		 this.setTitularidade(true);
	 }
	
	public void radioCpfCnpj() {
		this.setExibirCpfCnpj(true);	
	}
	
	public void radioCelular() {
		this.setExibirNumCelular(true);			
	}
	
	public void radioEmail() {
		this.setExibirEmail(true);		
	}
	
	public void radioAgConta() {
		this.setExibirAgConta(true);	
	}
	
	public void selecionarCelular(ActionEvent e) {
		radioCelular();
	}

	public void selecionarEmail(ActionEvent e) {
		radioEmail();
	}

	public void selecionarCPFCNPJ(ActionEvent e) {
		radioCpfCnpj();
	}
	
	public void selecionarAgConta(ActionEvent e) {
		radioAgConta();
	}
	
	public void habilitarBtncontinuar(ActionEvent e) {
		ativaBotaoContinuar();
	}
	

	public void ativaBotaoContinuar() {
		this.setBotaoContinuar(true);
	}	
	
	
	public String continuar() {

		int codChave = SiteUtil.tratarParametroInteger(this.getTransform().getChaveSelecionada());
			
			switch (codChave) {
			case 1:
				this.getTransform().setTipoChave(Constantes.DESC_CHAVE_CELULAR);
				this.getTransform().getTrasnferenciaModel().getChave().setTipoChave(SiteUtil.tratarParametroInteger(Constantes.COD_CHAVE_CNPJ));
				return NavegacaoUtils.getInserirValorBean().init();
			case 2:
				this.getTransform().setTipoChave(Constantes.DESC_CHAVE_EMAIL);
				this.getTransform().getTrasnferenciaModel().getChave().setTipoChave(SiteUtil.tratarParametroInteger(Constantes.COD_CHAVE_EMAIL));
				return NavegacaoUtils.getInserirValorBean().init();
			case 3:
				this.getTransform().setTipoChave(Constantes.DESC_CHAVE_CNPJ);
				this.getTransform().getTrasnferenciaModel().getChave().setTipoChave(SiteUtil.tratarParametroInteger(Constantes.COD_CHAVE_CNPJ));
				return NavegacaoUtils.getInserirValorBean().init();
			case 4:
				this.getTransform().setTipoChave(Constantes.TRANSFERENCIA_AGENCIA_CONTA);
				this.getTransform().getTrasnferenciaModel().getChave().setTipoChave(SiteUtil.tratarParametroInteger(4));
				return NavegacaoUtils.getTransferenciaAgenciaContaBean().init();
			}
			return Constantes.VAZIO;
			
		}
		

	public String voltar() {
		return NavegacaoUtils.getSelecaoAgenciaContaTransferenciaBean().recarregarPagina();
	}
	
	public boolean isBotaoContinuar() {
		return botaoContinuar;
	}

	public void setBotaoContinuar(boolean botaoContinuar) {
		this.botaoContinuar = botaoContinuar;
	}	

	public boolean isExibirCpfCnpj() {
		return exibirCpfCnpj;
	}

	public void setExibirCpfCnpj(boolean exibirCpfCnpj) {
		this.exibirCpfCnpj = exibirCpfCnpj;
	}

	public boolean isExibirNumCelular() {
		return exibirNumCelular;
	}

	public void setExibirNumCelular(boolean exibirNumCelular) {
		this.exibirNumCelular = exibirNumCelular;
	}

	public boolean isExibirEmail() {
		return exibirEmail;
	}

	public void setExibirEmail(boolean exibirEmail) {
		this.exibirEmail = exibirEmail;
	}

	public boolean isExibirAgConta() {
		return exibirAgConta;
	}

	public void setExibirAgConta(boolean exibirAgConta) {
		this.exibirAgConta = exibirAgConta;
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

	public boolean isFlagErro() {
		return flagErro;
	}

	public void setFlagErro(boolean flagErro) {
		this.flagErro = flagErro;
	}

	
	public boolean isTitularidade() {
		return titularidade;
	}

	public void setTitularidade(boolean titularidade) {
		this.titularidade = titularidade;
	}

	public String getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(String bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}	

}
