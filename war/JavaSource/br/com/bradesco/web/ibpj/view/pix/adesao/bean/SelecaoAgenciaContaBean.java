package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.aq.view.util.Numeros;
import br.com.bradesco.web.ib.service.business.midia.bean.Midia;
import br.com.bradesco.web.ibpj.bean.CodigoServicoOperacao;
import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ConsultaListaChavesBradescoRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.ConsultaListaChavesBradescoResponse;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ContratoConta;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ValidaContaResponse;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoService;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.empresa.EmpresaFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.PerfilFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.bean.Perfil;
import br.com.bradesco.web.ibpj.service.business.sessao.SessaoFactory;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaBean;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaSessionBean;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoContaEnum;

public class SelecaoAgenciaContaBean {

	private static final String NAV_SELECAO_AGENCIA_CONTA = "selecaoAgenciaConta";
	
	private boolean botaoContinuar = true;
	
	private IAdesaoService adesaoService;
	
	private IBspiService bspiService;
	
	private AdesaoForm form;
	
	private boolean confirmacao;
	
	ContaEmpresaSessionBean empresaSessionBean = ContaEmpresaSessionBean.getInstance();
	
	ContaEmpresaBean contaBean = ContaEmpresaBean.getInstance();
	
	private int quantidadeChaves = 0;
	
	public String initAdesao() {
		renderizarBtnContinuar();
		return NAV_SELECAO_AGENCIA_CONTA;
	}
	
	public void renderizarBtnContinuar() {
		
		CodigoServicoOperacao p = new CodigoServicoOperacao();
		/*
		 * 
		 * 
		 * 
		 * */
		p.setCodigoGrupo(32);
		//TODO: A Mariane informará como pegar a informação
		p.setCodigoOperacao(2);
		p.setCodigoServico(1);
		
		boolean possuiPermissao = verificarPermissao(p.getCodigoOperacao());
		
		Procurador procurador = DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso();
		List<Conta> listaOrdenada = EmpresaFactory.getEmpresaPerfilService().listarContasPerfilServico(procurador, Constantes.CAR_ADESAO);

		this.form.setEmpresa(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa());
		
		if (listaOrdenada != null && listaOrdenada.size() > 0 
				&& possuiPermissao == true) {
			this.form.setBtnContAgenciaConta(false);
		} else {
				this.form.setBtnContAgenciaConta(true);
				
		}
	}
	

	public String continuar() {
		//limpa os campos para as regras abaixo
		this.limparCampos();
		
		this.form.setEmpresa(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa());
		Conta conta = DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
				this.getContaBean().getContaDebito().getAgencia(), this.getContaBean().getContaDebito().getNumConta());
		BradescoCommonServiceFactory.getLogManager().info(this, 
			"Consulta da PEC3 agência:" + this.getContaBean().getContaDebito().getAgencia() 
			+ " conta:" + this.getContaBean().getContaDebito().getNumConta() 
			+ " -> razão CC:" + conta.getRazao() 
			+ " razão CP:" + conta.getRazaoContaPoupanca());
		this.obterTipoConta();
		
		this.form.setConta(this.getContaBean().getContaDebito());
		
		if (this.form.getTipoConta().getCodigo() == TipoContaEnum.CORRENTE.getCodigo()) {
			this.form.getConta().setRazao(conta.getRazao());
		} else {
			this.form.getConta().setRazao(conta.getRazaoContaPoupanca());
		}
		
		ValidaContaResponse validaConta = this.bspiService.validaConta(this.form.getConta(), this.form.getEmpresa());
		
		if (validaConta != null) {
			if (Constantes.PERMITE_ADESAO_PIX.equals(validaConta.getPermiteTrans())) {
				for (ContratoConta contrato : validaConta.getContratos()) {
					
					if (this.form.getTipoConta().getCodigo() == TipoContaEnum.CORRENTE.getCodigo()
							&& contrato.getCtpoNegoc() == 4) {
						this.obterListaChaves(validaConta, contrato);
						
					} else if (this.form.getTipoConta().getCodigo() == TipoContaEnum.POUPANCA.getCodigo()
							&& contrato.getCtpoNegoc() == 5) {
						this.obterListaChaves(validaConta, contrato);
					}
				}
			} else {
				this.form.setMensagemErro("Agência e conta não disponível para adesão PIX.");
				this.form.setContaNaoPodeOperarPix(true);
				return "";
			}
		} else {
			this.form.setMensagemErro("Erro ao validar a agência e conta.");
			this.form.setErroServico(true);
			return "";
		}
		
