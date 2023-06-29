<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:saveState value="#{transferenciaForm}" />


<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
			ocultarSaldoIniciarTela();
			validarCampos();
         });
		
		
		function ocultarSaldoIniciarTela(){
			var ocultarSaldo = document.getElementById("dadosAgenciaContaForm:idOcultarSaldo");
			var campoSaldo = document.getElementById("dadosAgenciaContaForm:idSaldo");
			
			if(ocultarSaldo.innerText == "true"){
				campoSaldo.innerText = "-";
			}
		}
		
		function ocultarValores(valor){			
			var campoSaldo = document.getElementById("dadosAgenciaContaForm:idSaldo");
			var saldoModel = document.getElementById("dadosAgenciaContaForm:idSaldoModel");
			var ocultarSaldo = document.getElementById("dadosAgenciaContaForm:idOcultarSaldo");
			
			if(valor.checked == true){
				campoSaldo.innerText = "-";
				ocultarSaldo.innerText = true;
				ocultarSaldoClass();
			} else {
				campoSaldo.innerText = "R$ " + saldoModel.innerText;
				ocultarSaldo.innerText = false;
				mostrarSaldo();
			}
			
		}
		
		function validaCPFCNPJ(campoCPFCNPJ){
		
			removeCaracteresNaonumericos (campoCPFCNPJ);
	 
			compoCPFCNPJ.value = compoCPFCNPJ.value.replace(/(\d{1})(\d{11})$/,"$1-$2");
			compoCPFCNPJ.value = compoCPFCNPJ.value.replace(/(\d{1})(\d{9})$/,"$1.$2");
			compoCPFCNPJ.value = compoCPFCNPJ.value.replace(/(\d{1})(\d{6})$/,"$1.$2");
			compoCPFCNPJ.value = compoCPFCNPJ.value.replace(/(\d{1})(\d{3})$/,"$1.$2");
			
        }
		
		
		function removeCaracteresNaoNumericos(campo){
			
			campo.value = campo.value.replace(/\D/g,"");
			
		} 
		
	
		/*****************************************************
	        * Function: Mascara CPF/CNPJ
	        *****************************************************/
	        function mascaraCpfCnpj(campoCpfCnpj){
	        
	            // Remove tudo o que não é dígito
	            campoCpfCnpj.value=campoCpfCnpj.value.replace(/\D/g,"");
	            
	            if (campoCpfCnpj.value.length <= 11) { // CPF
	        
	                // Coloca um ponto entre o terceiro e o quarto dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/(\d{3})(\d)/,"$1.$2");
	        
	                // Coloca um ponto entre o terceiro e o quarto dígitos
	                // de novo (para o segundo bloco de números)
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/(\d{3})(\d)/,"$1.$2");
	        
	                // Coloca um hífen entre o terceiro e o quarto dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
	        
	            } else { // CNPJ
	        
	                // Coloca ponto entre o segundo e o terceiro dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/^(\d{2})(\d)/,"$1.$2");
	        
	                // Coloca ponto entre o quinto e o sexto dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
	        
	                // Coloca uma barra entre o oitavo e o nono dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/\.(\d{3})(\d)/,".$1/$2");
	        
	                // Coloca um hífen depois do bloco de quatro dígitos
	                campoCpfCnpj.value=campoCpfCnpj.value.replace(/(\d{4})(\d)/,"$1-$2");
	        
	            }
	        
	            return campoCpfCnpj.value;
	        }	

		
	
	    	
			
			function validarCampos(){
				var campoCpfCnpj = document.getElementById("dadosAgenciaContaForm:campoCpfCnpj");
				var agenciaNum = document.getElementById("dadosAgenciaContaForm:agenciaNum");
				var contaNum = document.getElementById("dadosAgenciaContaForm:contaNum");
				var tipoConta = document.getElementById("dadosAgenciaContaForm:idTipoConta");
				
				console.log(tipoConta.value);
				
				var btn = document.getElementById("dadosAgenciaContaForm:btnContinuar");
				
			   if((campoCpfCnpj.value.length == 18 || campoCpfCnpj.value.length == 19 || campoCpfCnpj.value.length == 14)
					&& agenciaNum.value != "" 
					&& contaNum.value != "" 
					&& tipoConta.value != ""
					){
				   btn.disabled = false;	
			   }else{
				   btn.disabled = true;				   
	   			}
			   
			  
			}

	</script>
	<!--  
	<ul class="card clearfix mt-24">
		<li class="cardList card-regular sombra-nivel200">
	-->
</f:verbatim>




