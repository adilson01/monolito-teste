package br.com.bradesco.web.ibpj.view.utils;

import br.com.bradesco.web.aq.view.util.FacesUtils;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.CadastrarChaveBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.CadastroCelularBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.CadastroChaveOcultaBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.CadastroEmailBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.ConfirmacaoCadastroBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.ConfirmacaoDadosBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.SelecaoAgenciaContaBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.TermoDeUsoBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.TrocarPaisBean;
import br.com.bradesco.web.ibpj.view.pix.adesao.bean.ValidacaoPosseBean;
import br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean.ConfirmacaoDadosExclusaoBean;
import br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean.GerenciamentoChavesPixBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.ComprovantePendenteBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.InserirValorBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.SelecaoAgenciaContaTransferenciaBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.SelecionarBancoBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.SelecionarChaveBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.TransferenciaAgenciaContaBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.TransferenciaComprovanteBean;
import br.com.bradesco.web.ibpj.view.pix.transferencia.bean.TransferenciaConfirmacaoDadosBean;

/**
 * Nome: NavegacaoUtils </br> Propósito:
 * <p>
 * NavegacaoUtils
 * </p>
 * .
 * 
 */
public class NavegacaoUtils {
    /**
     * Instancia um novo NavegacaoUtils.
     */
    public NavegacaoUtils() {
        super();
    }
    
    /**
     * Nome: beanName
     * Propósito: Obter name.
     * 
     * @param classe
     *        the classe
     * @return object
     * @see
     */
    public static Object obterName(String classe) {
        String name = classe;
        return FacesUtils.getManagedBean(name.substring(0, 1).toLowerCase().concat(name.substring(1, name.length())));
    }
    
    public static final CadastrarChaveBean getCadastrarChaveBean() {
        return (CadastrarChaveBean) obterName(CadastrarChaveBean.class.getSimpleName());
    }
    
    public static final ConfirmacaoCadastroBean getConfirmacaoCadastroBean() {
        return (ConfirmacaoCadastroBean) obterName(ConfirmacaoCadastroBean.class.getSimpleName());
    }
    
    public static final ValidacaoPosseBean getValidacaoPosseBean() {
        return (ValidacaoPosseBean) obterName(ValidacaoPosseBean.class.getSimpleName());
    }
    
    public static final CadastroCelularBean getCadastroCelularBean() {
        return (CadastroCelularBean) obterName(CadastroCelularBean.class.getSimpleName());
    }
    
    public static final TrocarPaisBean getTrocarPaisBean() {
        return (TrocarPaisBean) obterName(TrocarPaisBean.class.getSimpleName());
    }
    
    public static final SelecionarBancoBean getSelecionarBancoBean() {
        return (SelecionarBancoBean) obterName(SelecionarBancoBean.class.getSimpleName());
    }
    
    public static final CadastroEmailBean getCadastroEmailBean() {
        return (CadastroEmailBean) obterName(CadastroEmailBean.class.getSimpleName());
    }
    
    public static final ConfirmacaoDadosBean getConfirmacaoDadosBean() {
        return (ConfirmacaoDadosBean) obterName(ConfirmacaoDadosBean.class.getSimpleName());
    }
    
    public static final SelecaoAgenciaContaBean getSelecaoAgenciaContaBean() {
        return (SelecaoAgenciaContaBean) obterName(SelecaoAgenciaContaBean.class.getSimpleName());
    }
    
    public static final TermoDeUsoBean getTermoDeUsoBean() {
        return (TermoDeUsoBean) obterName(TermoDeUsoBean.class.getSimpleName());
    }
    
    //######################## TELAS DE TRANSFERENCIA #######################################
    
    public static final SelecionarChaveBean getSelecionarChaveBean() {
        return (SelecionarChaveBean) obterName(SelecionarChaveBean.class.getSimpleName());
    }
    
    public static final CadastroChaveOcultaBean getCadastroChaveOcultaBean() {
        return (CadastroChaveOcultaBean) obterName(CadastroChaveOcultaBean.class.getSimpleName());
    }
    
    public static final InserirValorBean getInserirValorBean() {
        return (InserirValorBean) obterName(InserirValorBean.class.getSimpleName());
    }
    
    public static final SelecaoAgenciaContaTransferenciaBean getSelecaoAgenciaContaTransferenciaBean() {
        return (SelecaoAgenciaContaTransferenciaBean) obterName(SelecaoAgenciaContaTransferenciaBean.class.getSimpleName());
    }
    
    public static final TransferenciaAgenciaContaBean getTransferenciaAgenciaContaBean() {
        return (TransferenciaAgenciaContaBean) obterName(TransferenciaAgenciaContaBean.class.getSimpleName());
    }
    
    public static final TransferenciaConfirmacaoDadosBean getTransferenciaConfirmacaoDadosBean() {
        return (TransferenciaConfirmacaoDadosBean) obterName(TransferenciaConfirmacaoDadosBean.class.getSimpleName());
    }
    
    public static final TransferenciaComprovanteBean getTransferenciaComprovanteBean() {
        return (TransferenciaComprovanteBean) obterName(TransferenciaComprovanteBean.class.getSimpleName());
    }
    
    public static final ComprovantePendenteBean getComprovantePendenteBean() {
        return (ComprovantePendenteBean) obterName(ComprovantePendenteBean.class.getSimpleName());
    }
    
    public static final ConfirmacaoDadosExclusaoBean getConfirmacaoDadosExclusaoBean() {
        return (ConfirmacaoDadosExclusaoBean) obterName(ConfirmacaoDadosExclusaoBean.class.getSimpleName());
    }
    
    public static final GerenciamentoChavesPixBean getGerenciamentoChavesPixBean() {
        return (GerenciamentoChavesPixBean) obterName(GerenciamentoChavesPixBean.class.getSimpleName());
    }
   
}
