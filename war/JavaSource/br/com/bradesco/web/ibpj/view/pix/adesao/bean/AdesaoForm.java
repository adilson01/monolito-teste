package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.DadosCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.paises.model.response.PaisResponse;
import br.com.bradesco.web.ibpj.pix.service.posse.model.response.ValidaPosseResponse;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Cnpj;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Empresa;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoContaEnum;

public class AdesaoForm implements Serializable {

	private static final long serialVersionUID = 3435562079818628429L;

	public AdesaoForm() {
		 super();
		this.adesaoModel = new CriaChaveUnicaRequest();
		this.empresa = new Empresa();
    }
	
	private CriaChaveUnicaRequest adesaoModel;
	
	private String chaveSelecionada = null;
	
	private String ddi = "";
	
	private List<PaisResponse> listaPaises;
	
	private long cpssoaJurid;
	  
	private int ctpoNegoc;
  
	private long nseqNegoc;
  
	private long club;
	
	private Conta conta;
	
	private Cnpj cnpjEmpresa;
	
	private Empresa empresa;
	
	private boolean btnContAgenciaConta;
	
    /** Armazena o codigo do lote da transação. */
    private long numeroTransacao;
    
    private boolean ocultarTipoChaveCNPJ = false;
    
    private String chave = "";
    
    private boolean termoUsoLido = false;
    
    private TipoChaveEnum chaveEnderecamento;
    
    private boolean erroServico = false;
    
    private boolean contaNaoPodeOperarPix = false;
    
    private TipoContaEnum tipoConta;
    
    private ValidaPosseResponse posse;
    
    private String senhaPosse = "";
    
    private String mensagemErro = "";
    
    private boolean erroEnviarCodValidaPosse = false;
    
    private boolean codigoErradoPosse = false;
    
    private String descricaoSucesso = "";
    
    private boolean erroProcuradorSemPermissaoAcesso = false;
    
    private Boolean botaoConfirmarDadosAdesao = true;
    
    private boolean numeroMaximoChavesExcedido = false;
    
    private boolean erroIntencao = false;
    
	public void inciarCamposChave() {
		this.erroServico = false;
		this.erroProcuradorSemPermissaoAcesso = false;
		this.adesaoModel.setChave(new DadosCadastroChaveRequest());
		this.listaPaises = new ArrayList<PaisResponse>();
		this.numeroTransacao = 0;
		this.chave = "";
		this.ddi = "";
		this.setTermoUsoLido(false);
		this.setPosse(new ValidaPosseResponse());
		this.senhaPosse = "";
		this.mensagemErro = "";
		this.contaNaoPodeOperarPix = false;
		this.erroEnviarCodValidaPosse = false;
		this.codigoErradoPosse = false;
		this.descricaoSucesso = "";
		this.botaoConfirmarDadosAdesao = true;
		this.erroIntencao = false;
	}
	
	public String getDdiFormatado() {
		if (!this.getDdi().equals("")) {
			return "+" + this.getDdi();
		} else {
			return "";
		}
	}
	
