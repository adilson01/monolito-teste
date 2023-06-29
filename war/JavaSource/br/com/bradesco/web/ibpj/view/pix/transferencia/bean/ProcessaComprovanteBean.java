package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.bradesco.web.ib.view.components.app.UIComprovante.dados.Comprovante;
import br.com.bradesco.web.ibpj.service.business.operacao.comprovante.OperacaoComprovanteFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.TransacaoFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.DadosPost;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.model.ComprovanteTransferenciaModel;
import br.com.bradesco.web.ibpj.view.bundle.MessagesPixResourceBundle;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;
import br.com.bradesco.web.ibpj.view.utils.SiteUtil;
import util.criptografia.Criptografia;
import util.criptografia.CriptografiaException;

public class ProcessaComprovanteBean {

	private String label;
	private String valor;
	private boolean habilitaDadosRecebedor;
	private String autenticacao;
	ComprovanteTransferenciaModel comprovanteTransferenciaModel;
	HtmlPanelGroup dadosEmpresa = new HtmlPanelGroup();
	HtmlPanelGroup dadosRecebedor = new HtmlPanelGroup();
	HtmlPanelGroup dadosTransacao = new HtmlPanelGroup();
	HtmlPanelGroup dadosAutenticacao = new HtmlPanelGroup();
	Map<HtmlOutputText, HtmlOutputText> labelDadosEmpresa = new LinkedHashMap<HtmlOutputText, HtmlOutputText>();
	Map<HtmlOutputText, HtmlOutputText> labelDadosRecebedor = new LinkedHashMap<HtmlOutputText, HtmlOutputText>();
	Map<HtmlOutputText, HtmlOutputText> labelDadosTransacao = new LinkedHashMap<HtmlOutputText, HtmlOutputText>();
	List<HtmlOutputText> labelAutenticacao = new ArrayList<HtmlOutputText>();

	/**
	 * As 16 posições dos caracteres para a montagem da parte não criptografada da
	 * autenticação digital.
	 */
	private static final int[] AUT_DIG_POS_16_CPF_CNPJ = { 4, 3, 15, 12, 1, 2, 14, 22, 14, 4, 3, 5, 17, 13, 3, 11 };

	/**
	 * As 16 posições dos caracteres para a montagem da parte não criptografada da
	 * autenticação digital.
	 */
	private static final int[] AUT_DIG_POS_16_CELULAR = { 4, 4, 15, 12, 1, 2, 8, 22, 14, 4, 3, 6, 17, 13, 3, 10 };

	/**
	 * As 16 posições dos caracteres para a montagem da parte não criptografada da
	 * autenticação digital.
	 */
	private static final int[] AUT_DIG_POS_16_EMAIL = { 4, 15, 22, 12, 1, 2, 17, 19, 14, 4, 3, 16, 21, 13, 3, 23 };

	/**
	 * As 16 posições dos caracteres para a montagem da parte não criptografada da
	 * autenticação digital.
	 */
	private static final int[] AUT_DIG_POS_16_AGENCIA = { 4, 9, 15, 12, 1, 3, 2, 6, 22, 14, 4, 2, 3, 8, 17, 13 };

	/** Tamanho do ID da transação para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_ID_TRANSACAO = 21;

	/** Tamanho do Valor para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_VALOR = 15;

	/** Tamanho do dtOperacao para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_DT_OPERACAO = 8;

	/** Tamanho do hrOperacao para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_HR_OPERACAO = 6;

	/** Tamanho do CPF OU CNPJ para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_CPF_CNPJ = 15;

	/** Tamanho do numCelular para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_NUM_CELULAR = 13;

	/** Tamanho da agência para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_AG = 5;

	/** Tamanho da Conta do destinatario para a criação da autenticação digital. */
	private static final int AUT_DIG_TAM_CONTA_DESTINATARIO = 10;

	public String processaComprovante() {

		/* Obter a transação a partir da url */
		 HttpServletRequest request = (HttpServletRequest)
		 FacesContext.getCurrentInstance().getExternalContext()
		 .getRequest();
		
		 Transacao transacao = TransacaoFactory.getTransacaoService()
		 .obterTransacao(Long.parseLong(request.getParameter("nutransacao")));

		//Transacao transacao = TransacaoFactory.getTransacaoService().obterTransacao(100741253);

		DadosPost dadosPost = TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao());
		transacao.setDadosPost(dadosPost);

