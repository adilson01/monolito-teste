package br.com.bradesco.web.ibpj.service.model;

public class PagamentoModel {

	private int agencia = 0; 
	private String agenteUsuario = " "; 
	private int banco = 0;
	private String bookAreaMensagem = " ";
	private String bookFluxoExec = " ";
	private int canal = 0;
	private String chave = " ";
	private String codIdTransacao = " ";
	private int codSessao = 0; 
	private long conta = 0;
	private int disp = 0; 
	private String dtfimCarenDispos = " ";
	private String dthoraTransacao = " ";
	private String enderecoIp = " ";
	private int etapa = 0; 
	private int etapaTransacao = 0; 
	private String fluxoExecucao = " ";
	private String formaAcesso = " ";
	private int identCarenDispos = 0; 
	private String identEquipam = " ";
	private String identRegDipos = " ";
	private String identVirtHard = " ";
	private String indDisp = " ";
	private String latitude;
	private String longitude;
	private String modDispositivo = " ";
	private String nomeUsuario = " ";
	private String numProtocoloCafd = "0";
	private String plataforma = " ";
	private int portaIp = 0; 
	private int servico = 0; 
	private int tipoCliente = 0; 
	private int tipoTransacao = 0; 
	private String tpCartao = " ";
	private int tpChave = 0; 
	private String tpConta = " ";
	private long valorOper = 0;
	private int razao = 0;
	private String indDispositivo;
	private String chamarCafd;
	private int tipoDispositivo;
	private String tpoTransacao;
	private int funcao;
	private long cpssoaJuridDeb;
	private int ctpoContrDeb;
	private long nseqContrDeb;
	private long cclub;
	private int ccpfCnpjRemet;
	private int cflialCnpjRemet;
	private int cctrlCpfRemet;
	private int cinscDados;
	private int ctpoCanal;
	private int cispbBacen;
	private int debBanco;
	private int debAgen;
	private long debCta;
	private String debDigito;
	private String debTipo;
	private int ccpfCnpjFavrd;
	private int cflialCnpjFavrd;
	private int cctrlCpfFavrd;
	private long cpssoaContrFavrd;
	private int ctpoContrFavrd;
	private long nseqContrFavrd;
	private int credBanco;
	private int credAgen;
	private long credCta;
	private String credDigito;
	private String credTipo;
	private String operValor;
	private int tipoDoApelido;
	private String apelidoAgConta;
	private String ipssoaFavrd;
	private String ipssoaRemet;
	private String rinfmdCliPgdor;
	private String cidEndToEnd;
	private long inNsu;
	private String cidTrans;
	private int tempoRetenTrans;
	private int razaoContDebto;
	private int razaoContCredto;
	
