package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.ib.service.business.midia.bean.Midia;
import br.com.bradesco.web.ib.service.business.suporteautenticacao.bean.DispositivoSeguranca;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.IntencaoCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.IntencaoCadastroChaveResponse;
import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.pix.service.model.DadosCafd;
import br.com.bradesco.web.ibpj.pix.service.utils.PixCoreConstantes;
import br.com.bradesco.web.ibpj.pix.service.utils.TipoChave;
import br.com.bradesco.web.ibpj.pix.transacao.service.IPixTransacao;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoService;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.business.sessao.SessaoFactory;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoContaEnum;

public class ConfirmacaoDadosBean {

	public static final String NAV_CONFIRMACAO_DADOS = "confirmacaoDados";

	private static final String NAV_AUTENTICADOR = "autenticador";

	private Boolean exibirModal = false;
	
	private AdesaoForm form;
	
	private IAdesaoService adesaoService;
	
	private IBspiService bspiService;
	
	private String mensagemErroIntencao;
	
	private IPixTransacao pixTransacaoService;
	
	public String init() {
		this.obterDadosAdesao();
		this.form.setTermoUsoLido(false);
		this.form.setBotaoConfirmarDadosAdesao(true);
		return NAV_CONFIRMACAO_DADOS;
	}

	
	private void obterDadosAdesao() {
		this.form.setErroIntencao(false);
		this.setMensagemErroIntencao("");
		
		this.form.getAdesaoModel().getChave().setChave(this.form.getChave());
		this.form.getAdesaoModel().getChave().setTipoChave(TipoChave.valueOf(this.form.getChaveEnderecamento().name()));
		//this.form.getAdesaoModel().getChave().setChave(this.form.getChave().replaceAll("[^a-zZ-Z1-9 ]", ""));
		this.form.getAdesaoModel().getChave().setApelidoChave("");
		
		if (this.form.getAdesaoModel().getChave().getTipoChave().name().equals(TipoChave.CELULAR.name())) {
			this.form.getAdesaoModel().getChave().setChave(this.form.getDdiFormatado() + this.form.getChave().replace(" ", ""));
			
		} else if(this.form.getAdesaoModel().getChave().getTipoChave().name().equals(TipoChave.EVP.name())) {
			this.form.getAdesaoModel().getChave().setApelidoChave(this.form.getChave());
			this.form.getAdesaoModel().getChave().setChave("");
			
		} else if (this.form.getAdesaoModel().getChave().getTipoChave().name().equals(TipoChave.CNPJ.name())) {
			this.form.getAdesaoModel().getChave().setChave(
					SiteUtil.completarZerosEsquerda(8,SiteUtil.tratarParametroString(this.form.getEmpresa().getCnpj().getNumero()))
					+ SiteUtil.completarZerosEsquerda(4,SiteUtil.tratarParametroString(this.form.getEmpresa().getCnpj().getFilial()))
					+ SiteUtil.completarZerosEsquerda(2,SiteUtil.tratarParametroString(this.form.getEmpresa().getCnpj().getControle())));
		}
		
		//OBTEM OS DADOS DA CONTA
		this.form.getAdesaoModel().setAgencia(this.form.getConta().getAgencia());
		this.form.getAdesaoModel().setConta(SiteUtil.tratarParametroLong(this.form.getConta().getNumConta()));
		this.form.getAdesaoModel().setDac(SiteUtil.tratarParametroString(this.form.getConta().obterContaDig()));
		this.form.getAdesaoModel().setTitularidade(SiteUtil.tratarParametroString(this.form.getConta().getTitularidade()));
		this.form.getAdesaoModel().getChave().setBanco(this.form.getConta().getBanco());
		
		if (this.form.getTipoConta().getCodigo() == TipoContaEnum.CORRENTE.getCodigo()) {
			this.form.getAdesaoModel().getChave().setRazaoConta(SiteUtil.tratarParametroInteger(this.form.getConta().getRazao()));
			this.form.getAdesaoModel().getChave().setIdCorrPoup(PixCoreConstantes.ID_TIPO_CONTA_CORRENTE);
		} else {
			this.form.getAdesaoModel().getChave().setRazaoConta(SiteUtil.tratarParametroInteger(this.form.getConta().getRazao()));
			this.form.getAdesaoModel().getChave().setIdCorrPoup(PixCoreConstantes.ID_TIPO_CONTA_POUPANCA);
		}
		this.form.getAdesaoModel().getChave().setFilCpfCnpj(this.form.getEmpresa().getCnpj().getFilial());
		this.form.getAdesaoModel().getChave().setNumCpfCnpj(this.form.getEmpresa().getCnpj().getNumero());
		this.form.getAdesaoModel().getChave().setCrlCpfCnpj(this.form.getEmpresa().getCnpj().getControle());
		
		this.form.getAdesaoModel().getChave().setNomeReceita(this.form.getEmpresa().getNomeEmpresa());
		this.form.getAdesaoModel().getChave().setRazaoSocial(this.form.getEmpresa().getNomeEmpresa());
		
		//dados negocio da conta
		this.form.getAdesaoModel().getChave().setClub(this.form.getClub());
		this.form.getAdesaoModel().getChave().setCpssoaJurid(this.form.getCpssoaJurid());
		this.form.getAdesaoModel().getChave().setCtpoNegoc(this.form.getCtpoNegoc());
		this.form.getAdesaoModel().getChave().setNseqNegoc(this.form.getNseqNegoc());
		
		//INFORMAÇÕES FIXAS SOBRE SITUAÇÃO DA CHAVE
		this.form.getAdesaoModel().getChave().setIspbBacen(60746948);
		this.form.getAdesaoModel().getChave().setCodigoA("");
		this.form.getAdesaoModel().getChave().setCodigoB("");
		
		DadosCafd  cafd =  new DadosCafd();
		cafd.setCanal(Midia.CODIGOMIDIA_NETEMPRESA);
		cafd.setEnderecoIp(SessaoFactory.getSessaoIbpjService().obterSessao().getEnderecoIp());
		cafd.setPortaIp(SiteUtil.getRequest().getRemotePort());
		cafd.setCodSessao(SessaoFactory.getSessaoIbpjService().obterSessao().getNumSessao());
		cafd.setPlataforma("");
		cafd.setTipoTransacao(Constantes.TP_TRANSACAO_ADESAO);
		cafd.setAgenteUsuario(SiteUtil.getUserAgent());
		cafd.setDtfimCarenDispos("");
		cafd.setDtfimCarenDispos("");
		cafd.setDthoraTransacao(SiteUtil.gerarDataHoraTimeStamp());
		cafd.setFluxoExecucao("");
		cafd.setIdentCarenDispos(0);
		cafd.setIdentCarenDispos(0);
		cafd.setIdentEquipam("");
		cafd.setIdentRegDipos("");
		cafd.setIdentVirtHard("");
		cafd.setLatitude("");
		cafd.setLongdeSinal("");
		cafd.setLongitude("");
		cafd.setModDispositivo("");
		cafd.setNumProtocoloCafd(0);
		
		int codDispositivoSessao = SessaoFactory.getSessaoIbpjService().obterSessao().getCodTipoDispositivo();
		if (codDispositivoSessao == DispositivoSeguranca.TIPO_DISPOSITIVO_BRTOKEN
				|| codDispositivoSessao == DispositivoSeguranca.TIPO_DISPOSITIVO_TOKEN) {
			cafd.setTipoDispositivo(3);// token
		} else if (codDispositivoSessao == DispositivoSeguranca.TIPO_DISPOSITIVO_TOKEN_NO_CELULAR) {
			cafd.setTipoDispositivo(6);// mtoken
		} else {
			cafd.setTipoDispositivo(0);// não possui
		}
		cafd.setNomeUsuario(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getUsuario().getNome());
		
		//TODO: REVER , TESTE BANCO
		if (this.form.getAdesaoModel().getChave().getTipoChave() == TipoChave.EMAIL) {
			cafd.setFormaAcesso("USUARIO");
		} else if (this.form.getAdesaoModel().getChave().getTipoChave() == TipoChave.EVP) {
			cafd.setFormaAcesso("CERTIFICADO");
		} else if (this.form.getAdesaoModel().getChave().getTipoChave() == TipoChave.CELULAR) {
			cafd.setFormaAcesso("ICP");
		}
		
		this.form.getAdesaoModel().getChave().setCafd(cafd);
		
		IntencaoCadastroChaveRequest intencao = new IntencaoCadastroChaveRequest();
		intencao.setAgencia(this.form.getAdesaoModel().getAgencia());
		intencao.setChave(this.form.getAdesaoModel().getChave());
		intencao.setConta(this.form.getAdesaoModel().getConta());
		intencao.setDac(this.form.getAdesaoModel().getDac());
		intencao.setTitularidade(this.form.getAdesaoModel().getTitularidade());
		
		//CHAMADA AO MICRO SERVICO
		IntencaoCadastroChaveResponse intencaoResponse = new IntencaoCadastroChaveResponse();
		try {
			intencaoResponse = this.bspiService.criarIntencaoCadastroChaveUnica(intencao);
		}  catch (PixApiException e) {
			BradescoCommonServiceFactory.getLogManager().info(e, "ERRO AO CRIAR A INTENCAO");
			this.form.setErroIntencao(true);
			this.setMensagemErroIntencao(e.getMessage());
		}
		
		this.form.getAdesaoModel().getChave().setCodigoA(intencaoResponse.getCodigoA());
		this.form.getAdesaoModel().getChave().setCodigoB(intencaoResponse.getCodigoB());
		this.form.getAdesaoModel().getChave().getCafd().setNumProtocoloCafd(intencaoResponse.getCafdProtocolo());
		
	}


