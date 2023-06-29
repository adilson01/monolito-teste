<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<f:verbatim>
	<script language="javascript">
		
		jQuery(document).ready(function() {
			iniciarTela();
		});
		
		function iniciarTela(){
			var cel = document.getElementById("frmCadastroCelular:celular").value;
			var btn = document.getElementById("frmCadastroCelular:continuar");
			
			if (cel != undefined && cel.trim() != "") {
				btn.disabled = false;
			} else {
				document.getElementById("frmCadastroCelular:celular").focus();
			}
		}
			
		function mascaraCelular(celular){
					
			celular.value = celular.value.replace(/\D/g,""); 
			celular.value = celular.value.replace(/^(\d{2})(\d)/g,"$1 $2");
			celular.value = celular.value.replace(/(\d{1})(\d{4})$/,"$1 $2");
			
		}
		
		function habilitarBotaoConfirmar(celular){
	
			var btn = document.getElementById("frmCadastroCelular:continuar");
			
			if(celular.value.length == 13 ) {
				btn.disabled = false;					
			} else {
				btn.disabled = true;
			}
			
		}
		
		function atualizarMascara(){
			document.getElementById("frmCadastroCelular:celular").value = document.getElementById("frmCadastroCelular:valorFomatado").value;
		}
		
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<h:form id="frmCadastroCelular">

<h:inputText id="valorFomatado" style="display:none" value="#{cadastroCelularBean.form.chave}"></h:inputText>

<a4j:jsFunction name="mascara"
    action="#{cadastroCelularBean.mascaraCelular}"
    immediate="true" reRender="valorFomatado">
</a4j:jsFunction>   

	<brArq:div>
	
	
		<f:verbatim >
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->					
		</f:verbatim>
		
		<ib:campoFormulario tipo="entrada" id="teste1">
			
			<ib:campoFormularioNome styleClass="rotulo">
				<brHtml:brOutputText value="#{messageBundle.label_celular}" />
			</ib:campoFormularioNome>
			
			<ib:campoFormularioInput>	
				
				<h:inputText
                    id="celular"
                    value="#{cadastroCelularBean.form.chave}"
                    onkeyup="mascaraCelular(this), habilitarBotaoConfirmar(this)"
                    maxlength="13"
                    styleClass="inputCelCodPais"/>
			</ib:campoFormularioInput>
			
			<t:div styleClass="input-acao">
				
				<brHtml:brCommandLink  
					title="#{messageBundle.label_trocar_pais}"
					id="trocarPais"		
					action="#{cadastroCelularBean.trocarPais}"			
					styleClass="tabindex ml-32 mb-16 link "
					target="modal_infra_estrutura">
		
					<h:outputText value="#{messageBundle.label_trocar_pais}" />
				</brHtml:brCommandLink>
			</t:div>
			<brHtml:brOutputText value="+#{cadastroCelularBean.form.ddi}" styleClass="codPais"></brHtml:brOutputText>
			
		</ib:campoFormulario>

		<brArq:div id="divBotoes">
		
			<brArq:div styleClass="col-5">
				<brHtml:brPanelGroup>
					
					<h:commandButton 
						id="continuar" 
						styleClass="tabindex mb-16 btn-default-1"
						title="continuar" 
						value="Continuar"
						disabled="true"
						action="#{cadastroCelularBean.continuar}">
					</h:commandButton>
					
					<h:commandButton 
						title="voltar" 
						styleClass="tabindex ml-32 mb-16 link border0" 
						value="Voltar"
						action="#{cadastroCelularBean.voltar}">
					</h:commandButton>
				
				</brHtml:brPanelGroup>
			</brArq:div>

		</brArq:div>

	</brArq:div>
	
</h:form>

