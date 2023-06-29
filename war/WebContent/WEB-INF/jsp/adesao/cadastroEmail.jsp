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
			var cel = document.getElementById("frmCadastroEmail:email").value;
			var btn = document.getElementById("frmCadastroEmail:continuar");
			
			if (cel != undefined && cel.trim() != "") {
				btn.disabled = false;
			} else {
				document.getElementById("frmCadastroEmail:email").focus();
			}
		}
		
		function habilitarBotaoConfirmar(email){
			
			var btn = document.getElementById("frmCadastroEmail:continuar");
			
			if(isEmail(email.value)) {
				btn.disabled = false;					
			} else {
				btn.disabled = true;
			}
			
		}
		
		function isEmail(email) {
			  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			  return regex.test(email);
		}
		
		
		
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<h:form id="frmCadastroEmail">
	<brArq:div>
	
		<f:verbatim >
			<ul class="card clearfix mt-8">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->					
		</f:verbatim>
		
		<ib:campoFormulario tipo="entrada" id="teste1">
			<ib:campoFormularioNome styleClass="rotulo">
				<brHtml:brOutputText value="#{messageBundle.label_email}" />
			</ib:campoFormularioNome>
			<ib:campoFormularioInput>
				<h:inputText 
					id="email"  
					onkeyup="habilitarBotaoConfirmar(this);"
					onchange="habilitarBotaoConfirmar(this);"
					value="#{cadastroEmailBean.form.chave}"
					maxlength="72" />
				</ib:campoFormularioInput> 
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
						action="#{cadastroEmailBean.continuar}">
					</h:commandButton>
					<h:commandButton 
						title="voltar" 
						styleClass=" ml-32 mb-16 link border0" 
						value="Voltar"
						action="#{cadastroEmailBean.voltar}">
					</h:commandButton>
				</brHtml:brPanelGroup>
			</brArq:div>
		</brArq:div>
	</brArq:div>
</h:form>