	public String termoDeUso(){
		return NavegacaoUtils.getTermoDeUsoBean().init();
	}
	
	public String voltar() {
		int codChave = SiteUtil.tratarParametroInteger(this.getForm().getChaveSelecionada());
		
		switch (codChave) {
		case 1:
			return NavegacaoUtils.getCadastroCelularBean().recarregarTela();
		case 2:
			return NavegacaoUtils.getCadastroEmailBean().recarregarTela();
		case 3:
			return NavegacaoUtils.getCadastrarChaveBean().recarregarPagina();
		case 4:
			return NavegacaoUtils.getCadastroChaveOcultaBean().recarregarTela();
		}
		
		return Constantes.VAZIO;
	}
	
	/**
	 * 32 é o grupo
		1 é o serviço
		2 é o tipo serviço
		
		DsTipoServico é a descrição do serviço
		32 cdGrupo
		1 cdServico
		2 TpServico
	 * @return
	 */
	public String confirmar() {
		Transacao transacao = pixTransacaoService.criarTransacaoAdesao(this.form.getConta(),
				this.form.getAdesaoModel().getChave());
		
		this.form.setNumeroTransacao(transacao.getNumeroTransacao());
		
		return NAV_AUTENTICADOR;
	}
	

	
	public String recarregarPagina() {
		return NAV_CONFIRMACAO_DADOS;
	}
	
