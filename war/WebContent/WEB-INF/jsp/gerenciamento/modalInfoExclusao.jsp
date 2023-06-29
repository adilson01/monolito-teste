<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<t:saveState value="#{gerenciamentoForm}" />

<h:form id="modalInfoExclusao">

	<brArq:div styleClass="mt-24">
		<ib:campoFormularioNome styleClass="title-grande ">
			<brHtml:brOutputText
				value="#{messageBundle.excluir_chave_identificacao}" />
		</ib:campoFormularioNome>
	</brArq:div>

	<brArq:div>
		<brArq:div styleClass="col-8 mt-16">
			<brHtml:brPanelGroup>
				<brHtml:brOutputText styleClass="legenda"
					value="#{gerenciamentoChavesPixBean.gerenciamentoForm.chaveSelecionada.descricaoTipoChave}: " />
				<brHtml:brOutputText styleClass="legenda"
					value="#{gerenciamentoChavesPixBean.gerenciamentoForm.chaveSelecionada.chave}"
					rendered="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.tipoChave != 5}" />
				<brHtml:brOutputText styleClass="legenda"
					value="#{gerenciamentoChavesPixBean.gerenciamentoForm.chaveSelecionada.iapldoAdsaoSpi}"
					rendered="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.tipoChave == 5}" />
			</brHtml:brPanelGroup>
		</brArq:div>
	</brArq:div>

	<brArq:div styleClass="mt-24">

		<brArq:div id="divBotoes" styleClass="radioCustom mt-40">
			<brArq:div styleClass="col-12">
				<brArq:div>
					<h:commandLink
						styleClass="closeModal mb-16 btn-default-1 tabindex"
						title="Excluir" id="btn_filtrar" target="paginaCentral"
						action="#{gerenciamentoChavesPixBean.confirmacaoDadosExclusao}"
						value="Excluir">
						
					</h:commandLink>
					<h:commandLink title="Voltar"
						styleClass="tabindex ml-32 mb-16 link closeModal" immediate="true"
						action="#{gerenciamentoChavesPixBean.recarregarPagina}"
						target="modal_infra_estrutura"
						value="Voltar">
					</h:commandLink>
				</brArq:div>
			</brArq:div>
		</brArq:div>

	</brArq:div>
</h:form>