<h:form id="dadosAgenciaContaForm">

	<h:outputText id="idOcultarSaldo" style="display:none" value="#{transferenciaForm.horariosSaldoLimiteModel.ocultarSaldo}"></h:outputText>
	<h:outputText id="idSaldoModel" style="display:none" value="#{transferenciaForm.horariosSaldoLimiteModel.saldo}"></h:outputText>
	<h:outputText id="radioSelecionado" style="display:none" value="#{transferenciaForm.chaveSelecionada}"></h:outputText>

	
	<t:div styleClass="row">
	
		<t:div id="filho1" styleClass="col-8">
			<f:verbatim>
				<ul class="card clearfix mr-24 mb-24 mt-24">
					<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD -->
			</f:verbatim>


		<brArq:div styleClass="ml-16 mr-16 " ><!-- inicio campo cpf/cnpj -->
			<ib:campoFormulario tipo="entrada" id="mascaraDocumentoCPFCNPJ"> 
				  	<ib:campoFormularioNome styleClass="rotulo" > 
				 		<brHtml:brOutputText value="#{messageBundle.campo_cpf_cnpj}" /> 
				 	</ib:campoFormularioNome>
				 	<ib:campoFormularioInput>
						<brHtml:brInputText  maxlength="18"  onkeyup="removeCaracteresNaoNumericos(this); mascaraCpfCnpj(this); validarCampos();" id="campoCpfCnpj" 
											 value="#{transferenciaForm.cpfCnpjTransferenciaSemChave}"
											 disabled="#{transferenciaForm.cpfCnpjMesmaTitularidade}">
				 		</brHtml:brInputText> 
				 	</ib:campoFormularioInput> 
		 	</ib:campoFormulario>
		</brArq:div><!-- fim campo cpf/cnpj -->
		
		<brArq:div styleClass="ml-16 mr-16" > <!-- inicio campo agencia -->
		 	<ib:campoFormulario tipo="entrada" id="agenciaForm"> 
				  	<ib:campoFormularioNome styleClass="rotulo" > 
				 		<brHtml:brOutputText value="#{messageBundle.campo_agencia}" /> 
				 	</ib:campoFormularioNome>
				 	<ib:campoFormularioInput>
						<brHtml:brInputText maxlength="4" onkeyup="removeCaracteresNaoNumericos(this);validarCampos(); " id="agenciaNum" 
						value="#{transferenciaForm.agenciaTransferenciaSemChave}" title="" >
				 		</brHtml:brInputText> 
				 	</ib:campoFormularioInput> 
		 	</ib:campoFormulario>
		</brArq:div><!-- fim campo agencia -->
			
			
		<brArq:div styleClass="ml-16 mr-16"  ><!-- inicio campo conta -->
		
		 	<ib:campoFormulario tipo="entrada" id="contaForm"> 
				  	<ib:campoFormularioNome styleClass="rotulo" > 
				 		<brHtml:brOutputText value="#{messageBundle.campo_conta}" /> 
				 	</ib:campoFormularioNome>
				 	<ib:campoFormularioInput>
						<brHtml:brInputText maxlength="7" onkeyup="removeCaracteresNaoNumericos(this);validarCampos();" id="contaNum" 
											value="#{transferenciaForm.contaTransferenciaSemChave}" title="" >
				 		</brHtml:brInputText> 
				 	</ib:campoFormularioInput> 
		 	</ib:campoFormulario>
		 	
		</brArq:div><!-- fim campo conta -->
		
		
			<brArq:div styleClass="ml-16 mr-16" >
			
				<ib:campoFormulario tipo="entrada" id="idTipoContaForm">
					<ib:campoFormularioNome styleClass="rotulo" >
						<brHtml:brOutputText value="Tipo de conta" />
					</ib:campoFormularioNome>
					<ib:campoFormularioInput>
						<brHtml:brSelectOneMenu value="#{transferenciaForm.tipoContaTransferenciaSemChave}" styleClass="tabindex label"
							onchange="validarCampos();" title="Selecione" id="idTipoConta">
							<f:selectItem itemValue = "" itemLabel = "" />
							<f:selectItem itemValue = "1" itemLabel = "Conta corrente" />
							<f:selectItem itemValue = "2" itemLabel = "Conta poupança" />   
						</brHtml:brSelectOneMenu>
					</ib:campoFormularioInput>
				</ib:campoFormulario>   
			  
			</brArq:div>			 
			
			<brArq:div id="divBotoes" styleClass="radioCustom mt-48 ml-16">
				<brArq:div >
					<brHtml:brPanelGroup>
						<h:commandButton disabled="true"
							id="btnContinuar" 
							action="#{transferenciaAgenciaContaBean.continuar}"
							styleClass="tabindex mb-16 btn-default-1" title="Continuar"
							value="Continuar">
						</h:commandButton>
						<brHtml:brCommandLink  title="Voltar"
							styleClass="tabindex ml-32 mb-16 link" immediate="true" action="#{transferenciaAgenciaContaBean.voltar}">
							<brHtml:brOutputText value="Voltar" />
						</brHtml:brCommandLink>
					</brHtml:brPanelGroup>
				</brArq:div>
			</brArq:div>

		</t:div>
		
	
		<t:div styleClass="col-4 horariosLimites">
			<t:div styleClass="row">
				<brHtml:brOutputLabel styleClass="p-pequeno"
					value="Horarios e Limites" />
				<t:div styleClass="box-switch">
					<brHtml:brOutputLabel styleClass="label-switch"
						value="Ocultar Saldo" />
					<brHtml:brSelectBooleanCheckbox id="switch" styleClass="switch"
						title="" forceId="true" onclick="ocultarValores(this);" 
						value="#{transferenciaForm.horariosSaldoLimiteModel.ocultarSaldo}"></brHtml:brSelectBooleanCheckbox>
					<brHtml:brOutputText styleClass="switch" />
				</t:div>
			</t:div>
			<f:verbatim>
				<ul class="card clearfix mb-24 mt-8">
					<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD -->
			</f:verbatim>
			<!-- ####### CARD DEMONSTRADO SOMENTE NA TRANSFERENCIA -->
			<!--informacao saldo conta -->
			<ib:grupoCampos id="infoSaldo" separadorAbaixo="false">
				<%@include file="/WEB-INF/jsp/common/infoSaldoDisponivel.jsp"%>
			</ib:grupoCampos>
			<f:verbatim>
				</li>
				</ul>
				<!-- FIM CARD -->
			</f:verbatim>
		</t:div>
		
	</t:div>
	
</h:form>