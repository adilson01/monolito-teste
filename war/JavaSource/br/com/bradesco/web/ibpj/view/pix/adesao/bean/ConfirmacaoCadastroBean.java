package br.com.bradesco.web.ibpj.view.pix.adesao.bean;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.DadosCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.utils.TipoChave;
import br.com.bradesco.web.ibpj.pix.transacao.util.PixTransacaoUtil;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class ConfirmacaoCadastroBean implements IAdesaoServicoConstantes{

	private static final String NAV_CONFIRMACAO_CADASTRO = "confirmacaoCadastro";
	private static final String NAV_ERRO_CADASTRO = "erroCadastro";
	
	private String retorno;
	
	private boolean confirmacao;
	private boolean erroOutroPSP;
	private boolean erroOutroUsuario;
	private boolean pendente;
	
	Map<String,String> requestParams;
	
	private AdesaoForm form;
	
	/**
	 * Inicialização via acesso direto
	 * @return String contendo o navigation
	 */
	public String init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String numeroTransacao = request.getParameter("nutransacao");
        
        Transacao transacao = TransacaoFactory.getTransacaoService().obterTransacao(SiteUtil.tratarParametroLong(numeroTransacao));

        DadosCadastroChaveRequest chave = PixTransacaoUtil.recuperarChaveDadosPost(transacao.getDadosPost());

        
		if("X".equals(SiteUtil.tratarParametroString(transacao.getStatusTransacao()))) {
			
			if (chave.getTipoChave().name().equals(TipoChave.CNPJ.name())) {
				this.form.setDescricaoSucesso("Chave Pix " + SiteUtil.formatarCnpj(chave.getChave()) + " cadastrada");
				
			} else if (chave.getTipoChave().name().equals(TipoChave.EVP.name())) {
				this.form.setDescricaoSucesso("Chave aleatória " + chave.getChave() + " cadastrada");
				
			} else if (chave.getTipoChave().name().equals(TipoChave.CELULAR.name())) {
				this.form.setDescricaoSucesso("Chave Pix " + SiteUtil.formatarCelular(chave.getChave()) + " cadastrada");
				
			} else{
				this.form.setDescricaoSucesso("Chave Pix " + chave.getChave() + " cadastrada");
			}
			this.confirmacao = true;
			return NAV_CONFIRMACAO_CADASTRO;
		} else if ("A".equals(SiteUtil.tratarParametroString(transacao.getStatusTransacao()))) {
			this.pendente = true;
			return NAV_ERRO_CADASTRO;
		} else {
			this.pendente = true;
			return NAV_ERRO_CADASTRO;
			
		}
		
	
	}
	
	
	/**
	 * Obter o navigation do menu inical
	 * @return String contendo o nome da navigation do menu inicial
	 */
	public String voltarParaMenu() {
		return "";
	}
	
	public boolean isConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public boolean isErroOutroPSP() {
		return erroOutroPSP;
	}

	public void setErroOutroPSP(boolean erroOutroPSP) {
		this.erroOutroPSP = erroOutroPSP;
	}

	public boolean isErroOutroUsuario() {
		return erroOutroUsuario;
	}

	public void setErroOutroUsuario(boolean erroOutroUsuario) {
		this.erroOutroUsuario = erroOutroUsuario;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public AdesaoForm getForm() {
		return form;
	}

	public void setForm(AdesaoForm form) {
		this.form = form;
	}


	public boolean isPendente() {
		return pendente;
	}


	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}
	

}