	public String confirmarTermoUso() {
		this.form.setTermoUsoLido(true);
		this.form.setBotaoConfirmarDadosAdesao(false);
		return NAV_CONFIRMACAO_DADOS;
	}

	public Boolean getExibirModal() {
		return exibirModal;
	}

	public void setExibirModal(Boolean exibirModal) {
		this.exibirModal = exibirModal;
	}

	private boolean value1;  
   
    public boolean isValue1() {
        return value1;
    }
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}

	public IAdesaoService getAdesaoService() {
		return adesaoService;
	}


	public void setAdesaoService(IAdesaoService adesaoService) {
		this.adesaoService = adesaoService;
	}


	public IBspiService getBspiService() {
		return bspiService;
	}


	public void setBspiService(IBspiService bspiService) {
		this.bspiService = bspiService;
	}


	public String getMensagemErroIntencao() {
		return mensagemErroIntencao;
	}


	public void setMensagemErroIntencao(String mensagemErroIntencao) {
		this.mensagemErroIntencao = mensagemErroIntencao;
	}


	public IPixTransacao getPixTransacaoService() {
		return pixTransacaoService;
	}


	public void setPixTransacaoService(IPixTransacao pixTransacaoService) {
		this.pixTransacaoService = pixTransacaoService;
	}



}
