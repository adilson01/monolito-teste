package br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean;

import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.gerenciamento.IGerenciamentoService;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaSessionBean;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class ConfirmacaoDadosExclusaoBean {

	public static final String NAV_CONFIRMACAO_DADOS_EXCLUSAO = "confirmacaoDadosExclusao";
	
	private static final String NAV_AUTENTICADOR = "autenticadorGerenciamento";		
	private Boolean botaoConfirmar = true;
	private Boolean botaoCancelar = true;
	private GerenciamentoForm gerenciamentoForm;
	private ContaEmpresaSessionBean empresaSessionBean;
	private char digito;
	private IGerenciamentoService gerenciamentoService;
	
	public String init() {
		this.setDigito(this.gerenciamentoForm.getConta().obterContaDig());
		return NAV_CONFIRMACAO_DADOS_EXCLUSAO;
	}
	
	
	public String botaoCancelar() {	
	
		return NavegacaoUtils.getGerenciamentoChavesPixBean().recarregarPagina();
	}
	
	public String botaoConfirmar() {

		Transacao transacao = gerenciamentoService.criarTransacao(this.gerenciamentoForm.getConta(),
				DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso(), this.gerenciamentoForm.getChaveSelecionada());

		this.gerenciamentoForm.setNumeroTransacao(transacao.getNumeroTransacao());
		
		return NAV_AUTENTICADOR;

	}
	
	public String recarregarPagina() {
		return NAV_CONFIRMACAO_DADOS_EXCLUSAO;
	}

	public GerenciamentoForm getGerenciamentoForm() {
		return gerenciamentoForm;
	}

	public void setGerenciamentoForm(GerenciamentoForm gerenciamentoForm) {
		this.gerenciamentoForm = gerenciamentoForm;
	}

	public Boolean getBotaoCancelar() {
		return botaoCancelar;
	}

	public void setBotaoCancelar(Boolean botaoCancelar) {
		this.botaoCancelar = botaoCancelar;
	}

	public Boolean getBotaoConfirmar() {
		return botaoConfirmar;
	}

	public void setBotaoConfirmar(Boolean botaoConfirmar) {
		this.botaoConfirmar = botaoConfirmar;
	}

	public ContaEmpresaSessionBean getEmpresaSessionBean() {
		return empresaSessionBean;
	}

	public void setEmpresaSessionBean(ContaEmpresaSessionBean empresaSessionBean) {
		this.empresaSessionBean = empresaSessionBean;
	}

	public char getDigito() {
		return digito;
	}

	public void setDigito(char digito) {
		this.digito = digito;
	}
	
	public IGerenciamentoService getGerenciamentoService() {
		return gerenciamentoService;
	}


	public void setGerenciamentoService(IGerenciamentoService gerenciamentoService) {
		this.gerenciamentoService = gerenciamentoService;
	}
	
}
