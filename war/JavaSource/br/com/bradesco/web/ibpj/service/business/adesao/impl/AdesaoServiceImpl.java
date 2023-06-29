package br.com.bradesco.web.ibpj.service.business.adesao.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import br.com.bradesco.web.aq.application.log.ILogManager;
import br.com.bradesco.web.aq.application.util.faces.BradescoFacesUtils;
import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoService;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.exceptions.TransacaoException;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.exception.DadosPostException;
import br.com.bradesco.web.ibpj.service.business.sessao.SessaoFactory;
import br.com.bradesco.web.ibpj.view.utils.Constantes;

public class AdesaoServiceImpl implements IAdesaoService, IAdesaoServicoConstantes {

	private ILogManager logManager;
	
	public Transacao criarTransacao(Conta conta, Procurador procurador,
									CriaChaveUnicaRequest dados) {
	        
	        Transacao transacao = new Transacao();
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
	        
	        CodigoServicoTipoOperacao servicoAdesao = new CodigoServicoTipoOperacao();
			servicoAdesao.setCodigoGrupo(32);
			servicoAdesao.setCodigoServico(1);
			servicoAdesao.setCodigoTipoServico(2);
			
	        transacao.setCodigoServicoTipoOperacao(servicoAdesao);
	        transacao.setDescricao(DESCRICAO_OPERACAO);
	         
	        transacao.setConta(conta);
	        
	        transacao.setEmpresa(procurador.getEmpresa());
	        transacao.setValor(new BigDecimal(0));
        
        // Salvar os dados transação
        try {
            transacao = TransacaoFactory.getTransacaoService().salvarTransacao(transacao);
        } catch (Exception exception) {
            throw new TransacaoException("Não foi possível criar a transação.", exception);
        }
        // Salva o dadosPost
        DadosPost dadosPostTO = montarDadosPost(dados,procurador, conta);
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
    private DadosPost montarDadosPost(CriaChaveUnicaRequest dados, Procurador procurador, Conta conta) {
        DadosPost dadosPost = new DadosPost();
        try 
        	{
        	Gson gson = new Gson();
            dadosPost.setVariavel(DP_CTRL, SessaoFactory.getSessaoIbpjService().obterSessao().getNumControle());
            dadosPost.setVariavel("AG", String.valueOf(conta.getAgencia()));
    		dadosPost.setVariavel("CONTA", String.valueOf(conta.getNumConta()));
    		dadosPost.setVariavel("RAZAO", String.valueOf(conta.getRazao()));
            
            //DADOS PARA ADESAO DE CHAVE
            dadosPost.setVariavel(DP_DADOS_ADESAO,gson.toJson(dados));
            
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
