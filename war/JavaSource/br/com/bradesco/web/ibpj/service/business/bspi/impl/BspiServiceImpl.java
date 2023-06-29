package br.com.bradesco.web.ibpj.service.business.bspi.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.web.aq.application.log.ILogManager;
import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.ibpj.pix.service.PixServicesFactory;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ConsultaListaChavesBradescoRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ExluirChaveEnderecamentoRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.IntencaoCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.ConsultaListaChavesBradescoResponse;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.CriaChaveUnicaResponse;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.IntencaoCadastroChaveResponse;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ValidaContaResponse;
import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.pix.service.posse.model.request.ValidaPosseRequest;
import br.com.bradesco.web.ibpj.pix.service.posse.model.response.ValidaPosseResponse;
import br.com.bradesco.web.ibpj.service.business.BusinessServiceBase;
import br.com.bradesco.web.ibpj.service.business.bspi.IBspiService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Empresa;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;

public class BspiServiceImpl extends BusinessServiceBase implements IBspiService {
	
	private ILogManager logManager;

    public ValidaContaResponse validaConta(Conta conta, Empresa empresa) {
    	try {
    		return PixServicesFactory.getInstanceForContaService().validarConta(
    					   SiteUtil.tratarParametroInteger(conta.getAgencia()),
     					   SiteUtil.tratarParametroLong(conta.getNumConta()) ,
     					   SiteUtil.tratarParametroInteger(empresa.getCnpj().getNumero()),
     					   SiteUtil.tratarParametroInteger(empresa.getCnpj().getFilial()), 
     					   SiteUtil.tratarParametroInteger(empresa.getCnpj().getControle()), 
     					   SiteUtil.tratarParametroString(conta.getRazao()));
    		
		} catch (PixApiException e) {
			BradescoCommonServiceFactory.getLogManager().info(e, "Erro ao validar a conta");
			return null;
		}
    }

	@Override
	public List<ConsultaListaChavesBradescoResponse> consultaListaChaves(ConsultaListaChavesBradescoRequest dados) {
		try {
			return PixServicesFactory.getInstanceForChaveService().consultarListaChavesBradesco(dados);
		} catch (PixApiException e) {
			if (Constantes.COD_SEM_CHAVES_CAD.equals(e.getCode())) {
				BradescoCommonServiceFactory.getLogManager().info(this, "LISTA VAZIA - NAO HA DADOS PARA SEREM DEMONSTRADOS");
			} else if (Constantes.COD_CONRATO_INVALIDO.equals(e.getCode())) {
				BradescoCommonServiceFactory.getLogManager().info(this, "CONTRATO DE CONTA INVALIDO");
			} else {
				throw e;
			}
			return new ArrayList<ConsultaListaChavesBradescoResponse>();
		}
	}
	
	public CriaChaveUnicaResponse incluirChaveUnica(CriaChaveUnicaRequest chave) {
		try {
			return PixServicesFactory.getInstanceForChaveService().criarChaveUnica(chave);
		} catch (PixApiException e) {
			
			BradescoCommonServiceFactory.getLogManager().info(e, e.getMessage());
			
			return new CriaChaveUnicaResponse();
		}
	}
	
	public IntencaoCadastroChaveResponse criarIntencaoCadastroChaveUnica(IntencaoCadastroChaveRequest chave) {
			return PixServicesFactory.getInstanceForChaveService().criarIntencaoCadastroChaveUnica(chave);
	}
	
	@Override
	public ValidaPosseResponse validaPosse(Conta conta, 
										   Empresa empresa, 
										   TipoChaveEnum chave, 
										   String token,
										   String ddi, 
										   String celular, 
										   String email) {
		
		ValidaPosseResponse response = new ValidaPosseResponse();
		
		ValidaPosseRequest posse = new ValidaPosseRequest();
		
		//dados da conta
		posse.setAgencia(SiteUtil.tratarParametroInteger(conta.getAgencia()));
		posse.setConta(SiteUtil.tratarParametroInteger(conta.getNumConta()));
		posse.setDac(SiteUtil.tratarParametroInteger(conta.obterContaDig()));
		posse.setTipoConta(SiteUtil.tratarParametroInteger(conta.getTitularidade()));
		posse.setTitularidade(SiteUtil.tratarParametroInteger(conta.getTitularidade()));
		
		//dados da empresa
		posse.setCcpfCnpj(empresa.getCnpj().getNumero());
		posse.setCctrlCpfCnpj(empresa.getCnpj().getControle());
		posse.setcFlialCcpfCnpj(empresa.getCnpj().getFilial());

		//dados da chave
		posse.setCddi(SiteUtil.tratarParametroInteger(ddi));
		posse.setCddd(SiteUtil.obterDDD(celular));
		posse.setCelular(SiteUtil.obterNumeroCelularSemDDD(celular));
		posse.setEmail(SiteUtil.tratarParametroString(email));
		
		
		posse.setNomeCliente(SiteUtil.tratarParametroString(empresa.getNomeEmpresa()));
		
		posse.setEnviarVia(chave.getCodigo() == 1 ? "S" : "E");
		posse.setSenha(0);
		posse.setDados(SiteUtil.tratarParametroString(token));
		
		posse.setCanal(Constantes.COD_CANAL);
		//posse.setNomeLogicoCanal("");
		
		if (SiteUtil.isNotEmptyOrNull(token)) {
			posse.setEnviarVia("");
			return PixServicesFactory.getInstancePosseService().validarToken(posse);
		}
		
		response = chave.getCodigo() == 1 ?
				PixServicesFactory.getInstancePosseService().enviarSms(posse) : 
					PixServicesFactory.getInstancePosseService().enviarCodigoEmail(posse);
					
		return response;
	}
	
	//@Override
	public void excluirChave(ExluirChaveEnderecamentoRequest chave) {
		try {
			PixServicesFactory.getInstanceForChaveService().excluirChave(chave);
		} catch (PixApiException e) {
			System.out.println(e.getMessage());
		}
	}

	public ILogManager getLogManager() {
		return logManager;
	}

	public void setLogManager(ILogManager logManager) {
		this.logManager = logManager;
	}

}