		if (this.quantidadeChaves == 20 || this.quantidadeChaves > 20) {
			this.form.setNumeroMaximoChavesExcedido(true);
		} else {
			this.form.setNumeroMaximoChavesExcedido(false);
		}
		return NavegacaoUtils.getCadastrarChaveBean().init();
		
	}

	private void obterListaChaves(ValidaContaResponse validaConta, ContratoConta contrato) {
		
		ConsultaListaChavesBradescoRequest param = new ConsultaListaChavesBradescoRequest(); 
		
		param.setClub(validaConta.getClub());
		param.setCpssoaJurid(contrato.getCpssoaJurid());
		param.setCtpoNegoc(contrato.getCtpoNegoc());
		param.setNseqNegoc(contrato.getNseqNegoc());
		param.setTipoPesquisa(1);
		
		List<ConsultaListaChavesBradescoResponse> listaChaves = this.bspiService.consultaListaChaves(param);
		List<ConsultaListaChavesBradescoResponse> arrayChaves = new ArrayList<ConsultaListaChavesBradescoResponse>(listaChaves);
		
		this.form.setClub(validaConta.getClub());
		this.form.setCtpoNegoc(contrato.getCtpoNegoc());
		this.form.setNseqNegoc(contrato.getNseqNegoc());
		this.form.setCpssoaJurid(contrato.getCpssoaJurid());
		
		for (ConsultaListaChavesBradescoResponse chave : arrayChaves) {
			if (chave.getTipoChave() == TipoChaveEnum.CNPJ.getCodigo()) {
				this.form.setOcultarTipoChaveCNPJ(true);
				break;
			}
		}
		this.quantidadeChaves += listaChaves.size();
	}

	private void limparCampos() {
		this.form.setContaNaoPodeOperarPix(false);
		this.form.setErroServico(false);
		this.form.setOcultarTipoChaveCNPJ(false);
		this.form.setMensagemErro("");
		this.quantidadeChaves = 0;
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
        	this.form.setErroProcuradorSemPermissaoAcesso(false);
            retorno = true;
            
        } else {
            // Exibir a mensagem que "Procurador sem permissão de acesso ao serviço."
        	this.form.setErroProcuradorSemPermissaoAcesso(true);
            retorno = false;
        }
       
        return retorno;
    }
    
	
	public String recarregarPagina() {
		return NAV_SELECAO_AGENCIA_CONTA;
	}

	public boolean isBotaoContinuar() {
		return botaoContinuar;
	}
	
	public void obterTipoConta() {
		ContaEmpresaBean.getInstance().getIdContaDebito();
		for (SelectItem item : this.empresaSessionBean.getListaContaServico()) {
			String itemSelec = SiteUtil.tratarParametroString(item.getValue());
			
			if (itemSelec.equals(this.contaBean.getIdContaDebito())) {
				try {
					//agencia | conta-digito  1234 | 12345-4
					String[] split = item.getLabel().split("\\|");
					//conta - digito  12345-7
					String tpConta = SiteUtil.tratarParametroString(split[2]);
					if (TipoContaEnum.CORRENTE.getDescricao().equals(tpConta.trim())) {
						this.form.setTipoConta(TipoContaEnum.CORRENTE);
						break;
					} else {
						this.form.setTipoConta(TipoContaEnum.POUPANCA);
						break;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setBotaoContinuar(boolean botaoContinuar) {
		this.botaoContinuar = botaoContinuar;
	}

	public IAdesaoService getAdesaoService() {
		return adesaoService;
	}

	public void setAdesaoService(IAdesaoService adesaoService) {
		this.adesaoService = adesaoService;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
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

	public boolean isConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public int getQuantidadeChaves() {
		return quantidadeChaves;
	}

	public void setQuantidadeChaves(int quantidadeChaves) {
		this.quantidadeChaves = quantidadeChaves;
	}

}