	public void limparChaveIniciarTela() {
		this.chave = "";
		this.ddi = "";
		this.numeroTransacao = 0;
		this.senhaPosse = "";
		this.erroProcuradorSemPermissaoAcesso = false;
		this.erroEnviarCodValidaPosse = false;
		this.codigoErradoPosse = false;
		this.descricaoSucesso = "";
		this.botaoConfirmarDadosAdesao = true;
		this.erroIntencao = false;
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

	public String getChaveSelecionada() {
		return chaveSelecionada;
	}

	public void setChaveSelecionada(String chaveSelecionada) {
		this.chaveSelecionada = chaveSelecionada;
	}

	public CriaChaveUnicaRequest getAdesaoModel() {
		return adesaoModel;
	}

	public void setAdesaoModel(CriaChaveUnicaRequest adesaoModel) {
		this.adesaoModel = adesaoModel;
	}

	public List<PaisResponse> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<PaisResponse> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public long getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(long numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public boolean isBtnContAgenciaConta() {
		return btnContAgenciaConta;
	}

	public void setBtnContAgenciaConta(boolean btnContAgenciaConta) {
		this.btnContAgenciaConta = btnContAgenciaConta;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public boolean isOcultarTipoChaveCNPJ() {
		return ocultarTipoChaveCNPJ;
	}

	public void setOcultarTipoChaveCNPJ(boolean ocultarTipoChaveCNPJ) {
		this.ocultarTipoChaveCNPJ = ocultarTipoChaveCNPJ;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public long getCpssoaJurid() {
		return cpssoaJurid;
	}

	public void setCpssoaJurid(long cpssoaJurid) {
		this.cpssoaJurid = cpssoaJurid;
	}

	public int getCtpoNegoc() {
		return ctpoNegoc;
	}

	public void setCtpoNegoc(int ctpoNegoc) {
		this.ctpoNegoc = ctpoNegoc;
	}

	public long getNseqNegoc() {
		return nseqNegoc;
	}

	public void setNseqNegoc(long nseqNegoc) {
		this.nseqNegoc = nseqNegoc;
	}

	public long getClub() {
		return club;
	}

	public void setClub(long club) {
		this.club = club;
	}

	public boolean isTermoUsoLido() {
		return termoUsoLido;
	}

	public void setTermoUsoLido(boolean termoUsoLido) {
		this.termoUsoLido = termoUsoLido;
	}

	public TipoChaveEnum getChaveEnderecamento() {
		return chaveEnderecamento;
	}

	public void setChaveEnderecamento(TipoChaveEnum chaveEnderecamento) {
		this.chaveEnderecamento = chaveEnderecamento;
	}

	public boolean isErroServico() {
		return erroServico;
	}

	public void setErroServico(boolean erroServico) {
		this.erroServico = erroServico;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public ValidaPosseResponse getPosse() {
		return posse;
	}

	public void setPosse(ValidaPosseResponse posse) {
		this.posse = posse;
	}

	public String getSenhaPosse() {
		return senhaPosse;
	}

	public void setSenhaPosse(String senhaPosse) {
		this.senhaPosse = senhaPosse;
	}

	public boolean isErroProcuradorSemPermissaoAcesso() {
		return erroProcuradorSemPermissaoAcesso;
	}

	public void setErroProcuradorSemPermissaoAcesso(boolean erroProcuradorSemPermissaoAcesso) {
		this.erroProcuradorSemPermissaoAcesso = erroProcuradorSemPermissaoAcesso;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public boolean isContaNaoPodeOperarPix() {
		return contaNaoPodeOperarPix;
	}

	public void setContaNaoPodeOperarPix(boolean contaNaoPodeOperarPix) {
		this.contaNaoPodeOperarPix = contaNaoPodeOperarPix;
	}

	public boolean isErroEnviarCodValidaPosse() {
		return erroEnviarCodValidaPosse;
	}

	public void setErroEnviarCodValidaPosse(boolean erroEnviarCodValidaPosse) {
		this.erroEnviarCodValidaPosse = erroEnviarCodValidaPosse;
	}

	public boolean isCodigoErradoPosse() {
		return codigoErradoPosse;
	}

	public void setCodigoErradoPosse(boolean codigoErradoPosse) {
		this.codigoErradoPosse = codigoErradoPosse;
	}

	public String getDescricaoSucesso() {
		return descricaoSucesso;
	}

	public void setDescricaoSucesso(String descricaoSucesso) {
		this.descricaoSucesso = descricaoSucesso;
	}

	public Boolean getBotaoConfirmarDadosAdesao() {
		return botaoConfirmarDadosAdesao;
	}

	public void setBotaoConfirmarDadosAdesao(Boolean botaoConfirmarDadosAdesao) {
		this.botaoConfirmarDadosAdesao = botaoConfirmarDadosAdesao;
	}

	public boolean getNumeroMaximoChavesExcedido() {
		return numeroMaximoChavesExcedido;
	}

	public void setNumeroMaximoChavesExcedido(boolean numeroMaximoChavesExcedido) {
		this.numeroMaximoChavesExcedido = numeroMaximoChavesExcedido;
	}

	public boolean isErroIntencao() {
		return erroIntencao;
	}

	public void setErroIntencao(boolean erroIntencao) {
		this.erroIntencao = erroIntencao;
	}

}
