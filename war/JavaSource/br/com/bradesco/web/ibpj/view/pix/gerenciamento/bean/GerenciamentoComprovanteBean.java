package br.com.bradesco.web.ibpj.view.pix.gerenciamento.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ExluirChaveEnderecamentoRequest;
import br.com.bradesco.web.ibpj.pix.service.utils.TipoChave;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.gerenciamento.IGerenciamentoServicoConstantes;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

public class GerenciamentoComprovanteBean implements IGerenciamentoServicoConstantes {

	private boolean sucesso = false;
	private boolean insucesso = false;
	private boolean pendente= false;
	private String descricaoSucesso = "";

	public static final String NAV_MENSAGEM_EXCLUIR = "gerenciamentoComprovante";
	
	public String init() {
		this.setDescricaoSucesso("");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String numeroTransacao = request.getParameter("nutransacao");
		Transacao transacao = TransacaoFactory.getTransacaoService().obterTransacao(SiteUtil.tratarParametroLong(numeroTransacao));

		DadosPost dadosPost = TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao());

		Gson gson = new Gson();
		ExluirChaveEnderecamentoRequest dadosExclusao = gson.fromJson(dadosPost.getVariavel(DP_DADOS_EXCLUSAO), ExluirChaveEnderecamentoRequest.class);
		
		int tipoCHave = 0;
		try {
			tipoCHave = SiteUtil.tratarParametroInteger(dadosPost.getVariavel(TP_CHAVE));
		} catch (Exception e) {
			tipoCHave = 0 ;
		}
		
		if ("X".equals(SiteUtil.tratarParametroString(transacao.getStatusTransacao()))) {
			if (tipoCHave == 3) {
				this.setDescricaoSucesso("Chave Pix " + SiteUtil.formatarCnpj(dadosExclusao.getIaliasAdsaoCta()) + " excluída");
			} else if (tipoCHave == 5) {
				this.setDescricaoSucesso("Chave aleatória " + dadosExclusao.getIaliasAdsaoCta() + " excluída");
			}else if (tipoCHave == 2) {
				this.setDescricaoSucesso("Chave Pix " + SiteUtil.formatarCelular(dadosExclusao.getIaliasAdsaoCta()) + " cadastrada");
			} else {
				this.setDescricaoSucesso("Chave Pix " + dadosExclusao.getIaliasAdsaoCta() + " excluída");
			}
			this.sucesso = true;
		} else if ("A".equals(SiteUtil.tratarParametroString(transacao.getStatusTransacao()))) {
			this.pendente = true;
		} else {
			this.insucesso = true;
		}

		return NAV_MENSAGEM_EXCLUIR;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public boolean isInsucesso() {
		return insucesso;
	}

	public void setInsucesso(boolean insucesso) {
		this.insucesso = insucesso;
	}

	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}

	public String getDescricaoSucesso() {
		return descricaoSucesso;
	}

	public void setDescricaoSucesso(String descricaoSucesso) {
		this.descricaoSucesso = descricaoSucesso;
	}

}
