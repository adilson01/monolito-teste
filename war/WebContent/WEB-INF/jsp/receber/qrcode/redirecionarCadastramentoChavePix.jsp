<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<t:saveState value="#{adesaoForm}" />

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
		});
	</script>
</f:verbatim>

<h:form id="redirecionarCadastramentoChavePixForm">

	<f:verbatim>
		<ul class="card clearfix mt-24">
			<li class="cardList card-regular sombra-nivel200">
				<!-- INICIO CARD -->
	</f:verbatim>

	<brArq:div styleClass="alinhamento-centro">
		<t:div>
			<h:outputText styleClass="title-medio  mb-32"
				value="#{messageBundle.cadastre_chave_pix}"></h:outputText>
		</t:div>
	</brArq:div>

	<brArq:div styleClass="mt-24 mb-32 ">
		<t:div styleClass="alinhamento-centro mb-4">
			<h:outputText styleClass="legenda"
				value="Para Criar QR code, é necessário" />
		</t:div>
		<t:div styleClass="alinhamento-centro">
			<h:outputText styleClass="legenda" value="ter uma chave." />
		</t:div>
	</brArq:div>
	<brArq:div id="divBotoes"
		styleClass="radioCustom mt-48 alinhamento-centro">
		<h:commandButton id="btnCadastrarNovaChave"
			action="#{redirecionarCadastramentoChavePixBean.cadastrarNovaChavePix}"
			styleClass="tabindex btn-default-1" title="Cadastrar chave"
			value="Cadastrar chave">
		</h:commandButton>
	</brArq:div>


</h:form>