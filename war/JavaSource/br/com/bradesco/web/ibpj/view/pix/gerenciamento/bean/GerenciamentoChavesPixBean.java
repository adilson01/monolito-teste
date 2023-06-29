package br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.aq.view.util.Numeros;
import br.com.bradesco.web.ib.service.business.midia.bean.Midia;
import br.com.bradesco.web.ibpj.bean.CodigoServicoOperacao;
import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ConsultaListaChavesBradescoRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.ConsultaListaChavesBradescoResponse;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ContratoConta;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ValidaContaResponse;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.empresa.EmpresaFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.PerfilFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.bean.Perfil;
import br.com.bradesco.web.ibpj.service.model.GerenciamentoChaveModel;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaBean;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaSessionBean;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoContaEnum;

public class GerenciamentoChavesPixBean {
	
	public GerenciamentoChavesPixBean() {
		 this.contaBean = ContaEmpresaBean.getInstance();
		 this.empresaSessionBean = ContaEmpresaSessionBean.getInstance();
	}

	private static final String NAV_GERENCIAMENTO_CHAVE_PIX = "gerenciamentoChavesPix";
	
	private static final String NAV_MODAL_EXCLUIR = "modalInfoExclusao";
	
	private ContaEmpresaBean contaBean;
	
	private GerenciamentoForm gerenciamentoForm;
	
	private ContaEmpresaSessionBean empresaSessionBean;
	
	private IBspiService bspiService;
	
	public String init() {
		this.gerenciamentoForm.iniciarCampos();
		listarChaves();
		return NAV_GERENCIAMENTO_CHAVE_PIX;
	}

	private void listarChaves() {
		
		CodigoServicoOperacao p = new CodigoServicoOperacao();
		/*
		 * 
		 * 
		 * 
		 * */
		p.setCodigoGrupo(32);
		//TODO: A Mariane informará como pegar a informação
		p.setCodigoOperacao(3);
		p.setCodigoServico(1);
		
		boolean possuiPermissao = verificarPermissao(p.getCodigoOperacao());
		
		Procurador procurador = DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso();
		List<Conta> listaOrdenada = EmpresaFactory.getEmpresaPerfilService().listarContasPerfilServico(procurador, Constantes.CAR_ADESAO);
		this.gerenciamentoForm.setEmpresa(procurador.getEmpresa());
		
		if (listaOrdenada != null && listaOrdenada.size() > 0) {
			this.gerenciamentoForm.setConta(listaOrdenada.get(0));
			obterTipoContaIniciarTela();
		}
		
		if(possuiPermissao == true) {
			this.obterListaServico();
			
		}
		
	}

	private void obterListaServico() {
		this.gerenciamentoForm.setErroServico(false);
		
		Conta conta = DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
				this.gerenciamentoForm.getConta().getAgencia(), this.gerenciamentoForm.getConta().getNumConta());
		
		BradescoCommonServiceFactory.getLogManager().info(this, 
			"Consulta da PEC3 agência:" + this.gerenciamentoForm.getConta().getAgencia() 
			+ " conta:" + this.gerenciamentoForm.getConta().getNumConta() 
			+ " -> razão CC:" + conta.getRazao() 
			+ " razão CP:" + conta.getRazaoContaPoupanca());
		
		//OBTEM A RAZAO DA CONTA
		if (this.gerenciamentoForm.getTipoConta().getCodigo() == TipoContaEnum.CORRENTE.getCodigo()) {
			this.gerenciamentoForm.getConta().setRazao(conta.getRazao());
		} else {
			this.gerenciamentoForm.getConta().setRazao(conta.getRazaoContaPoupanca());
		}
		
		this.gerenciamentoForm.setListaChaves(new ArrayList<GerenciamentoChaveModel>());
		ValidaContaResponse validaConta = this.bspiService.validaConta(this.gerenciamentoForm.getConta(), this.gerenciamentoForm.getEmpresa());
		
