package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.bradesco.web.aq.view.util.Numeros;
import br.com.bradesco.web.ib.service.business.midia.bean.Midia;
import br.com.bradesco.web.ibpj.bean.CodigoServicoOperacao;
import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.client.saldosextratos.SaldosExtratosFactory;
import br.com.bradesco.web.ibpj.client.saldosextratos.bean.SaldosServico;
import br.com.bradesco.web.ibpj.client.saldosextratos.saldos.ISaldoIBPJService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.empresa.EmpresaFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.PerfilFactory;
import br.com.bradesco.web.ibpj.service.business.perfil.bean.Perfil;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaBean;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.ContaEmpresaSessionBean;
import br.com.bradesco.web.ibpj.view.bean.empresa.gruposervico.GrupoEmpresaSessionBean;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class SelecaoAgenciaContaTransferenciaBean {

	public SelecaoAgenciaContaTransferenciaBean() {
		 this.contaEmpresaBean = ContaEmpresaBean.getInstance();
		 this.empresaSessionBean = ContaEmpresaSessionBean.getInstance();
		 this.grupoEmpresaSessionBean = GrupoEmpresaSessionBean.getInstance();
	}
	
	private static final String NAV_SELECAO_AGENCIA_CONTA_TRANSFERENCIA = "selecaoAgenciaContaTransferencia";
	
	private boolean botaoContinuar = true;
	
	private TransferenciaForm transform;
	
	ContaEmpresaBean contaEmpresaBean;
	
	ContaEmpresaSessionBean empresaSessionBean;
	
	GrupoEmpresaSessionBean grupoEmpresaSessionBean;
	
	private ISaldoIBPJService saldoIBPJService;
	
	public String initTransferencia() {
		this.transform.iniciarSaldoLimite();
		this.transform.inciarCamposChave();
		this.iniciarDados();
		
		this.carregarSaldoELimites();
		
		return NAV_SELECAO_AGENCIA_CONTA_TRANSFERENCIA;
	}
	
	private void carregarSaldoELimites() {
		this.transform.setExibirCardSaldoDiponivel(true);
		this.obterSaldoConta();
		//VALORES SERÃO CARREGADOS POR SERVICO BSPI
		this.transform.getHorariosSaldoLimiteModel().setLimiteDiario("3.000.000,00");
		this.transform.getHorariosSaldoLimiteModel().setLimiteDisponivel("2.500.000,00");
	}

	public void iniciarDados() {
		
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
		
		if (listaOrdenada != null && listaOrdenada.size() > 0) {
			//obtem a conta do serviço do canal de listar conta
			this.transform.setConta(listaOrdenada.get(0));
			
			//obtem a conta com razao
			this.transform.setConta(DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
					this.transform.getConta().getAgencia(), this.transform.getConta().getNumConta()));
			
			this.transform.setContaFormatadaSelecionada(SiteUtil.montarAgenciaConta(this.transform.getConta()));
		}
		
		
		this.transform.setEmpresa(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa());

		if (listaOrdenada != null && listaOrdenada.size() > 0
				&& possuiPermissao == true) {
			this.transform.setBtnContAgenciaConta(false);
		} else {
			this.transform.setBtnContAgenciaConta(true);
		}
	}
	

	public String continuar() {
		this.transform.setEmpresa(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa());
		this.transform.getTrasnferenciaModel().setAgencia(this.getContaEmpresaBean().getContaDebito().getAgencia());
		this.transform.getTrasnferenciaModel().setConta(SiteUtil.tratarParametroLong(this.getContaEmpresaBean().getContaDebito().getNumConta()));
		this.transform.getTrasnferenciaModel().setDac(SiteUtil.tratarParametroString(this.getContaEmpresaBean().getContaDebito().obterContaDig()));
		this.transform.setConta(this.getContaEmpresaBean().getContaDebito());
		
		return NavegacaoUtils.getSelecionarChaveBean().init();
	}
	
	public void recarregarHorariosLimitesAgenciaConta() {
		this.carregarAgenciaConta();
		this.obterSaldoConta();
		
		//VALORES SERÃO CARREGADOS POR SERVICO BSPI
		this.transform.getHorariosSaldoLimiteModel().setLimiteDiario("99.000.000,00");
		this.transform.getHorariosSaldoLimiteModel().setLimiteDisponivel("88.500.000,00");
	}
	
	public void carregarAgenciaConta() {
		ContaEmpresaBean.getInstance().getIdContaDebito();
		for (SelectItem item : this.empresaSessionBean.getListaContaServico()) {
			String itemSelec = SiteUtil.tratarParametroString(item.getValue());
			
			if (itemSelec.equals(this.contaEmpresaBean.getIdContaDebito())) {
				this.transform.setContaFormatadaSelecionada(item.getLabel());
				try {
					//agencia | conta-digito  1234 | 12345-4
					String[] split = item.getLabel().split("\\|");
					//conta - digito  12345-7
					String[] contaDigito = split[1].split("-");
					this.transform.setContaFormatadaSelecionada(
							SiteUtil.montarAgenciaConta(
									SiteUtil.tratarParametroInteger(split[0].trim()), 
									SiteUtil.tratarParametroLong(contaDigito[0].trim()), 
									SiteUtil.tratarParametroLong(contaDigito[1].trim())));
					
					//obtem a conta com razao
					this.transform.setConta(DadoClienteFactory.getDadoContaService().obterDadoConta(Midia.MIDIA_NETEMPRESA,
							SiteUtil.tratarParametroInteger(split[0].trim()), SiteUtil.tratarParametroLong(contaDigito[0].trim())));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void obterSaldoConta() {
		//SALDO CARREGADO PELO CANAL
		try {
			// Lembrando que o objeto conta precisa estar com os campos preenchidos: agência/conta/tipo de conta ("CC" -> conta-corrente, "CP" -> conta-poupança)
			if (SiteUtil.isEmptyOrNull(this.transform.getConta().getRazao())) {
				this.transform.getConta().setTipoConta(Constantes.COD_CONTA_POUP);
			} else {
				this.transform.getConta().setTipoConta(Constantes.COD_CONTA_CORRENT);
			}
			SaldosServico saldosServico = SaldosExtratosFactory.getSaldoIBPJService().obterSaldo(
					DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso(), this.transform.getConta());
			   
			// Para obter o valor total
			this.transform.getHorariosSaldoLimiteModel().setSaldo(SiteUtil.formatarValorApresentacao(saldosServico.getTotalDisponivel()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
        	this.transform.setErroProcuradorSemPermissaoAcesso(false);
            retorno = true;
            
        } else {
            // Exibir a mensagem que "Procurador sem permissão de acesso ao serviço."
        	this.transform.setErroProcuradorSemPermissaoAcesso(true);
            retorno = false;
        }
       
        return retorno;
    }
	
	public String recarregarPagina() {
		return NAV_SELECAO_AGENCIA_CONTA_TRANSFERENCIA;
	}

	public boolean isBotaoContinuar() {
		return botaoContinuar;
	}

	public void setBotaoContinuar(boolean botaoContinuar) {
		this.botaoContinuar = botaoContinuar;
	}

	public ContaEmpresaSessionBean getEmpresaSessionBean() {
		return empresaSessionBean;
	}

	public void setEmpresaSessionBean(ContaEmpresaSessionBean empresaSessionBean) {
		this.empresaSessionBean = empresaSessionBean;
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

	public GrupoEmpresaSessionBean getGrupoEmpresaSessionBean() {
		return grupoEmpresaSessionBean;
	}

	public void setGrupoEmpresaSessionBean(GrupoEmpresaSessionBean grupoEmpresaSessionBean) {
		this.grupoEmpresaSessionBean = grupoEmpresaSessionBean;
	}

	public ContaEmpresaBean getContaEmpresaBean() {
		return contaEmpresaBean;
	}

	public void setContaEmpresaBean(ContaEmpresaBean contaEmpresaBean) {
		this.contaEmpresaBean = contaEmpresaBean;
	}

	public ISaldoIBPJService getSaldoIBPJService() {
		return saldoIBPJService;
	}

	public void setSaldoIBPJService(ISaldoIBPJService saldoIBPJService) {
		this.saldoIBPJService = saldoIBPJService;
	}

}
