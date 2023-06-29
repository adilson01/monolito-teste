<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:saveState value="#{adesaoForm}" />

<h:form id="cadastrarChave">

<a4j:jsFunction name="selecionarChave" 
    actionListener="#{cadastrarChaveBean.selecionarNumero}"
    reRender="btnContinuar" immediate="true">
</a4j:jsFunction>	

	<brArq:div id="principal">
		<f:verbatim>
			<ul class="card clearfix mt-8">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>

		<t:div styleClass="row">
			<brHtml:brOutputText styleClass="legenda" value="#{messageBundle.label_info_chave}" />
		</t:div>
		<t:div>
			<brHtml:brOutputText styleClass="legenda" value="#{messageBundle.label_info_chave_2}" />
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16 mt-24" rendered="#{selecaoAgenciaContaBean.form.numeroMaximoChavesExcedido}">
			<t:div styleClass="box-alerta after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="Limite de chaves Pix atingido para a agência e conta selecionada." />
				</t:div>
			</t:div>
		</t:div>

		<brArq:div rendered="#{!selecaoAgenciaContaBean.form.numeroMaximoChavesExcedido}">
				<brArq:div styleClass=" mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarChave();" value="">
							<ib:radioButton id="celular" name="itemDois" itemValue="1" 
								overrideName="true" value="#{cadastrarChaveBean.form.chaveSelecionada}" styleClass="tabindex mt-32"
								itemLabel="#{messageBundle.chave_celular}" />
						</brHtml:brOutputLabel>
				</brArq:div>
				
				<brArq:div styleClass="mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarChave();" value="">
							<ib:radioButton id="email" name="itemDois" itemValue="2"
								overrideName="true" value="#{cadastrarChaveBean.form.chaveSelecionada}" styleClass="tabindex mt-32"
								itemLabel="#{messageBundle.chave_email}" />
						</brHtml:brOutputLabel>
				</brArq:div>
				
				<brArq:div styleClass="mt-32" rendered="#{!cadastrarChaveBean.form.ocultarTipoChaveCNPJ}">
						<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarChave();" value="">
							<ib:radioButton  id="radioCNPJ" name="itemDois" itemValue="3"
								overrideName="true" value="#{cadastrarChaveBean.form.chaveSelecionada}" styleClass="tabindex mt-32"
								itemLabel="#{messageBundle.chave_cnpj}: #{cadastrarChaveBean.form.empresa.cnpj.cnpjFormatado}" />
						</brHtml:brOutputLabel>
				</brArq:div>
				
				<brArq:div styleClass="mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarChave();" value="">
							<ib:radioButton id="evp" name="itemDois" itemValue="4"
								overrideName="true" value="#{cadastrarChaveBean.form.chaveSelecionada}" styleClass="tabindex mt-32"
								itemLabel="#{messageBundle.chave_aleatoria}" />
						</brHtml:brOutputLabel>
				</brArq:div>
				
		</brArq:div>
		
		<brArq:div id="divBotoes" styleClass="radioCustom mt-32">
			<brArq:div>
				<t:div styleClass="col-12">
					<h:commandButton id="btnContinuar"
						action="#{cadastrarChaveBean.continuar}"
						disabled="#{cadastrarChaveBean.botaoContinuar}"
						styleClass="tabindex mb-16 btn-default-1" title="Continuar"
						value="Continuar">
					</h:commandButton>
					<h:commandButton 
						title="voltar" style="border:0"
						styleClass="tabindex ml-32 mb-16 link" 
						value="Voltar"
						action="#{cadastrarChaveBean.voltar}">
					</h:commandButton>
				</t:div>
			</brArq:div>

		</brArq:div>

	</brArq:div>
</h:form>