		if (validaConta != null) {
			for (ContratoConta contrato : validaConta.getContratos()) {
				
				ConsultaListaChavesBradescoRequest param = new ConsultaListaChavesBradescoRequest(); 
				param.setClub(validaConta.getClub());
				param.setCpssoaJurid(contrato.getCpssoaJurid());
				param.setCtpoNegoc(contrato.getCtpoNegoc());
				param.setNseqNegoc(contrato.getNseqNegoc());
				param.setTipoPesquisa(1);
				
				if (this.gerenciamentoForm.getTipoConta().getCodigo() == TipoContaEnum.CORRENTE.getCodigo()
						&& contrato.getCtpoNegoc() == 4) {
					
					this.obterListaChaves(param);
					
				} else if (this.gerenciamentoForm.getTipoConta().getCodigo() == TipoContaEnum.POUPANCA.getCodigo()
						&& contrato.getCtpoNegoc() == 5) {
					
					this.obterListaChaves(param);
				}
				
				
			}
		} else {
				// tratar erro 
				// Sistema indisponível. Tente mais tarde
				this.gerenciamentoForm.setErroServico(true);
				this.gerenciamentoForm.setListaChaves(new ArrayList<GerenciamentoChaveModel>());
			}
	}

	private void obterListaChaves(ConsultaListaChavesBradescoRequest param) {
		List<ConsultaListaChavesBradescoResponse> listaChaves = this.bspiService.consultaListaChaves(param);
		List<ConsultaListaChavesBradescoResponse> arrayChaves = new ArrayList<ConsultaListaChavesBradescoResponse>(listaChaves);
		
		for (ConsultaListaChavesBradescoResponse item : arrayChaves) {
			this.gerenciamentoForm.getListaChaves().add(new GerenciamentoChaveModel(
					item.getTipoChave(),
					item.getChave(),
					item.getNordChaveApldo(),
					item.getIapldoAdsaoSpi(),
					item.getIdCorrPoup(),
					item.getStatusChave(),
					item.getIdChavePrinc(),
					item.getHinclReg(),
					item.getSeqChave(),
					this.gerenciamentoForm.getConta().getAgencia(),
					this.gerenciamentoForm.getConta().getNumConta(),
					SiteUtil.tratarParametroString(this.gerenciamentoForm.getConta().obterContaDig())));
		}
	}

	
	public void recarregarListaChaves() {
		this.carregarAgenciaConta();
		this.obterListaServico();
	}
	
	public void carregarAgenciaConta() {
		ContaEmpresaBean.getInstance().getIdContaDebito();
		for (SelectItem item : this.empresaSessionBean.getListaContaServico()) {
			String itemSelec = SiteUtil.tratarParametroString(item.getValue());
			
			if (itemSelec.equals(ContaEmpresaBean.getInstance().getIdContaDebito())) {
				try {
					//agencia | conta-digito  1234 | 12345-4
					String[] split = item.getLabel().split("\\|");
					//conta - digito  12345-7
					String[] contaDigito = split[1].split("-");
					
					//OBTEM A RAZAO DA CONTA
					this.gerenciamentoForm.setConta(
							DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
									SiteUtil.tratarParametroInteger(split[0].trim()), 
									SiteUtil.tratarParametroLong(contaDigito[0].trim())));
					
					String tpConta = SiteUtil.tratarParametroString(split[2]);
					if (TipoContaEnum.CORRENTE.getDescricao().equals(tpConta.trim())) {
						this.gerenciamentoForm.setTipoConta(TipoContaEnum.CORRENTE);
						break;
					} else {
						this.gerenciamentoForm.setTipoConta(TipoContaEnum.POUPANCA);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String abrirModalExcluir() {
		Conta conta = DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
				this.getContaBean().getContaDebito().getAgencia(), this.getContaBean().getContaDebito().getNumConta());
		
		conta.setCodigoConta(this.getContaBean().getContaDebito().getCodigoConta());
		this.gerenciamentoForm.setConta(conta);
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		int chave = SiteUtil.tratarParametroInteger(request.getParameter("indexChaveExcluir"));
        
		this.gerenciamentoForm.setChaveSelecionada(this.gerenciamentoForm.getListaChaves().get(chave));
		
		return  confirmacaoDadosExclusao();
	}
	
	
	public String confirmacaoDadosExclusao() {

		return NavegacaoUtils.getConfirmacaoDadosExclusaoBean().init();
	}
	
	public String cadastrarNovaChave() {
		return NavegacaoUtils.getSelecaoAgenciaContaBean().initAdesao();
	}
	
	 /**
     * Verificar a permissão de consulta de um determinado serviço
     * @param int codigoOperacao contendo o código operação
     * @return boolean contendo true se for permitido
     */
    protected boolean verificarPermissao(int codigoOperacao) {
    	
        boolean retorno = false;
        
        CodigoServicoTipoOperacao servico = new CodigoServicoTipoOperacao();
        
        servico.setCodigoServico(Numeros.UM);
        
        Perfil perfil = PerfilFactory.getPerfilIbpjService().obterPerfil();
        if (servico != null) {
        	servico.setCodigoGrupo(32);
            servico.setCodigoOperacao(codigoOperacao);
            servico.setCodigoTipoServico(Numeros.DOIS);
        }
       
        if (PerfilFactory.getPermissaoIbpjService().verificarPermissao(perfil, servico)) {
        	this.gerenciamentoForm.setErroProcuradorSemPermissaoAcesso(false);
            retorno = true;
            
        } else {
            // Exibir a mensagem que "Procurador sem permissão de acesso ao serviço."
        	this.gerenciamentoForm.setErroProcuradorSemPermissaoAcesso(true);
            retorno = false;
        }
       
        return retorno;
    }
	
	public String recarregarPagina() {
		this.gerenciamentoForm.setChaveSelecionada(new GerenciamentoChaveModel());
		this.gerenciamentoForm.setChave(null);
		
		return NAV_GERENCIAMENTO_CHAVE_PIX;
	}
	
	public void obterTipoContaIniciarTela() {
		for (SelectItem item : this.empresaSessionBean.getListaContaServico()) {
			try {
				//agencia | conta-digito  1234 | 12345-4
				String[] split = item.getLabel().split("\\|");
				//conta - digito  12345-7
				String tpConta = SiteUtil.tratarParametroString(split[2]);
				if (TipoContaEnum.CORRENTE.getDescricao().equals(tpConta.trim())) {
					this.gerenciamentoForm.setTipoConta(TipoContaEnum.CORRENTE);
					break;
				} else {
					this.gerenciamentoForm.setTipoConta(TipoContaEnum.POUPANCA);
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public GerenciamentoForm getGerenciamentoForm() {
		return gerenciamentoForm;
	}

	public void setGerenciamentoForm(GerenciamentoForm gerenciamentoForm) {
		this.gerenciamentoForm = gerenciamentoForm;
	}

	public ContaEmpresaBean getContaBean() {
		return contaBean;
	}

	public void setContaBean(ContaEmpresaBean contaBean) {
		this.contaBean = contaBean;
	}

	public IBspiService getBspiService() {
		return bspiService;
	}

	public void setBspiService(IBspiService bspiService) {
		this.bspiService = bspiService;
	}

	public ContaEmpresaSessionBean getEmpresaSessionBean() {
		return empresaSessionBean;
	}

	public void setEmpresaSessionBean(ContaEmpresaSessionBean empresaSessionBean) {
		this.empresaSessionBean = empresaSessionBean;
	}
	

}
