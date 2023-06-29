package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import javax.faces.event.ActionEvent;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.DadosCadastroChaveRequest;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;

public class CadastrarChaveBean {

	private static final String NAV_CADASTRAR_CHAVE = "cadastrarChave";
	
	private boolean botaoContinuar = true;
	
	private AdesaoForm form;

	
	public String init() {
		this.form.getAdesaoModel().setChave(new DadosCadastroChaveRequest());
		this.getForm().setChaveSelecionada(Constantes.VAZIO);
		return NAV_CADASTRAR_CHAVE;
	}
	
	public String recarregarPagina() {
		this.setBotaoContinuar((this.getForm().getChaveSelecionada() != null) ? false : true);
		return NAV_CADASTRAR_CHAVE;
	}

	public void selecionarNumero(ActionEvent e) {
		ativaBotaoContinuar();
	}

	public void ativaBotaoContinuar() {
		this.setBotaoContinuar(false);
	}
	
	public String continuar() {
		
		int codChave = SiteUtil.tratarParametroInteger(this.getForm().getChaveSelecionada());
		
		this.form.setChaveEnderecamento(TipoChaveEnum.getByCodigo(codChave));
		
		switch (codChave) {
			case 1:
				return NavegacaoUtils.getCadastroCelularBean().init();
			case 2:
				return NavegacaoUtils.getCadastroEmailBean().init();
			case 3:
				this.form.inciarCamposChave();
				this.form.setChave(this.form.getEmpresa().getCnpj().getCnpjFormatado());
				return NavegacaoUtils.getConfirmacaoDadosBean().init();
			case 4:
				return NavegacaoUtils.getCadastroChaveOcultaBean().init();
		}
		return Constantes.VAZIO;
		
	}
	
	public String voltar() {
		return NavegacaoUtils.getSelecaoAgenciaContaBean().recarregarPagina();
	}

	public boolean isBotaoContinuar() {
		return botaoContinuar;
	}

	public void setBotaoContinuar(boolean botaoContinuar) {
		this.botaoContinuar = botaoContinuar;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}
	
	

}
