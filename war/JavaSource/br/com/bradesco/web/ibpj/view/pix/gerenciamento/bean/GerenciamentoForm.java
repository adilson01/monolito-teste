package br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Cnpj;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Empresa;
import br.com.bradesco.web.ibpj.service.model.GerenciamentoChaveModel;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoContaEnum;

public class GerenciamentoForm implements Serializable {

	private static final long serialVersionUID = 3435562079818628429L;

	public GerenciamentoForm() {
		 super();
		this.empresa = new Empresa();
    }
	
	private Conta conta;
	
	private Cnpj cnpjEmpresa;
	
	private Empresa empresa;
	
	private List<GerenciamentoChaveModel> listaChaves;
	
	private GerenciamentoChaveModel chaveSelecionada;
	
	private TipoChaveEnum chave;
	
	private boolean erroServico;
	
	private boolean erroProcuradorSemPermissaoAcesso = false;
	
    /** Armazena o codigo do lote da transação. */
    private long numeroTransacao;
	
    private TipoContaEnum tipoConta;
    
	public void iniciarCampos() {
		this.erroServico = false;
		this.erroProcuradorSemPermissaoAcesso = false;
		this.listaChaves = new ArrayList<GerenciamentoChaveModel>();
		this.chaveSelecionada = new GerenciamentoChaveModel();
		this.tipoConta = null;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Cnpj getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(Cnpj cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public List<GerenciamentoChaveModel> getListaChaves() {
		return listaChaves;
	}

	public void setListaChaves(List<GerenciamentoChaveModel> listaChaves) {
		this.listaChaves = listaChaves;
	}

	public GerenciamentoChaveModel getChaveSelecionada() {
		return chaveSelecionada;
	}

	public void setChaveSelecionada(GerenciamentoChaveModel chaveSelecionada) {
		this.chaveSelecionada = chaveSelecionada;
	}

	public TipoChaveEnum getChave() {
		return chave;
	}

	public void setChave(TipoChaveEnum chave) {
		this.chave = chave;
	}

	public boolean isErroServico() {
		return erroServico;
	}

	public void setErroServico(boolean erroServico) {
		this.erroServico = erroServico;
	}

	public long getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(long numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}
	
	public boolean isErroProcuradorSemPermissaoAcesso() {
		return erroProcuradorSemPermissaoAcesso;
	}

	public void setErroProcuradorSemPermissaoAcesso(boolean erroProcuradorSemPermissaoAcesso) {
		this.erroProcuradorSemPermissaoAcesso = erroProcuradorSemPermissaoAcesso;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}
}