		Comprovante comprovante = (OperacaoComprovanteFactory.getInstance().obterComprovanteTransacao(transacao));

		carregaMaps(comprovante);

		montaAutenticacao(carregaModelComprovante(comprovante));

		carregaPainel();

		return NavegacaoUtils.getTransferenciaComprovanteBean().init(this.dadosEmpresa, this.dadosRecebedor,
				this.dadosTransacao, this.dadosAutenticacao, transacao, isHabilitaDadosRecebedor());

	}

	public String processaComprovantePendente() {

		/* Obter a transação a partir da url */
		// HttpServletRequest request = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequest();
		//
		// Transacao transacao = TransacaoFactory.getTransacaoService()
		// .obterTransacao(Long.parseLong(request.getParameter("nutransacao")));

		// implementando
		Map<String, String> teste = new LinkedHashMap<String, String>();
		Transacao transacao = TransacaoFactory.getTransacaoService().obterTransacao(100741253);

		DadosPost dadosPost = TransacaoFactory.getTransacaoService().obterDadosPost(transacao.getNumeroTransacao());
		transacao.setDadosPost(dadosPost);

		Comprovante comprovante = (OperacaoComprovanteFactory.getInstance().obterComprovanteTransacao(transacao));

		/*teste.put("DADOS DA EMPRESA DE TESTE ESPAÇAMENTO", "");
		teste.put("nome DA EMPRESA: ", "teste");*/

		comprovante.setTransacao(teste);
		comprovante.setDadosAutenticacao(SiteUtil.formatarTextoAutenticado("dsa/*dasfsafhsuaif0s98au8fsahf0a9sfan09fansfaikfgbas90fsaiufsa(*fsafasfsa*fasfsa*fas*"));

		return NavegacaoUtils.getComprovantePendenteBean().init(comprovante);
	}

	private void montaAutenticacao(ComprovanteTransferenciaModel comprovanteModel) {
		if (comprovanteTransferenciaModel.getTipoChave().toLowerCase().equals("celular")) {
			montaAutenticacaoCelular(comprovanteTransferenciaModel);

		} else if (comprovanteTransferenciaModel.getTipoChave().toLowerCase().equals("email")) {
			montaAutenticacaoEmail(comprovanteTransferenciaModel);

		} else if (comprovanteTransferenciaModel.getTipoChave().toLowerCase().equals("agenciaconta")) {
			montaAutenticacaoAgencia(comprovanteTransferenciaModel);

		} else if (comprovanteTransferenciaModel.getTipoChave().toLowerCase().equals("cpf/cnpj")) {

			montaAutenticacaoCpfCnpj(comprovanteTransferenciaModel);

		}

	}

	private void montaAutenticacaoCpfCnpj(ComprovanteTransferenciaModel comprovanteModel) {
		char[] buf = new char[16];
		StringBuffer autenticador = new StringBuffer();
		int index = 0;

		// informações utilizadas para montar a autenticação

		autenticador.append(SiteUtil.formatarTexto(comprovanteModel.getIdTransacao(), AUT_DIG_TAM_ID_TRANSACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getValor(), AUT_DIG_TAM_VALOR));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getDataDaOperacao(), AUT_DIG_TAM_DT_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getHoraDaOperacao(), AUT_DIG_TAM_HR_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getCpfCnpj(), AUT_DIG_TAM_CPF_CNPJ));

		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getCpfCnpj().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getCpfCnpj().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getCpfCnpj().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_CPF_CNPJ[index++]);

		;

		// Monta as informações que serão criptografadas pelo autenticador

		String txtAutDigital = new String(buf) + autenticador.toString();

		try {
			Criptografia criptografia = Criptografia.getInstance();
			String criptografada = criptografia.gerarAutenticacaoDigital(autenticador.toString());

			txtAutDigital = criptografada + new String(buf);
		} catch (CriptografiaException e) {

			e.printStackTrace();
		}

		Matcher matcher = Pattern.compile("<pre>(.*)</pre>").matcher(SiteUtil.formatarTextoAutenticado(txtAutDigital));

		while (matcher.find()) {
			this.labelAutenticacao.add(convertTituloLabelToHtmlOutputText(matcher.group(1)));
		}
	}

	private void montaAutenticacaoAgencia(ComprovanteTransferenciaModel comprovanteModel) {
		char[] buf = new char[16];
		StringBuffer autenticador = new StringBuffer();
		int index = 0;

		// informações utilizadas para montar a autenticação
		// comprovanteModel.setIdTransacao("E6074694820200612151143a2516A014");
		autenticador.append(SiteUtil.formatarTexto(comprovanteModel.getIdTransacao(), AUT_DIG_TAM_ID_TRANSACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getValor(), AUT_DIG_TAM_VALOR));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getDataDaOperacao(), AUT_DIG_TAM_DT_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getHoraDaOperacao(), AUT_DIG_TAM_HR_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getAgenciaDoDestinatario(), AUT_DIG_TAM_AG));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getContaDoDestinatarioSemDigito(),
				AUT_DIG_TAM_CONTA_DESTINATARIO));

		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getContaDoDestinatarioSemDigito().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getAgenciaDoDestinatario().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getContaDoDestinatarioSemDigito().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getAgenciaDoDestinatario().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getContaDoDestinatarioSemDigito().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);

		// Monta as informações que serão criptografadas pelo autenticador
		String txtAutDigital = new String(buf) + autenticador.toString();

		try {
			Criptografia criptografia = Criptografia.getInstance();
			String criptografada = criptografia.gerarAutenticacaoDigital(autenticador.toString());

			txtAutDigital = criptografada + new String(buf);
		} catch (CriptografiaException e) {

			e.printStackTrace();
		}

		Matcher matcher = Pattern.compile("<pre>(.*)</pre>").matcher(SiteUtil.formatarTextoAutenticado(txtAutDigital));

		while (matcher.find()) {
			this.labelAutenticacao.add(convertTituloLabelToHtmlOutputText(matcher.group(1)));
		}
	}

	private void montaAutenticacaoEmail(ComprovanteTransferenciaModel comprovanteModel) {
		char[] buf = new char[16];
		StringBuffer autenticador = new StringBuffer();
		int index = 0;

		// informações utilizadas para montar a autenticação
		// comprovanteModel.setIdTransacao("E6074694820200612151143a2516A014");
		autenticador.append(SiteUtil.formatarTexto(comprovanteModel.getIdTransacao(), AUT_DIG_TAM_ID_TRANSACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getValor(), AUT_DIG_TAM_VALOR));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getDataDaOperacao(), AUT_DIG_TAM_DT_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getHoraDaOperacao(), AUT_DIG_TAM_HR_OPERACAO));

		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);

		// Monta as informações que serão criptografadas pelo autenticador
		String txtAutDigital = new String(buf) + autenticador.toString();

		try {
			Criptografia criptografia = Criptografia.getInstance();
			String criptografada = criptografia.gerarAutenticacaoDigital(autenticador.toString());

			txtAutDigital = criptografada + new String(buf);
		} catch (CriptografiaException e) {

			e.printStackTrace();
		}
		
		Matcher matcher = Pattern.compile("<pre>(.*)</pre>").matcher(SiteUtil.formatarTextoAutenticado(txtAutDigital));

		while (matcher.find()) {
			this.labelAutenticacao.add(convertTituloLabelToHtmlOutputText(matcher.group(1)));
		}
	}

	private void montaAutenticacaoCelular(ComprovanteTransferenciaModel comprovanteModel) {
		char[] buf = new char[16];
		StringBuffer autenticador = new StringBuffer();
		int index = 0;

		// informações utilizadas para montar a autenticação
		// comprovanteModel.setIdTransacao("E6074694820200612151143a2516A014");
		autenticador.append(SiteUtil.formatarTexto(comprovanteModel.getIdTransacao(), AUT_DIG_TAM_ID_TRANSACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getValor(), AUT_DIG_TAM_VALOR));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getDataDaOperacao(), AUT_DIG_TAM_DT_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getHoraDaOperacao(), AUT_DIG_TAM_HR_OPERACAO));
		autenticador.append(SiteUtil.formatarNumero(comprovanteModel.getNumCelular(), AUT_DIG_TAM_NUM_CELULAR));

		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getNumCelular().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getNumCelular().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getHoraDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getNumCelular().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getIdTransacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getValor().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getDataDaOperacao().charAt(AUT_DIG_POS_16_AGENCIA[index++]);
		buf[index] = comprovanteModel.getNumCelular().charAt(AUT_DIG_POS_16_AGENCIA[index++]);

		// Monta as informações que serão criptografadas pelo autenticador
		String txtAutDigital = new String(buf) + autenticador.toString();

		try {
			Criptografia criptografia = Criptografia.getInstance();
			String criptografada = criptografia.gerarAutenticacaoDigital(autenticador.toString());

			txtAutDigital = criptografada + new String(buf);
		} catch (CriptografiaException e) {

			e.printStackTrace();
		}
		String teste = SiteUtil.formatarTextoAutDigital(txtAutDigital);
		
		Matcher matcher = Pattern.compile("<pre>(.*)</pre>").matcher(SiteUtil.formatarTextoAutenticado(txtAutDigital));

		while (matcher.find()) {
			this.labelAutenticacao.add(convertTituloLabelToHtmlOutputText(matcher.group(1)));
		}

	}

	private ComprovanteTransferenciaModel carregaModelComprovante(Comprovante comprovante) {
		comprovanteTransferenciaModel = new ComprovanteTransferenciaModel();

		for (String key : comprovante.getTransacao().keySet()) {
			if (key.contains("chave_da_transacao")) {
				 comprovanteTransferenciaModel.setTipoChave(comprovante.getTransacao().get(key));
			//	comprovanteTransferenciaModel.setTipoChave("celular");
				// comprovanteTransferenciaModel.setTipoChave("email");
				// comprovanteTransferenciaModel.setTipoChave("agenciaconta");
				// comprovanteTransferenciaModel.setTipoChave("cpf/cnpj");

			} else if (key.contains("label_dados_transferencia_valor")) {
				comprovanteTransferenciaModel.setValor(comprovante.getTransacao().get(key));

			} else if (key.contains("label_dataDaOperacao")) {
				comprovanteTransferenciaModel.setDataDaOperacao(comprovante.getTransacao().get(key));

			} else if (key.contains("label_dados_recebimento_cpf_cnpj")
					|| key.contains("label_dados_transferencia_cnpj_cpf")) {
				comprovanteTransferenciaModel.setCpfCnpj(comprovante.getTransacao().get(key));

			} else if (key.contains("label_horaDaOperacao")) {
				comprovanteTransferenciaModel.setHoraDaOperacao(comprovante.getTransacao().get(key));

			} else if (key.contains("label_dados_recebimento_chave_vinculada")) {
				// Caso seja celular
				comprovanteTransferenciaModel.setNumCelular(comprovante.getTransacao().get(key));

			} else if (key.contains("label_dados_recebimento_agencia")) {
				// Caso seja agencia e conta
				comprovanteTransferenciaModel.setAgenciaDoDestinatario(comprovante.getTransacao().get(key));

			} else if (key.contains("label_dados_recebimento_numContaRecebedor")) {
				// Caso seja agencia e conta
				comprovanteTransferenciaModel.setContaDoDestinatarioSemDigito(comprovante.getTransacao().get(key));
			} else if (key.contains("label_dados_transferencia_id_transacao")) {
				// Caso seja agencia e conta
				comprovanteTransferenciaModel.setIdTransacao(comprovante.getTransacao().get(key));
			}

		}

		return comprovanteTransferenciaModel;

	}

	@SuppressWarnings("unchecked")
	private void carregaPainel() {

		/* Definindos o style dos paineis */
		this.dadosEmpresa.setStyleClass("TextoPanelGroup");

		this.dadosRecebedor.setStyleClass("TextoPanelGroup");
		
		this.dadosTransacao.setStyleClass("TextoPanelGroup");

		this.dadosAutenticacao.setStyleClass("TextoPanelGroup");

		// Carregando dados empresa
		for (Entry<HtmlOutputText, HtmlOutputText> entry : this.labelDadosEmpresa.entrySet()) {

			this.dadosEmpresa.getChildren().add(entry.getKey());
			this.dadosEmpresa.getChildren().add(entry.getValue());

			HtmlOutputText brT = new HtmlOutputText();
			brT.setEscape(false);
			brT.setValue("<br/> <br/>");
			brT.setStyle("margin-top:48px");
			this.dadosEmpresa.getChildren().add(brT);
		}

		// Carregando dados recebedor
		for (Entry<HtmlOutputText, HtmlOutputText> entry : this.labelDadosRecebedor.entrySet()) {

			this.dadosRecebedor.getChildren().add(entry.getKey());
			this.dadosRecebedor.getChildren().add(entry.getValue());

			HtmlOutputText brT = new HtmlOutputText();
			brT.setEscape(false);
			brT.setValue("<br/> <br/>");
			brT.setStyle("margin-top:48px");
			this.dadosRecebedor.getChildren().add(brT);
		}

		// Carregando dados transação
		for (Entry<HtmlOutputText, HtmlOutputText> entry : this.labelDadosTransacao.entrySet()) {

			this.dadosTransacao.getChildren().add(entry.getKey());
			this.dadosTransacao.getChildren().add(entry.getValue());

			HtmlOutputText brT = new HtmlOutputText();
			brT.setEscape(false);
			brT.setValue("<br/> <br/>");
			brT.setStyle("margin-top:48px");
			this.dadosTransacao.getChildren().add(brT);
		}

		// Carregando autenticacao
		for (HtmlOutputText entry : this.labelAutenticacao) {

			this.dadosAutenticacao.getChildren().add(entry);

			HtmlOutputText brT = new HtmlOutputText();
			brT.setEscape(false);
			brT.setValue("<br/>");
			brT.setStyle("margin-top:48px");
			this.dadosAutenticacao.getChildren().add(brT);
		}

	}

	public void carregaMaps(Comprovante comprovante) {

		for (String key : comprovante.getTransacao().keySet()) {
			if (key.contains("chave_da_transacao")) {

				
				  if (comprovante.getTransacao().get(key).toLowerCase().equals("celular") ||
				  comprovante.getTransacao().get(key).toLowerCase().equals("email")) {
				 

//				if ("CELULAR".toLowerCase().equals("celular")
//						|| comprovante.getTransacao().get(key).toLowerCase().equals("email")) {

					setHabilitaDadosRecebedor(true);
					montaMapsCelularEmail(comprovante);
					break;
				} else if (comprovante.getTransacao().get(key).toLowerCase().equals("agenciaConta")) {
					setHabilitaDadosRecebedor(true);
					montaMapsAgencia(comprovante);
					break;
				} else if (comprovante.getTransacao().get(key).toLowerCase().equals("cpf/cnpj")) {
					setHabilitaDadosRecebedor(false);
					montaMapsCPFCNPJ(comprovante);
					break;
				}
			}
		}

	}

	private void montaMapsCPFCNPJ(Comprovante comprovante) {
		for (String key : comprovante.getTransacao().keySet()) {

			if (!(key.contains("chave_da_transacao") || key.contains("label_dataDaOperacao")
					|| key.contains("label_horaDaOperacao") || key.contains("label_dados_empresa_instituicao")
					|| key.contains("label_dados_recebimento") || key.contains("label_dados_transferencia_descricao")
					|| key.contains("label_dados_transferencia_id_transacao"))
					|| key.contains("label_dados_recebimento_nome")
					|| key.contains("label_dados_recebimento_instituicao")) {

				this.setLabel(MessagesPixResourceBundle.getInstance().getString(key));
				this.setValor(comprovante.getTransacao().get(key));

				if (key.toLowerCase().contains("label_dados_empresa")) {

					this.labelDadosEmpresa.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));

				} else if (key.toLowerCase().contains("label_dados_transferencia")
						|| key.contains("label_dados_recebimento_nome")
						|| key.contains("label_dados_recebimento_instituicao")) {

					if (key.toLowerCase().contains("label_dados_transferencia_cnpj_cpf") && getValor().length() == 11) {
						this.setValor(SiteUtil.ocultarCPF(getValor()));
					}

					if (key.toLowerCase().contains("label_dados_transferencia_data_hora")) {
						this.setLabel("Data da transação: ");
					}

					this.labelDadosTransacao.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));
				}
			}
		}

	}

	private void montaMapsCelularEmail(Comprovante comprovante) {
		for (String key : comprovante.getTransacao().keySet()) {

			if (!(key.contains("chave_da_transacao") || key.contains("label_dataDaOperacao")
					|| key.contains("label_horaDaOperacao") || key.contains("label_dados_transferencia_cnpj_cpf")
					|| key.contains("label_dados_recebimento_agencia")
					|| key.contains("label_dados_recebimento_tipoContaRecebedor")
					|| key.contains("label_dados_recebimento_numContaRecebedor"))) {

				this.setLabel(MessagesPixResourceBundle.getInstance().getString(key));
				this.setValor(comprovante.getTransacao().get(key));

				if (key.toLowerCase().contains("label_dados_empresa")) {

					this.labelDadosEmpresa.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));

				} else if (key.toLowerCase().contains("label_dados_recebimento")) {

					if (key.toLowerCase().contains("label_dados_recebimento_cpf_cnpj") && getValor().length() == 11) {
						this.setValor(SiteUtil.ocultarCPF(getValor()));
					}

					this.labelDadosRecebedor.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));

				} else if (key.toLowerCase().contains("label_dados_transferencia")) {

					this.labelDadosTransacao.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));
				}
			}
		}

	}

	private void montaMapsAgencia(Comprovante comprovante) {
		String tipoConta = null;
		for (String key : comprovante.getTransacao().keySet()) {

			if (!(key.contains("chave_da_transacao") || key.contains("label_dataDaOperacao")
					|| key.contains("label_horaDaOperacao") || key.contains("label_dados_transferencia_cnpj_cpf")
					|| key.contains("label_dados_recebimento_chave_vinculada"))) {

				if (key.contains("label_dados_recebimento_tipoContaRecebedor")
						|| key.contains("label_dados_recebimento_numContaRecebedor")) {

					this.setValor(comprovante.getTransacao().get(key));

				} else {

					this.setLabel(MessagesPixResourceBundle.getInstance().getString(key));
					this.setValor(comprovante.getTransacao().get(key));

				}

				if (key.toLowerCase().contains("label_dados_empresa")) {

					this.labelDadosEmpresa.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));

				} else if (key.toLowerCase().contains("label_dados_recebimento")) {

					if (key.toLowerCase().contains("label_dados_recebimento_cpf_cnpj") && getValor().length() == 11) {
						this.setValor(SiteUtil.ocultarCPF(getValor()));
					}

					if (key.contains("label_dados_recebimento_tipoContaRecebedor")) {

						tipoConta = getValor() + ": ";

					} else if (key.contains("label_dados_recebimento_numContaRecebedor")) {

						this.labelDadosRecebedor.put(convertTituloLabelToHtmlOutputText(tipoConta),
								convertLabelToHtmlOutputText(getValor()));
					} else {

						this.labelDadosRecebedor.put(convertTituloLabelToHtmlOutputText(getLabel()),
								convertLabelToHtmlOutputText(getValor()));

					}
				} else if (key.toLowerCase().contains("label_dados_transferencia")) {

					this.labelDadosTransacao.put(convertTituloLabelToHtmlOutputText(getLabel()),
							convertLabelToHtmlOutputText(getValor()));
				}
			}
		}

	}

	public HtmlOutputText convertTituloLabelToHtmlOutputText(String label) {

		HtmlOutputText labelTitulo = new HtmlOutputText();
		labelTitulo.setValue(label);
		labelTitulo.setStyleClass("legenda");

		return labelTitulo;

	}

	public HtmlOutputText convertLabelToHtmlOutputText(String label) {

		HtmlOutputText valorInfo = new HtmlOutputText();
		valorInfo.setValue(label);
		valorInfo.setStyleClass("p-pequeno");

		return valorInfo;

	}

	public String getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(String autenticacao) {
		this.autenticacao = autenticacao;
	}

	public boolean isHabilitaDadosRecebedor() {
		return habilitaDadosRecebedor;
	}

	public void setHabilitaDadosRecebedor(boolean habilitaDadosRecebedor) {
		this.habilitaDadosRecebedor = habilitaDadosRecebedor;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
