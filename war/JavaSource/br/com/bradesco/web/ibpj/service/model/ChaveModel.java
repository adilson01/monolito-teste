package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;

public class ChaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String chave = "";
	private int tipoChave = 1;
	private long cpssoaJurid = 1;
	private int ctpoNegoc = 1;
	private long nseqNegoc = 1;
	private long club = 1;
	private int idCorrPoup = 1;
	private int ispbBacen = 1;
	private int razaoConta = 1;
	private int banco = 1;
	private int agencia = 1;
	private long conta = 1;
	private int numCpfCnpj = 1;
	private int filCpfCnpj = 1;
	private int crlCpfCnpj = 1;
	private String nomeReceita = " ";
	private String apelidoChave = " ";
	private int etapa = 1;
	private String acionarCafd = "";
	private int canal = 1;
	private int codSessao = 1;
	private String identVirtHard = " ";
	private int tipoDispositivo = 1;
	private String enderecoIp = " ";
	private int portaIp = 1;
	private int tipoTransacao = 1;
	private int etapaTransacao = 1;
	private int tipoCliente = 1;
	private String agenteUsuario = " ";
	private String dthoraTransacao = " ";
	private String fluxoExecucao = " ";
	private String bookFluxoExec = " ";
	private long numProtocoloCafd = 1;
	private String latitude = " ";
	private String longitude = " ";
	private String modDispositivo = " ";
	private String plataforma =         " "             ;
	private String identRegDipos = " ";
	private String identEquipam = " ";
	private int identCarenDispos = 1;
	private String dtfimCarenDispos = " ";
	private String formaAcesso = " ";
	private String nomeUsuario = " ";
	private String codIdTransacao = " ";
	private String bookAreaMensagem = " ";
	private int status;

	// LOG NEGOCIO
	private String tipoDispSeguranca = " ";
	private String assDigital = " ";
	private String referencia = " ";
	private String modDispSeguranca = " ";
	private String indicadorDispositivo = " ";
	private String carenciaDispositivo = " ";

	// RESPONSE
	private String indSegue = " ";
	private String indChave = " ";
	private String indRest = " ";
	private String indCafd = " ";
	private long cafdProtocolo = 1;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public int getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(int tipoChave) {
		this.tipoChave = tipoChave;
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

	public int getIdCorrPoup() {
		return idCorrPoup;
	}

	public void setIdCorrPoup(int idCorrPoup) {
		this.idCorrPoup = idCorrPoup;
	}

	public int getIspbBacen() {
		return ispbBacen;
	}

	public void setIspbBacen(int ispbBacen) {
		this.ispbBacen = ispbBacen;
	}

	public int getRazaoConta() {
		return razaoConta;
	}

	public void setRazaoConta(int razaoConta) {
		this.razaoConta = razaoConta;
	}

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public long getConta() {
		return conta;
	}

	public void setConta(long conta) {
		this.conta = conta;
	}

	public int getNumCpfCnpj() {
		return numCpfCnpj;
	}

	public void setNumCpfCnpj(int numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

	public int getFilCpfCnpj() {
		return filCpfCnpj;
	}

	public void setFilCpfCnpj(int filCpfCnpj) {
		this.filCpfCnpj = filCpfCnpj;
	}

	public int getCrlCpfCnpj() {
		return crlCpfCnpj;
	}

	public void setCrlCpfCnpj(int crlCpfCnpj) {
		this.crlCpfCnpj = crlCpfCnpj;
	}

	public String getNomeReceita() {
		return nomeReceita;
	}

	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}

	public String getApelidoChave() {
		return apelidoChave;
	}

	public void setApelidoChave(String apelidoChave) {
		this.apelidoChave = apelidoChave;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public String getAcionarCafd() {
		return acionarCafd;
	}

	public void setAcionarCafd(String acionarCafd) {
		this.acionarCafd = acionarCafd;
	}

	public int getCanal() {
		return canal;
	}

	public void setCanal(int canal) {
		this.canal = canal;
	}

	public int getCodSessao() {
		return codSessao;
	}

	public void setCodSessao(int codSessao) {
		this.codSessao = codSessao;
	}

	public String getIdentVirtHard() {
		return identVirtHard;
	}

	public void setIdentVirtHard(String identVirtHard) {
		this.identVirtHard = identVirtHard;
	}

	public int getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(int tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public String getEnderecoIp() {
		return enderecoIp;
	}

	public void setEnderecoIp(String enderecoIp) {
		this.enderecoIp = enderecoIp;
	}

	public int getPortaIp() {
		return portaIp;
	}

	public void setPortaIp(int portaIp) {
		this.portaIp = portaIp;
	}

	public int getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public int getEtapaTransacao() {
		return etapaTransacao;
	}

	public void setEtapaTransacao(int etapaTransacao) {
		this.etapaTransacao = etapaTransacao;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getAgenteUsuario() {
		return agenteUsuario;
	}

	public void setAgenteUsuario(String agenteUsuario) {
		this.agenteUsuario = agenteUsuario;
	}

	public String getDthoraTransacao() {
		return dthoraTransacao;
	}

	public void setDthoraTransacao(String dthoraTransacao) {
		this.dthoraTransacao = dthoraTransacao;
	}

	public String getFluxoExecucao() {
		return fluxoExecucao;
	}

	public void setFluxoExecucao(String fluxoExecucao) {
		this.fluxoExecucao = fluxoExecucao;
	}

	public String getBookFluxoExec() {
		return bookFluxoExec;
	}

	public void setBookFluxoExec(String bookFluxoExec) {
		this.bookFluxoExec = bookFluxoExec;
	}

	public long getNumProtocoloCafd() {
		return numProtocoloCafd;
	}

	public void setNumProtocoloCafd(long numProtocoloCafd) {
		this.numProtocoloCafd = numProtocoloCafd;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getModDispositivo() {
		return modDispositivo;
	}

	public void setModDispositivo(String modDispositivo) {
		this.modDispositivo = modDispositivo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getIdentRegDipos() {
		return identRegDipos;
	}

	public void setIdentRegDipos(String identRegDipos) {
		this.identRegDipos = identRegDipos;
	}

	public String getIdentEquipam() {
		return identEquipam;
	}

	public void setIdentEquipam(String identEquipam) {
		this.identEquipam = identEquipam;
	}

	public int getIdentCarenDispos() {
		return identCarenDispos;
	}

	public void setIdentCarenDispos(int identCarenDispos) {
		this.identCarenDispos = identCarenDispos;
	}

	public String getDtfimCarenDispos() {
		return dtfimCarenDispos;
	}

	public void setDtfimCarenDispos(String dtfimCarenDispos) {
		this.dtfimCarenDispos = dtfimCarenDispos;
	}

	public String getFormaAcesso() {
		return formaAcesso;
	}

	public void setFormaAcesso(String formaAcesso) {
		this.formaAcesso = formaAcesso;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCodIdTransacao() {
		return codIdTransacao;
	}

	public void setCodIdTransacao(String codIdTransacao) {
		this.codIdTransacao = codIdTransacao;
	}

	public String getBookAreaMensagem() {
		return bookAreaMensagem;
	}

	public void setBookAreaMensagem(String bookAreaMensagem) {
		this.bookAreaMensagem = bookAreaMensagem;
	}

	public String getTipoDispSeguranca() {
		return tipoDispSeguranca;
	}

	public void setTipoDispSeguranca(String tipoDispSeguranca) {
		this.tipoDispSeguranca = tipoDispSeguranca;
	}

	public String getAssDigital() {
		return assDigital;
	}

	public void setAssDigital(String assDigital) {
		this.assDigital = assDigital;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getModDispSeguranca() {
		return modDispSeguranca;
	}

	public void setModDispSeguranca(String modDispSeguranca) {
		this.modDispSeguranca = modDispSeguranca;
	}

	public String getIndicadorDispositivo() {
		return indicadorDispositivo;
	}

	public void setIndicadorDispositivo(String indicadorDispositivo) {
		this.indicadorDispositivo = indicadorDispositivo;
	}

	public String getCarenciaDispositivo() {
		return carenciaDispositivo;
	}

	public void setCarenciaDispositivo(String carenciaDispositivo) {
		this.carenciaDispositivo = carenciaDispositivo;
	}

	public String getIndSegue() {
		return indSegue;
	}

	public void setIndSegue(String indSegue) {
		this.indSegue = indSegue;
	}

	public String getIndChave() {
		return indChave;
	}

	public void setIndChave(String indChave) {
		this.indChave = indChave;
	}

	public String getIndRest() {
		return indRest;
	}

	public void setIndRest(String indRest) {
		this.indRest = indRest;
	}

	public String getIndCafd() {
		return indCafd;
	}

	public void setIndCafd(String indCafd) {
		this.indCafd = indCafd;
	}

	public long getCafdProtocolo() {
		return cafdProtocolo;
	}

	public void setCafdProtocolo(long cafdProtocolo) {
		this.cafdProtocolo = cafdProtocolo;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChaveModel {chave=" + chave + ", tipoChave=" + tipoChave + ", cpssoaJurid=" + cpssoaJurid
				+ ", ctpoNegoc=" + ctpoNegoc + ", nseqNegoc=" + nseqNegoc + ", club=" + club + ", idCorrPoup="
				+ idCorrPoup + ", ispbBacen=" + ispbBacen + ", razaoConta=" + razaoConta + ", banco=" + banco
				+ ", agencia=" + agencia + ", conta=" + conta + ", numCpfCnpj=" + numCpfCnpj + ", filCpfCnpj="
				+ filCpfCnpj + ", crlCpfCnpj=" + crlCpfCnpj + ", nomeReceita=" + nomeReceita + ", apelidoChave="
				+ apelidoChave + ", etapa=" + etapa + ", acionarCafd=" + acionarCafd + ", canal=" + canal
				+ ", codSessao=" + codSessao + ", identVirtHard=" + identVirtHard + ", tipoDispositivo="
				+ tipoDispositivo + ", enderecoIp=" + enderecoIp + ", portaIp=" + portaIp + ", tipoTransacao="
				+ tipoTransacao + ", etapaTransacao=" + etapaTransacao + ", tipoCliente=" + tipoCliente
				+ ", agenteUsuario=" + agenteUsuario + ", dthoraTransacao=" + dthoraTransacao + ", fluxoExecucao="
				+ fluxoExecucao + ", bookFluxoExec=" + bookFluxoExec + ", numProtocoloCafd=" + numProtocoloCafd
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", modDispositivo=" + modDispositivo
				+ ", plataforma=" + plataforma + ", identRegDipos=" + identRegDipos + ", identEquipam=" + identEquipam
				+ ", identCarenDispos=" + identCarenDispos + ", dtfimCarenDispos=" + dtfimCarenDispos + ", formaAcesso="
				+ formaAcesso + ", nomeUsuario=" + nomeUsuario + ", codIdTransacao=" + codIdTransacao
				+ ", bookAreaMensagem=" + bookAreaMensagem + ", tipoDispSeguranca=" + tipoDispSeguranca
				+ ", assDigital=" + assDigital + ", referencia=" + referencia + ", modDispSeguranca=" + modDispSeguranca
				+ ", indicadorDispositivo=" + indicadorDispositivo + ", carenciaDispositivo=" + carenciaDispositivo
				+ ", indSegue=" + indSegue + ", indChave=" + indChave + ", indRest=" + indRest + ", indCafd=" + indCafd
				+ ", cafdProtocolo=" + cafdProtocolo + "}";
	}

	

	
	
	
	
}
