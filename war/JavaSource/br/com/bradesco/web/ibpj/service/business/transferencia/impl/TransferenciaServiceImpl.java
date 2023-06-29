package br.com.bradesco.web.ibpj.service.business.transferencia.impl;

import java.math.BigDecimal;

import com.google.gson.Gson;

import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.exceptions.TransacaoException;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.exception.DadosPostException;
import br.com.bradesco.web.ibpj.service.business.sessao.SessaoFactory;
import br.com.bradesco.web.ibpj.service.business.transferencia.ITransferenciaService;
import br.com.bradesco.web.ibpj.service.business.transferencia.ITransferenciaServicoConstantes;
import br.com.bradesco.web.ibpj.service.model.EfetivaTransferenciaModel;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class TransferenciaServiceImpl implements ITransferenciaService, ITransferenciaServicoConstantes {

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
	public Transacao criarTransacaoTransferencia(Conta conta, 
												 Procurador procurador, 
									             CodigoServicoTipoOperacao trinca,
									             EfetivaTransferenciaModel dados) {
	        
	        Transacao transacao = new Transacao();
	        
	        CodigoServicoTipoOperacao codigoServicoTipo = new CodigoServicoTipoOperacao();
	        codigoServicoTipo.setCodigoGrupo(trinca.getCodigoGrupo());
	        codigoServicoTipo.setCodigoOperacao(trinca.getCodigoOperacao());
	        codigoServicoTipo.setCodigoServico(trinca.getCodigoTipoServico());
	        codigoServicoTipo.setDescricao(trinca.getDescricao());
	        codigoServicoTipo.setCodigoTipoServico(3); // 2 = parcial
	        transacao.setCodigoServicoTipoOperacao(codigoServicoTipo);
      
	        Conta contaDeb = new Conta();
	        contaDeb.setAgencia(conta.getAgencia());
	        contaDeb.setBanco(conta.getBanco());
	        contaDeb.setNumConta(conta.getNumConta());
	        contaDeb.setTitularidade(conta.getTitularidade());
	        contaDeb.setCodigoConta(conta.getCodigoConta());
	        transacao.setConta(contaDeb);
	        
	        transacao.setEmpresa(procurador.getEmpresa());
	        transacao.setDescricao(Constantes.TIPO_OP_TRANSFERENCIA);
	        transacao.setValor(new BigDecimal(0));
        
	        // Salvar os dados transação
	        try {
	            transacao = TransacaoFactory.getTransacaoService().salvarTransacao(transacao);
	            
	        } catch (Exception exception) {
	            throw new TransacaoException("Não foi possível criar a transação.", exception);
	        }
	        
	        // Salva o dadosPost
	        DadosPost dadosPostTO = montarDadosPost(dados,procurador,conta);
	        transacao.setDadosPost(dadosPostTO);
	        
	        try {
	            TransacaoFactory.getTransacaoService().salvarDadosPost(transacao);
	            
	        } catch (Exception exception) {
	            throw new TransacaoException("Não foi possível salvar dados post da transação.", exception);
	        }
	        
	        try {
	        	// Gerar e Salvar texto assinado
	     		transacao.setTextoAssinado(transacao.getDescricao());
	     		TransacaoFactory.getTransacaoService().salvarTextoAssinado(transacao);
	            
	        } catch (Exception exception) {
	            throw new TransacaoException("Não foi possível salvar o texto assinado da transação.", exception);
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
    private DadosPost montarDadosPost(EfetivaTransferenciaModel dados, Procurador procurador, Conta conta) {
        DadosPost dadosPost = new DadosPost();
        try 
        	{
        	Gson gson = new Gson();
            dadosPost.setVariavel(CTRL, SessaoFactory.getSessaoIbpjService().obterSessao().getNumControle());
            
            //informacoes empresa
            dadosPost.setVariavel(EMPRESA_CNPJ_FORMAT, procurador.getEmpresa().getCnpj().getCnpjFormatado() +" | "+ procurador.getEmpresa().getNomeEmpresa());
            dadosPost.setVariavel(NOME_PROCURADOR, procurador.getUsuario().getNome());
            dadosPost.setVariavel(AGENCIA_CONTA_FORMAT_DEB, SiteUtil.montarAgenciaConta(conta));
            dadosPost.setVariavel(TIPO_CONTA_DEB, SiteUtil.tratarParametroString(conta.getTipoConta()));
            
            //dados recebedor
            dadosPost.setVariavel(RECEB_TIPO_CHAVE, SiteUtil.tratarParametroString(dados.getChave().getTipoChave()));
            dadosPost.setVariavel(RECEB_NUM_CPF_CNPJ_FORMAT,"");
            dadosPost.setVariavel(RECEB_CHAVE, SiteUtil.tratarParametroString(dados.getChave().getChave()));
            dadosPost.setVariavel(RECEB_NOME, SiteUtil.tratarParametroString(dados.getChave().getNomeUsuario()));
            dadosPost.setVariavel(RECEB_AGENCIA, SiteUtil.tratarParametroString(dados.getChave().getAgenteUsuario()));
            dadosPost.setVariavel(RECEB_CONTA, SiteUtil.tratarParametroString(dados.getChave().getConta()));
            dadosPost.setVariavel(RECEB_TIPO_CONTA,"");
            
            //dados da trasnferencia
            dadosPost.setVariavel(VALOR_TARIFA,"");
            dadosPost.setVariavel(ID_TRANSACAO,"");
            dadosPost.setVariavel(DATA_HORA,"");
            dadosPost.setVariavel(DEBITADO_CONTA,"");
            
            //objeto de transferencia
            dadosPost.setVariavel(DADOS_TRANSFERENCIA, gson.toJson(dados));
            
            
        } catch (NullPointerException exception) {
            throw new TransacaoException("Não foi possível criar o dados post da transação.", exception);
        } catch (DadosPostException exception) {
            throw new TransacaoException("Não foi possível criar o dados post da transação.", exception);
        }
        return dadosPost;
    }
	
}
