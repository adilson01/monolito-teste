package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.web.ib.service.business.dadocliente.bean.Cnpj;
import br.com.bradesco.web.ib.service.business.dadocliente.bean.Empresa;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.model.ChaveModel;
import br.com.bradesco.web.ibpj.service.model.EfetivaTransferenciaModel;
import br.com.bradesco.web.ibpj.service.model.HorariosSaldoLimiteModel;
import br.com.bradesco.web.ibpj.service.model.Pais;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class TransferenciaForm implements Serializable {

	private static final long serialVersionUID = 3435562079818628429L;

	public TransferenciaForm() {
		 super();
		this.trasnferenciaModel = new EfetivaTransferenciaModel();
		this.empresa = new Empresa();
    }
	
    /** Armazena o codigo do lote da transação. */
    private long numeroTransacao;
	
	private EfetivaTransferenciaModel trasnferenciaModel;
	
	private String chaveSelecionada = null;
	
	private String cnpjFormat = null;
	
	private String cnpj = null;
	
	private Conta conta;
	
	private String contaFormatadaSelecionada = "";
	
	private Cnpj cnpjEmpresa;
	
	private String tipoChave = "";
	
	private List<Pais> listaPaises;
	
	Empresa empresa;
	
	private boolean exibirCardSaldoDiponivel = false;
	
    private HorariosSaldoLimiteModel horariosSaldoLimiteModel;
    
    private String titularidade = "";
    
    private String chave = "";
    
    private String chaveEmail = "";
    
    private String chaveCpfCnpj = "";
    
    private String valor = "";
    
    private String descricaoValor = "";
    
    private String agenciaTransferenciaSemChave = "";
    
    private String contaTransferenciaSemChave = "";
    
    private String tipoContaTransferenciaSemChave = "";
    
    private String cpfCnpjTransferenciaSemChave = "";
    
    private boolean cpfCnpjMesmaTitularidade = false;
    
    private String ddi = "";
    
    private boolean acessoTransferencia = false;
    
    private String bancoSelecionado;
    
    private boolean btnContAgenciaConta;
    
    private boolean erroProcuradorSemPermissaoAcesso = false;
    
    
	public void inciarCamposChave() {
		this.erroProcuradorSemPermissaoAcesso = false;
		this.numeroTransacao = 0;
		this.trasnferenciaModel.setChave(new ChaveModel());
		this.listaPaises = new ArrayList<Pais>();
		this.chave = "";
		this.valor = "";
		this.descricaoValor = "";
		this.chaveEmail = "";
		this.chaveCpfCnpj = "";
		this.agenciaTransferenciaSemChave = "";
		this.contaTransferenciaSemChave = "";
		this.tipoContaTransferenciaSemChave = "";
		this.cpfCnpjTransferenciaSemChave = "";
		this.titularidade = "";
		this.ddi = "";
		this.acessoTransferencia = false;
		this.btnContAgenciaConta = false;
	}
	
	public void iniciarvaloresAgenciaConta() {
		this.agenciaTransferenciaSemChave = "";
		this.contaTransferenciaSemChave = "";
		this.tipoContaTransferenciaSemChave = "";
		this.cpfCnpjTransferenciaSemChave = "";
		this.cpfCnpjMesmaTitularidade = false;
	}
	
	public void iniciarSaldoLimite() {
		this.horariosSaldoLimiteModel = new HorariosSaldoLimiteModel();
		this.contaFormatadaSelecionada = "";
		this.exibirCardSaldoDiponivel = false;
	}
	
	public void ocultarSaldo() {
		this.getHorariosSaldoLimiteModel().setOcultarSaldo(true);
	}
	
	public void mostrarSaldo() {
		this.getHorariosSaldoLimiteModel().setOcultarSaldo(false);
	}
	
	public String getChaveFinal() {
		try {
			int codChave = SiteUtil.tratarParametroInteger(this.getChaveSelecionada());
			switch (codChave) {
			case 1:
				return "+55 " + this.chave;
			case 2:
				return this.chaveEmail;
			case 3:
				return this.chaveCpfCnpj;
			}
			return Constantes.VAZIO;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getCreditarTipoContaPorAgenciaConta() {
		if ("1".equals(this.tipoContaTransferenciaSemChave)) {
			return Constantes.TIPO_CONTA_CORRENT;
		} else {
			return Constantes.TIPO_CONTA_POUP;
		}
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

	public String getCnpjFormat() {
		return cnpjFormat;
	}

	public void setCnpjFormat(String cnpjFormat) {
		this.cnpjFormat = cnpjFormat;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public long getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(long numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public String getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(String tipoChave) {
		this.tipoChave = tipoChave;
	}

	public HorariosSaldoLimiteModel getHorariosSaldoLimiteModel() {
		return horariosSaldoLimiteModel;
	}

	public void setHorariosSaldoLimiteModel(HorariosSaldoLimiteModel horariosSaldoLimiteModel) {
		this.horariosSaldoLimiteModel = horariosSaldoLimiteModel;
	}

	public boolean isExibirCardSaldoDiponivel() {
		return exibirCardSaldoDiponivel;
	}

	public void setExibirCardSaldoDiponivel(boolean exibirCardSaldoDiponivel) {
		this.exibirCardSaldoDiponivel = exibirCardSaldoDiponivel;
	}

	public EfetivaTransferenciaModel getTrasnferenciaModel() {
		return trasnferenciaModel;
	}

	public void setTrasnferenciaModel(EfetivaTransferenciaModel trasnferenciaModel) {
		this.trasnferenciaModel = trasnferenciaModel;
	}

	public String getChaveSelecionada() {
		return chaveSelecionada;
	}

	public void setChaveSelecionada(String chaveSelecionada) {
		this.chaveSelecionada = chaveSelecionada;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public String getContaFormatadaSelecionada() {
		return contaFormatadaSelecionada;
	}

	public void setContaFormatadaSelecionada(String contaFormatadaSelecionada) {
		this.contaFormatadaSelecionada = contaFormatadaSelecionada;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricaoValor() {
		return descricaoValor;
	}

	public void setDescricaoValor(String descricaoValor) {
		this.descricaoValor = descricaoValor;
	}

	public String getChaveEmail() {
		return chaveEmail;
	}

	public void setChaveEmail(String chaveEmail) {
		this.chaveEmail = chaveEmail;
	}

	public String getChaveCpfCnpj() {
		return chaveCpfCnpj;
	}

	public void setChaveCpfCnpj(String chaveCpfCnpj) {
		this.chaveCpfCnpj = chaveCpfCnpj;
	}

	public String getAgenciaTransferenciaSemChave() {
		return agenciaTransferenciaSemChave;
	}

	public void setAgenciaTransferenciaSemChave(String agenciaTransferenciaSemChave) {
		this.agenciaTransferenciaSemChave = agenciaTransferenciaSemChave;
	}

	public String getContaTransferenciaSemChave() {
		return contaTransferenciaSemChave;
	}

	public void setContaTransferenciaSemChave(String contaTransferenciaSemChave) {
		this.contaTransferenciaSemChave = contaTransferenciaSemChave;
	}

	public String getTipoContaTransferenciaSemChave() {
		return tipoContaTransferenciaSemChave;
	}

	public void setTipoContaTransferenciaSemChave(String tipoContaTransferenciaSemChave) {
		this.tipoContaTransferenciaSemChave = tipoContaTransferenciaSemChave;
	}

	public String getCpfCnpjTransferenciaSemChave() {
		return cpfCnpjTransferenciaSemChave;
	}

	public void setCpfCnpjTransferenciaSemChave(String cpfCnpjTransferenciaSemChave) {
		this.cpfCnpjTransferenciaSemChave = cpfCnpjTransferenciaSemChave;
	}

	public String getTitularidade() {
		return titularidade;
	}

	public void setTitularidade(String titularidade) {
		this.titularidade = titularidade;
	}

	public boolean isCpfCnpjMesmaTitularidade() {
		return cpfCnpjMesmaTitularidade;
	}

	public void setCpfCnpjMesmaTitularidade(boolean cpfCnpjMesmaTitularidade) {
		this.cpfCnpjMesmaTitularidade = cpfCnpjMesmaTitularidade;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public boolean isAcessoTransferencia() {
		return acessoTransferencia;
	}

	public void setAcessoTransferencia(boolean acessoTransferencia) {
		this.acessoTransferencia = acessoTransferencia;
	}

	public String getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(String bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public boolean isBtnContAgenciaConta() {
		return btnContAgenciaConta;
	}

	public void setBtnContAgenciaConta(boolean btnContAgenciaConta) {
		this.btnContAgenciaConta = btnContAgenciaConta;
	}
	
	public boolean isErroProcuradorSemPermissaoAcesso() {
		return erroProcuradorSemPermissaoAcesso;
	}

	public void setErroProcuradorSemPermissaoAcesso(boolean erroProcuradorSemPermissaoAcesso) {
		this.erroProcuradorSemPermissaoAcesso = erroProcuradorSemPermissaoAcesso;
	}


}