	public String getIndDispositivo() {
		return indDispositivo;
	}
	public void setIndDispositivo(String indDispositivo) {
		this.indDispositivo = indDispositivo;
	}
	public String getChamarCafd() {
		return chamarCafd;
	}
	public void setChamarCafd(String chamarCafd) {
		this.chamarCafd = chamarCafd;
	}
	public int getTipoDispositivo() {
		return tipoDispositivo;
	}
	public void setTipoDispositivo(int tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}
	public String getTpoTransacao() {
		return tpoTransacao;
	}
	public void setTpoTransacao(String tpoTransacao) {
		this.tpoTransacao = tpoTransacao;
	}
	public int getFuncao() {
		return funcao;
	}
	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}
	public long getCpssoaJuridDeb() {
		return cpssoaJuridDeb;
	}
	public void setCpssoaJuridDeb(long cpssoaJuridDeb) {
		this.cpssoaJuridDeb = cpssoaJuridDeb;
	}
	public int getCtpoContrDeb() {
		return ctpoContrDeb;
	}
	public void setCtpoContrDeb(int ctpoContrDeb) {
		this.ctpoContrDeb = ctpoContrDeb;
	}
	public long getNseqContrDeb() {
		return nseqContrDeb;
	}
	public void setNseqContrDeb(long nseqContrDeb) {
		this.nseqContrDeb = nseqContrDeb;
	}
	public long getCclub() {
		return cclub;
	}
	public void setCclub(long cclub) {
		this.cclub = cclub;
	}
	public int getCcpfCnpjRemet() {
		return ccpfCnpjRemet;
	}
	public void setCcpfCnpjRemet(int ccpfCnpjRemet) {
		this.ccpfCnpjRemet = ccpfCnpjRemet;
	}
	public int getCflialCnpjRemet() {
		return cflialCnpjRemet;
	}
	public void setCflialCnpjRemet(int cflialCnpjRemet) {
		this.cflialCnpjRemet = cflialCnpjRemet;
	}
	public int getCctrlCpfRemet() {
		return cctrlCpfRemet;
	}
	public void setCctrlCpfRemet(int cctrlCpfRemet) {
		this.cctrlCpfRemet = cctrlCpfRemet;
	}
	public int getCinscDados() {
		return cinscDados;
	}
	public void setCinscDados(int cinscDados) {
		this.cinscDados = cinscDados;
	}
	public int getCtpoCanal() {
		return ctpoCanal;
	}
	public void setCtpoCanal(int ctpoCanal) {
		this.ctpoCanal = ctpoCanal;
	}
	public int getCispbBacen() {
		return cispbBacen;
	}
	public void setCispbBacen(int cispbBacen) {
		this.cispbBacen = cispbBacen;
	}
	public int getDebBanco() {
		return debBanco;
	}
	public void setDebBanco(int debBanco) {
		this.debBanco = debBanco;
	}
	public int getDebAgen() {
		return debAgen;
	}
	public void setDebAgen(int debAgen) {
		this.debAgen = debAgen;
	}
	public long getDebCta() {
		return debCta;
	}
	public void setDebCta(long debCta) {
		this.debCta = debCta;
	}
	public String getDebDigito() {
		return debDigito;
	}
	public void setDebDigito(String debDigito) {
		this.debDigito = debDigito;
	}
	public String getDebTipo() {
		return debTipo;
	}
	public void setDebTipo(String debTipo) {
		this.debTipo = debTipo;
	}
	public int getCcpfCnpjFavrd() {
		return ccpfCnpjFavrd;
	}
	public void setCcpfCnpjFavrd(int ccpfCnpjFavrd) {
		this.ccpfCnpjFavrd = ccpfCnpjFavrd;
	}
	public int getCflialCnpjFavrd() {
		return cflialCnpjFavrd;
	}
	public void setCflialCnpjFavrd(int cflialCnpjFavrd) {
		this.cflialCnpjFavrd = cflialCnpjFavrd;
	}
	public int getCctrlCpfFavrd() {
		return cctrlCpfFavrd;
	}
	public void setCctrlCpfFavrd(int cctrlCpfFavrd) {
		this.cctrlCpfFavrd = cctrlCpfFavrd;
	}
	public long getCpssoaContrFavrd() {
		return cpssoaContrFavrd;
	}
	public void setCpssoaContrFavrd(long cpssoaContrFavrd) {
		this.cpssoaContrFavrd = cpssoaContrFavrd;
	}
	public int getCtpoContrFavrd() {
		return ctpoContrFavrd;
	}
	public void setCtpoContrFavrd(int ctpoContrFavrd) {
		this.ctpoContrFavrd = ctpoContrFavrd;
	}
	public long getNseqContrFavrd() {
		return nseqContrFavrd;
	}
	public void setNseqContrFavrd(long nseqContrFavrd) {
		this.nseqContrFavrd = nseqContrFavrd;
	}
	public int getCredBanco() {
		return credBanco;
	}
	public void setCredBanco(int credBanco) {
		this.credBanco = credBanco;
	}
	public int getCredAgen() {
		return credAgen;
	}
	public void setCredAgen(int credAgen) {
		this.credAgen = credAgen;
	}
	public long getCredCta() {
		return credCta;
	}
	public void setCredCta(long credCta) {
		this.credCta = credCta;
	}
	public String getCredDigito() {
		return credDigito;
	}
	public void setCredDigito(String credDigito) {
		this.credDigito = credDigito;
	}
	public String getCredTipo() {
		return credTipo;
	}
	public void setCredTipo(String credTipo) {
		this.credTipo = credTipo;
	}
	public String getOperValor() {
		return operValor;
	}
	public void setOperValor(String operValor) {
		this.operValor = operValor;
	}
	public int getTipoDoApelido() {
		return tipoDoApelido;
	}
	public void setTipoDoApelido(int tipoDoApelido) {
		this.tipoDoApelido = tipoDoApelido;
	}
	public String getApelidoAgConta() {
		return apelidoAgConta;
	}
	public void setApelidoAgConta(String apelidoAgConta) {
		this.apelidoAgConta = apelidoAgConta;
	}
	public String getIpssoaFavrd() {
		return ipssoaFavrd;
	}
	public void setIpssoaFavrd(String ipssoaFavrd) {
		this.ipssoaFavrd = ipssoaFavrd;
	}
	public String getIpssoaRemet() {
		return ipssoaRemet;
	}
	public void setIpssoaRemet(String ipssoaRemet) {
		this.ipssoaRemet = ipssoaRemet;
	}
	public String getRinfmdCliPgdor() {
		return rinfmdCliPgdor;
	}
	public void setRinfmdCliPgdor(String rinfmdCliPgdor) {
		this.rinfmdCliPgdor = rinfmdCliPgdor;
	}
	public String getCidEndToEnd() {
		return cidEndToEnd;
	}
	public void setCidEndToEnd(String cidEndToEnd) {
		this.cidEndToEnd = cidEndToEnd;
	}
	public long getInNsu() {
		return inNsu;
	}
	public void setInNsu(long inNsu) {
		this.inNsu = inNsu;
	}
	public String getCidTrans() {
		return cidTrans;
	}
	public void setCidTrans(String cidTrans) {
		this.cidTrans = cidTrans;
	}
	public int getTempoRetenTrans() {
		return tempoRetenTrans;
	}
	public void setTempoRetenTrans(int tempoRetenTrans) {
		this.tempoRetenTrans = tempoRetenTrans;
	}
	public int getRazaoContDebto() {
		return razaoContDebto;
	}
	public void setRazaoContDebto(int razaoContDebto) {
		this.razaoContDebto = razaoContDebto;
	}
	public int getRazaoContCredto() {
		return razaoContCredto;
	}
	public void setRazaoContCredto(int razaoContCredto) {
		this.razaoContCredto = razaoContCredto;
	}
}
