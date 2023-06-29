package br.com.bradesco.web.ibpj.service.business.gerenciamento.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import br.com.bradesco.web.aq.application.log.ILogManager;
import br.com.bradesco.web.aq.application.util.faces.BradescoFacesUtils;
import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ExluirChaveEnderecamentoRequest;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.exceptions.TransacaoException;
import br.com.bradesco.web.ibpj.service.business.gerenciamento.IGerenciamentoService;
import br.com.bradesco.web.ibpj.service.business.gerenciamento.IGerenciamentoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.exception.DadosPostException;
import br.com.bradesco.web.ibpj.service.business.sessao.SessaoFactory;
import br.com.bradesco.web.ibpj.service.model.GerenciamentoChaveModel;
import br.com.bradesco.web.ibpj.view.utils.Constantes;

public class GerenciamentoServiceImpl implements IGerenciamentoService, IGerenciamentoServicoConstantes {

	private ILogManager logManager;
	
	public Transacao criarTransacao(Conta conta, Procurador procurador,
			GerenciamentoChaveModel chaveSelecionada) {
	        
	        Transacao transacao = new Transacao();
	    	/**
	    	 * 32 é o grupo
	    		1 é o serviço
	    		3 é o tipo serviço
	    		
	    		DsTipoServico é a descrição do serviço
	    		32 cdGrupo
	    		1 cdServico
	    		3 TpServico
	    	 * @return
	    	 */
	        
			CodigoServicoTipoOperacao codigoServicoTipo = new CodigoServicoTipoOperacao();
			codigoServicoTipo.setCodigoGrupo(Constantes.COD_GRUPO);
			codigoServicoTipo.setCodigoOperacao(Constantes.CODIGO_OPERACAO_EFETIVA);
			codigoServicoTipo.setCodigoServico(Constantes.COD_TIPO_SERVICO);
	        codigoServicoTipo.setCodigoTipoServico(3); // 3 = ???
	        
	        transacao.setCodigoServicoTipoOperacao(codigoServicoTipo);
	        transacao.setDescricao(DESCRICAO_OPERACAO);
      
	        Conta contaAdesao = new Conta();
	        contaAdesao.setAgencia(conta.getAgencia());
	        contaAdesao.setBanco(conta.getBanco());
	        contaAdesao.setNumConta(conta.getNumConta());
	        contaAdesao.setTitularidade(conta.getTitularidade());
	        contaAdesao.setCodigoConta(conta.getCodigoConta());
	        transacao.setConta(contaAdesao);
	        
	        transacao.setEmpresa(procurador.getEmpresa());
	        transacao.setValor(new BigDecimal(0));
        
        // Salvar os dados transação
        try {
            transacao = TransacaoFactory.getTransacaoService().salvarTransacao(transacao);
        } catch (Exception exception) {
            throw new TransacaoException("Não foi possível criar a transação.", exception);
        }
        // Salva o dadosPost
        DadosPost dadosPostTO = montarDadosPost(chaveSelecionada,procurador);
        transacao.setDadosPost(dadosPostTO);
        try {
            TransacaoFactory.getTransacaoService().salvarDadosPost(transacao);
            
            // Gerar e Salvar texto assinado
     		transacao.setTextoAssinado(transacao.getDescricao());
     		TransacaoFactory.getTransacaoService().salvarTextoAssinado(transacao);
     		
        } catch (Exception exception) {
            throw new TransacaoException("Não foi possível salvar dados post da transação.", exception);
        }
          
       return transacao;
	}
	
    /**
     * Metodo para montar a DadosPost da transação dos lote pgit (Consolidado)
     * 
     * @param lotePgit
     *        com os dados do lote
     * @param cnpjPagFor
     *        se cnpj no PagFor.
     * @return DadosPost da transação
     */
    private DadosPost montarDadosPost(GerenciamentoChaveModel chaveSelecionada, Procurador procurador) {
        DadosPost dadosPost = new DadosPost();
        try 
        	{
        	Gson gson = new Gson();
            dadosPost.setVariavel(DP_CTRL, SessaoFactory.getSessaoIbpjService().obterSessao().getNumControle());
            
            ExluirChaveEnderecamentoRequest param = new ExluirChaveEnderecamentoRequest();
    		param.setCnpjCpfDig(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa().getCnpj().getControle());
    		param.setCnpjCpfFilial(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa().getCnpj().getFilial());
    		param.setCnpjCpfNro(DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso().getEmpresa().getCnpj().getNumero());
    		param.setIaliasAdsaoCta(chaveSelecionada.getChave());
    		
            //DADOS PARA EXCLUIR CHAVE
            dadosPost.setVariavel(DP_DADOS_EXCLUSAO,gson.toJson(param));
            dadosPost.setVariavel(TP_CHAVE,gson.toJson(chaveSelecionada.getTipoChave()));
            
            //DADOS PROCURADOR
            dadosPost.setVariavel(DP_NOME_USUARIO, procurador.getUsuario().getNome());
            dadosPost.setVariavel(CPF_USUARIO, procurador.getUsuario().getCpf().getCpfSemFormatacao());
           
            //DADOS EMPRESA
            dadosPost.setVariavel(DP_NOME_EMPRESA, procurador.getEmpresa().getNomeEmpresa());
            dadosPost.setVariavel(CNPJ_EMPR_SEM_FORMATACAO, procurador.getEmpresa().getCnpj().getCnpjSemFormatacao());
            dadosPost.setVariavel(CNPJ_EMPR_COM_FORMATACAO, procurador.getEmpresa().getCnpj().getCnpjFormatado());
            
            //DATA DA OPERACAO
            dadosPost.setVariavel(DATA_OPERACAO, String.valueOf(GregorianCalendar.getInstance(BradescoFacesUtils.getLocale()).getTime().getTime()));
            
        } catch (NullPointerException exception) {
            throw new TransacaoException("Não foi possível criar o dados post da transação.", exception);
        } catch (DadosPostException exception) {
            throw new TransacaoException("Não foi possível criar o dados post da transação.", exception);
        }
        return dadosPost;
    }

	public ILogManager getLogManager() {
		return logManager;
	}

	public void setLogManager(ILogManager logManager) {
		this.logManager = logManager;
	}
	
}
