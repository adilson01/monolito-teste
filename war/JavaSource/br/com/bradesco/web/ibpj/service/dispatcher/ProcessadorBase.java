package br.com.bradesco.web.ibpj.service.dispatcher;

import br.com.bradesco.web.aq.application.adapter.sso.sharedata.AdapterSSOUtils;
import br.com.bradesco.web.aq.application.log.ILogManager;
import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.aq.application.util.spring.BradescoSpringUtils;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.operacao.autenticador.impl.AutenticadorServiceBase;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;

public class ProcessadorBase extends AutenticadorServiceBase {
	
	 /** Para armazenar as mensagens de log */
    protected ILogManager logManager = (ILogManager) BradescoSpringUtils.getBeanById("logManager");
    
    /** Status para a transação com comprovante */
    protected final int STATUS_TRANSACAO_COM_COMPROVANTE = 0;
    
    /** Status para a transação não efetuada */
    protected final int STATUS_TRANSACAO_NAO_EFETUADA = -1;
    
    /** Código de retorno da Bridge que determina que a transação não tem previsão de término */
    protected static final int STATUS_SEM_PREVISAO_TERMINO = 3;
    
    /** Armazena o número de tentativas de chamada em caso de sessão expirada */
    protected int numeroTentativasSessaoExpiradas = 1;
    
    /** Para armazenar as mensagens de log */
   // protected ILogManager logManager = (ILogManager) BradescoSpringUtils.getBeanById("logManager");
    
    /** Indica o fluxo de aprovação */
    protected final String FLUXO_APROVACAO = "aprovacao";
    
    /** Indica o fluxo de pendentes */
    protected final String FLUXO_PENDENTE = "pendente";
    
    
    protected Transacao recuperarTransacao(Transacao transacao) {
        transacao = TransacaoFactory.getTransacaoService().obterTransacao(transacao.getNumeroTransacao());
        transacao.setDadosPost(TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao()));
        return transacao;
    }

    /**
	 * Preencher os dados da Sessap
	 * 
	 * @param procurador
	 *            - dados do procurador
	 * @param Ctrl
	 *            - número de controle
	 */
	protected void popularDadosSessao(Procurador procurador, String Ctrl) {
		
		//String autenticacaoSeguranca = procurador.getEmpresa().getCnpj().getCnpjSemFormatacao() + procurador.getUsuario().getCpf().getCpfSemFormatacao();
		
		String autenticacaoSeguranca = "07549156000175" + "21601997884";
		
		BradescoCommonServiceFactory.getObjectManager().setSessionAttribute("login.identificadorPeriferico", "035");
		BradescoCommonServiceFactory.getObjectManager().setSessionAttribute("login.autenticacaoSeguranca", autenticacaoSeguranca);
		BradescoCommonServiceFactory.getObjectManager().setSessionAttribute("login.usuario", autenticacaoSeguranca);
		AdapterSSOUtils.setShareDataUser(autenticacaoSeguranca);
		AdapterSSOUtils.setShareDataKey(Ctrl);
		
	}
}
