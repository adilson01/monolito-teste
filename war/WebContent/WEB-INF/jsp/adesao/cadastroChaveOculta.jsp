<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:verbatim>
	<script language="javascript">
		
		jQuery(document).ready(function() {
			
		});
		
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<h:form id="frmCadastroChaveOculta">

	<brArq:div>
	
		<f:verbatim >
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->					
		</f:verbatim>
		
			<brHtml:brOutputText styleClass="legenda" value="#{messageBundle.label_inf_cadastrar_chave_oculta}" />
				
			<ib:campoFormulario tipo="entrada" id="idChaveOculta" styleClass="mt-16 mb-24">
				
				<ib:campoFormularioNome styleClass="rotulo">
					<brHtml:brOutputText value="#{messageBundle.label_apelido}" />
				</ib:campoFormularioNome>
				
				<ib:campoFormularioInput>
					<h:inputText style="margin-bottom-0px"
						id="chaveOc"  
						value="#{cadastroChaveOcultaBean.form.chave}"
						maxlength="72" />
 				</ib:campoFormularioInput> 
				 <brHtml:brOutputText styleClass="input-texto-apoio" value="Opcional"></brHtml:brOutputText>
			</ib:campoFormulario>
			
		<brArq:div id="divBotoes">

			<brArq:div styleClass="col-5">
				<brHtml:brPanelGroup>
					
					<h:commandButton 
						id="continuar" 
						styleClass="tabindex mb-16 btn-default-1"
						title="continuar" 
						value="Continuar"
						action="#{cadastroChaveOcultaBean.continuar}">
					</h:commandButton>
					
					<h:commandButton 
						title="voltar" 
						style="border:0"
						styleClass="tabindex ml-32 mb-16 link" 
						value="Voltar"
						action="#{cadastroChaveOcultaBean.voltar}">
					</h:commandButton>
				
				</brHtml:brPanelGroup>
			</brArq:div>

		</brArq:div>

	</brArq:div>
	
</h:form>