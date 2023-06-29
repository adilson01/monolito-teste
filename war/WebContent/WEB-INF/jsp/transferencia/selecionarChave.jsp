<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://richfaces.org/rich" prefix = "rich" %>


<t:jsValueSet name="flagErro" value="#{selecionarChaveBean.flagErro}" />
<t:jsValueSet name="msgErrorCPFCNPJInvalido"
	value="#{messageBundle.label_selecionar_chave_erro_cpf_cnpj_invalido}" />


<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
			iniciarChaveSelecionada();
			ocultarSaldoIniciarTela();			
         });
		
		var $ = jQuery.noConflict();
		jQuery(window).load(
				function() {
					if (flagErro) {
						jQuery('#erro').removeClass('none_i');
					} else {
						jQuery('#erro').addClass('none_i');
					}

					// Remove todos os estilos de erro quando o usuário interage com algum input
					jQuery(document)
						.find("input, select, a, .UICalendarPeriod").live(
								"click", function() {
									removerErros(jQuery(this));
								});	
					
						// volta ao topo da página
						jQuery(document).ready(function() {
						jQuery('.ScrollTop').click(function() {
							jQuery('html, body').animate({
									scrollTop : 0
								}, 0);
								return false;
							});
						});
				});
		
				function removerErros(element) {
					var elementosComErros = jQuery(element).parents(".campos_form").find(
							".erro_msg strong:not(:empty)");
					jQuery(elementosComErros).filter(":gt(0)").remove();
					jQuery(elementosComErros).html("");
					jQuery(elementosComErros).parents(".campos_form").removeClass(
							"form_erro");
					jQuery('#erro').addClass('none_i');					
				}
					
				function validarFormulario() {
					//removerErros();
					var form = jQuery("form[id$=selecionarChaveForm]")[0];
					result = validateForm(form);
					if (result) {
						result = verificarCPF() && result;
					}
					return result;
				} 
								
			
			function ocultarSaldoIniciarTela() {
				var ocultarSaldo = document.getElementById("selecionarChaveForm:idOcultarSaldo");
				var campoSaldo = document.getElementById("selecionarChaveForm:idSaldo");

				if (ocultarSaldo.innerText == "true") {
					campoSaldo.innerText = "-";
				}

			}

			function ocultarValores(valor) {
				var campoSaldo = document.getElementById("selecionarChaveForm:idSaldo");
				var saldoModel = document.getElementById("selecionarChaveForm:idSaldoModel");
				var ocultarSaldo = document.getElementById("selecionarChaveForm:idOcultarSaldo");

				if (valor.checked == true) {
					campoSaldo.innerText = "-";
					ocultarSaldo.innerText = true;
				} else {
					campoSaldo.innerText = "R$ " + saldoModel.innerText;
					ocultarSaldo.innerText = false;
				}

			}

		
		
		function mascaraCpfCnpj(v){				  
			  v = document
				.getElementById("selecionarChaveForm:cpfC");
			  
	            // Remove tudo o que não é dígito
	            v.value=v.value.replace(/\D/g,"");
	            
	            if (v.value.length <= 11) { // CPF
	        
	                // Coloca um ponto entre o terceiro e o quarto dígitos
	                v.value=v.value.replace(/(\d{3})(\d)/,"$1.$2");
	        
	                // Coloca um ponto entre o terceiro e o quarto dígitos
	                // de novo (para o segundo bloco de números)
	                v.value=v.value.replace(/(\d{3})(\d)/,"$1.$2");
	        
	                // Coloca um hífen entre o terceiro e o quarto dígitos
	                v.value=v.value.replace(/(\d{3})(\d{1,2})$/,"$1-$2");   	                
	              	                
	            } else if(v.value.length <= 18) { // CNPJ
	        
	                // Coloca ponto entre o segundo e o terceiro dígitos
	                v.value=v.value.replace(/^(\d{2})(\d)/,"$1.$2");
	        
	                // Coloca ponto entre o quinto e o sexto dígitos
	                v.value=v.value.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
	        
	                // Coloca uma barra entre o oitavo e o nono dígitos
	                v.value=v.value.replace(/\.(\d{3})(\d)/,".$1/$2");
	        
	                // Coloca um hífen depois do bloco de quatro dígitos
	                v.value=v.value.replace(/(\d{4})(\d)/,"$1-$2");                 
	            }            
	            return v.value;	           
	        }		  
		
			//Função de definissão do delay para chamar a função que fica escutando quando para de digitar
			function DelayedSubmission() {
			    var date = new Date();
			    initial_time = date.getTime();
			    if (typeof setInverval_Variable == 'undefined') {
			            setInverval_Variable = setInterval(DelayedSubmission_Check, 50);
			    } 
			}
			//Fução acionada quando o usuário terminar de digitar o CPF ou o CNPJ 
			function DelayedSubmission_Check() {
				var cpfC = document.getElementById("selecionarChaveForm:cpfC");
			    var date = new Date();
			    check_time = date.getTime();
			    var limit_ms=check_time-initial_time;
			    if (limit_ms > 1000) { //Change value in milliseconds
			    	verificarCPF(cpfC);		       
			        clearInterval(setInverval_Variable);
			        delete setInverval_Variable;
			    }
			}		
			
			// validador CPF e desabilita botão		
			function verificarCPF(cpfC) {		
				
				var btn = document.getElementById("selecionarChaveForm:btnContinuar");
	
				var cpf = cpfC.value;
				
				if(cpf == ''){
					btn.disabled=true;				
				}
	
				cpf = cpf.replace(/[^\d]+/g, '');
				
				if (cpfC.value.length <= 14) { // CPF
				
				if (cpf == '')
					return false;
				if (cpf.length != 11 || cpf == "00000000000"
						|| cpf == "11111111111" || cpf == "22222222222"
						|| cpf == "33333333333" || cpf == "44444444444"
						|| cpf == "55555555555" || cpf == "66666666666"
						|| cpf == "77777777777" || cpf == "88888888888"
						|| cpf == "99999999999")
					return false;
				add = 0;
				for (i = 0; i < 9; i++)
					add += parseInt(cpf.charAt(i)) * (10 - i);
				rev = 11 - (add % 11);
				if (rev == 10 || rev == 11)
					rev = 0;
				if (rev != parseInt(cpf.charAt(9)) == false){
					
				add = 0;
				for (i = 0; i < 10; i++)
					add += parseInt(cpf.charAt(i)) * (11 - i);
				rev = 11 - (add % 11);
				if (rev == 10 || rev == 11)
					rev = 0;
				if (rev != parseInt(cpf.charAt(10))) 
					return false;	
					document.getElementById("selecionarChaveForm:btnContinuar").disabled = false;	
				return true;				
				}else{				
					var valido = true;	
					var campoErro = [ "selecionarChaveForm:cpfC" ];			
					var messageArray = [ msgErrorCPFCNPJInvalido ];
					exibirErros(campoErro, messageArray);				
					document.getElementById("selecionarChaveForm:btnContinuar").disabled = true;	
					valido = false;    		
				return false;					
				}
			}else if(cpfC.value.length <= 18) { // CNPJ
				var cnpj = cpfC.value;
	
				cnpj = cnpj.replace(/[^\d]+/g, '');
	
				if (cnpj == '')
					return false;
	
				if (cnpj.length != 14)
					return false;
	
				// Elimina CNPJs invalidos conhecidos
				if (cnpj == "00000000000000" || cnpj == "11111111111111"
						|| cnpj == "22222222222222" || cnpj == "33333333333333"
						|| cnpj == "44444444444444" || cnpj == "55555555555555"
						|| cnpj == "66666666666666" || cnpj == "77777777777777"
						|| cnpj == "88888888888888" || cnpj == "99999999999999")
					return false;
	
				// Valida DVs
				tamanho = cnpj.length - 2
				numeros = cnpj.substring(0, tamanho);
				digitos = cnpj.substring(tamanho);
				soma = 0;
				pos = tamanho - 7;
				for (i = tamanho; i >= 1; i--) {
					soma += numeros.charAt(tamanho - i) * pos--;
					if (pos < 2)
						pos = 9;
				}
				resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
				if (resultado != digitos.charAt(0)==false){
					
					tamanho = tamanho + 1;
					numeros = cnpj.substring(0, tamanho);
					soma = 0;
					pos = tamanho - 7;
					for (i = tamanho; i >= 1; i--) {
						soma += numeros.charAt(tamanho - i) * pos--;
						if (pos < 2)
							pos = 9;
					}
					resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
					if (resultado != digitos.charAt(1))
						return false;
					document.getElementById("selecionarChaveForm:btnContinuar").disabled = false;	
					return true;
				}else{
					var valido = true;	
					var campoErro = [ "selecionarChaveForm:cpfC" ];			
					var messageArray = [ msgErrorCPFCNPJInvalido ];
					exibirErros(campoErro, messageArray);				
					document.getElementById("selecionarChaveForm:btnContinuar").disabled = true;	
					valido = false;
					
					return false;
				}
						
			}
				
		}
		
		function iniciarChaveSelecionada() {
			
			 var radioSelecionado =	document.getElementById("selecionarChaveForm:radioSelecionado")
			      						
			if (radioSelecionado.innerText == "1") {
				jQuery("#selecionarChaveForm\\:exibirNumCelular").removeClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirCNPJ").addClass('none_i');			
				jQuery("#selecionarChaveForm\\:exibirEmail").addClass('none_i');					
				jQuery("#selecionarChaveForm\\:exibirAgConta").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				
				jQuery("#selecionarChaveForm\\:btnContinuar").attr("disabled", false);
			} else if (radioSelecionado.innerText == "2") {
				jQuery("#selecionarChaveForm\\:exibirEmail").removeClass('none_i');	
				jQuery("#selecionarChaveForm\\:exibirNumCelular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirCNPJ").addClass('none_i');									
				jQuery("#selecionarChaveForm\\:exibirAgConta").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				
				jQuery("#selecionarChaveForm\\:btnContinuar").attr("disabled", false);
			} else if (radioSelecionado.innerText == "3") {
				jQuery("#selecionarChaveForm\\:exibirCNPJ").removeClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirNumCelular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirEmail").addClass('none_i');					
				jQuery("#selecionarChaveForm\\:exibirAgConta").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				
				jQuery("#selecionarChaveForm\\:btnContinuar").attr("disabled", false);
			} else if (radioSelecionado.innerText == "4") {
				jQuery("#selecionarChaveForm\\:exibirAgConta").removeClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirNumCelular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirEmail").addClass('none_i');
				jQuery("#selecionarChaveForm\\:exibirCNPJ").addClass('none_i');				
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				
				 var banco =jQuery("#selecionarChaveForm\\:selectedBanco")				 	
					         
					var tit1 = document.getElementById("selecionarChaveForm:titularidade").checked;
					var tit2 = document.getElementById("selecionarChaveForm:titularidade2").checked;
					
					if (banco!= null && banco != '') {
						jQuery("#selecionarChaveForm\\:titular").removeClass('none_i');
						
						if (tit1 || tit2) {
							jQuery("#selecionarChaveForm\\:btnContinuar").attr("disabled", false);		
						}
					}
				
			} else if (radioSelecionado.innerText == "") {
				jQuery("#selecionarChaveForm\\:exibirNumCelular").addClass('none_i');	
				jQuery("#selecionarChaveForm\\:exibirCNPJ").addClass('none_i');			
				jQuery("#selecionarChaveForm\\:exibirEmail").addClass('none_i');					
				jQuery("#selecionarChaveForm\\:exibirAgConta").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				
				jQuery("#selecionarChaveForm\\:btnContinuar").attr("disabled", true);
			}
		}
		
		function trocarChaveSelecionada(param) {

			var divCelular = jQuery("#selecionarChaveForm\\:exibirNumCelular");
			var divEmail = jQuery("#selecionarChaveForm\\:exibirEmail");
			var divCNPJ = jQuery("#selecionarChaveForm\\:exibirCNPJ");
			var divAgConta = jQuery("#selecionarChaveForm\\:exibirAgConta");

			if (param.id == 'selecionarChaveForm:radioCelular') {
				divCelular.removeClass('none_i');	
				divAgConta.addClass('none_i');
				divCNPJ.addClass('none_i');
				divEmail.addClass('none_i');
				limparCampo(param);
			}
			if (param.id == 'selecionarChaveForm:radioEmail') {
				divEmail.removeClass('none_i');	
				divAgConta.addClass('none_i');
				divCNPJ.addClass('none_i');
				divCelular.addClass('none_i');
				limparCampo(param);

			}
			if (param.id == 'selecionarChaveForm:radioCPFCNPJ') {
				divCNPJ.removeClass('none_i');	
				divAgConta.addClass('none_i');
				divEmail.addClass('none_i');
				divCelular.addClass('none_i');
				limparCampo(param);
			}
			if (param.id == 'selecionarChaveForm:radioAgConta') {
				divAgConta.removeClass('none_i');	
				divCNPJ.addClass('none_i');
				divEmail.addClass('none_i');
				divCelular.addClass('none_i');
				limparCampo(param);
			}
		}
		
		function limparCampo(param) {
			var btn = document.getElementById("selecionarChaveForm:btnContinuar");
			if (param.id == 'selecionarChaveForm:radioCelular') {
				btn.disabled = true;	
				jQuery("#selecionarChaveForm\\:em").val('');
				jQuery("#selecionarChaveForm\\:cpfC").val('');			
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titularidade").prop('checked', false)
				jQuery("#selecionarChaveForm\\:titularidade2").prop('checked', false)
				jQuery("#selecionarChaveForm\\:selectedBanco").val('');
				    	
			}
			
			if (param.id == 'selecionarChaveForm:radioEmail') {
				btn.disabled = true;
				jQuery("#selecionarChaveForm\\:celPhone").val('');
				jQuery("#selecionarChaveForm\\:cpfC").val('');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titularidade").prop('checked', false)
				jQuery("#selecionarChaveForm\\:titularidade2").prop('checked', false)
				jQuery("#selecionarChaveForm\\:selectedBanco").val('');
			}

			if (param.id == 'selecionarChaveForm:radioCPFCNPJ') {
				btn.disabled = true;
				jQuery("#selecionarChaveForm\\:celPhone").val('');
				jQuery("#selecionarChaveForm\\:em").val('');
				jQuery("#selecionarChaveForm\\:titular").addClass('none_i');
				jQuery("#selecionarChaveForm\\:titularidade").prop('checked', false)
				jQuery("#selecionarChaveForm\\:titularidade2").prop('checked', false)
				jQuery("#selecionarChaveForm\\:selectedBanco").val('');
			}

			if (param.id == 'selecionarChaveForm:radioAgConta') {
				btn.disabled = true;
				jQuery("#selecionarChaveForm\\:celPhone").val('');
				jQuery("#selecionarChaveForm\\:em").val('');
				jQuery("#selecionarChaveForm\\:cpfC").val('');
				jQuery("#selecionarChaveForm\\:titularidade").prop('checked', false)
				jQuery("#selecionarChaveForm\\:titularidade2").prop('checked', false)
				jQuery("#selecionarChaveForm\\:selectedBanco").val('');
			}
		}		
		
	function habilitarBotaoContinuar(em){			
			var btn = document.getElementById("selecionarChaveForm:btnContinuar");
			
			if(isEmail(em.value)) {
				btn.disabled = false;					
			} else {
				btn.disabled = true;
			}
			
		}
		
		function isEmail(email) {
			  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			  return regex.test(email);
		}			

		function mascaraCelular(celPhone) {
			celPhone.value = celPhone.value.replace(/\D/g, "");
			celPhone.value = celPhone.value.replace(/^(\d{2})(\d)/g, "$1 $2");
			celPhone.value = celPhone.value.replace(/(\d{1})(\d{4})$/, "$1 $2");
		}
		
		function habilitarBtnContinuar(celPhone) {
			var btn = document.getElementById("selecionarChaveForm:btnContinuar");
			if (celPhone.value.length == 13) {
				btn.disabled = false;
			} else {
				btn.disabled = true;
			}
		}		
		
		function habilitarBtn(){			
			 document.getElementById("selecionarChaveForm:btnContinuar").disabled = false;
		}	
		
	</script>
</f:verbatim>

<t:saveState value="#{transferenciaForm}" />

<h:form id="selecionarChaveForm">
	<h:messages showDetail="true"></h:messages>	
	
	<h:outputText id="idOcultarSaldo" style="display:none" value="#{transferenciaForm.horariosSaldoLimiteModel.ocultarSaldo}"></h:outputText>
	<h:outputText id="idSaldoModel" style="display:none" value="#{transferenciaForm.horariosSaldoLimiteModel.saldo}"></h:outputText>
	<h:outputText id="radioSelecionado" style="display:none" value="#{transferenciaForm.chaveSelecionada}"></h:outputText>

	<a4j:jsFunction name="showTitularidade"
		actionListener="#{selecionarChaveBean.exibirTitularidade}"
		reRender="titular" immediate="true">
	</a4j:jsFunction>
	
	<a4j:jsFunction name="getBancoSelecionado"
		action="#{selecionarChaveBean.bancoSelecionado}"/>
	

	<t:div styleClass="row">
		<t:div id="filho1" styleClass="col-8">
				<f:verbatim>
					<ul class="card clearfix mr-24 mb-24 mt-24">
						<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD -->
				</f:verbatim>
		
				<brHtml:brOutputText styleClass="legenda"
					value="#{label_titulo_selecionar_chave}" />
		
				<brArq:div>
					<!--  -->
					<brHtml:brOutputText styleClass="title-pequeno ml-8"
						value="Além das chaves, você pode usar dados de agência e conta" />
		
					<brArq:div styleClass=" mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " id="radioCelular"
							onclick="trocarChaveSelecionada(this);" value="">
							<ib:radioButton id="celular" name="itemDois" itemValue="1"
								overrideName="true"
								value="#{selecionarChaveBean.transform.chaveSelecionada}"
								styleClass="tabindex mt-32" itemLabel="Celular" />
						</brHtml:brOutputLabel>
					</brArq:div>
		
					<!--  -->
					<brArq:div styleClass="mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " id="radioEmail"
							onclick="trocarChaveSelecionada(this);" value="">
							<ib:radioButton id="email" name="itemDois" itemValue="2"
								overrideName="true"
								value="#{selecionarChaveBean.transform.chaveSelecionada}"
								styleClass="tabindex mt-32" itemLabel="E-mail" />
						</brHtml:brOutputLabel>
					</brArq:div>
		
					<!--  -->
					<brArq:div styleClass="mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " id="radioCPFCNPJ"
							onclick="trocarChaveSelecionada(this);" value="">
							<ib:radioButton id="radioCNPJ" name="itemDois" itemValue="3"
								overrideName="true"
								value="#{selecionarChaveBean.transform.chaveSelecionada}"
								styleClass="tabindex mt-32"
								itemLabel="CPF ou CNPJ" />
						</brHtml:brOutputLabel>
					</brArq:div>
		
					<brArq:div styleClass=" mt-32">
						<brHtml:brOutputLabel styleClass="radioCustom " id="radioAgConta"
							onclick="trocarChaveSelecionada(this);" value="">
							<ib:radioButton id="agenciaConta" name="itemDois" itemValue="4"
								overrideName="true"
								value="#{selecionarChaveBean.transform.chaveSelecionada}"
								styleClass="tabindex mt-32" itemLabel="Agência e conta" />
						</brHtml:brOutputLabel>
					</brArq:div>
		
						<brArq:div styleClass="mt-32 ml-8" id="exibirNumCelular">
							<ib:campoFormulario tipo="entrada" id="campoFormulario"
								styleClass="campos_form ">
								<brHtml:brOutputText styleClass="p-pequeno"
									value="E qual é o número do celular de quem vai receber?" />
								<ib:campoFormularioNome styleClass="rotulo">
									<brHtml:brOutputText value="#{messageBundle.label_celular}" />
								</ib:campoFormularioNome>
								<ib:campoFormularioInput>
									<h:inputText id="celPhone" styleClass="inputCelCodPais"
										value="#{selecionarChaveBean.transform.chave}" maxlength="13"
										onkeyup="mascaraCelular(this);habilitarBtnContinuar(this)"></h:inputText>
								</ib:campoFormularioInput>
		
								<t:div styleClass="input-acao">
									<brHtml:brCommandLink title="#{messageBundle.label_trocar_pais}"
										id="trocarPais" action="#{selecionarChaveBean.trocarPais}"
										styleClass="tabindex ml-32 mb-16 link "
										target="modal_infra_estrutura">
		
										<h:outputText value="#{messageBundle.label_trocar_pais}" />
									</brHtml:brCommandLink>
								</t:div>
								<brHtml:brOutputText value="+#{selecionarChaveBean.transform.ddi}"
									styleClass="codPais"></brHtml:brOutputText>
							</ib:campoFormulario>
						</brArq:div>
		
		
						<t:div id="exibirEmail" styleClass="mt-32 ml-8">
						<brArq:div>
							<brHtml:brOutputText styleClass="p-pequeno" value="E qual é o email de quem vai receber?" />
							<ib:campoFormulario tipo="entrada" id="Email"
								styleClass="campos_form">
								<ib:campoFormularioNome styleClass="rotulo">
									<brHtml:brOutputText value="#{messageBundle.label_email}" />
								</ib:campoFormularioNome>
								<ib:campoFormularioInput>
									<brHtml:brInputText id="em" maxlength="72" title="email"
										value="#{selecionarChaveBean.transform.chaveEmail}"
										onkeyup="habilitarBotaoContinuar(this);" onchange="habilitarBotaoContinuar(this);">
									</brHtml:brInputText>
								</ib:campoFormularioInput>
							</ib:campoFormulario>
						</brArq:div>
					</t:div>
		
					<t:div id="exibirCNPJ" styleClass="mt-32 ml-8">
						<brArq:div >
							<brHtml:brOutputText styleClass="p-pequeno" value="E qual é o CPF OU CNPJ de quem vai receber?" />
							<ib:campoFormulario tipo="entrada" id="cpfCn"
								styleClass="campos_form">
										<ib:campoFormularioMensagemErro id="msgErroValor">
											<t:message for="cpfCn" />
										</ib:campoFormularioMensagemErro>	
								<ib:campoFormularioNome styleClass="rotulo">
									<brHtml:brOutputText value="#{messageBundle.label_cpf_cnpj}" />
								</ib:campoFormularioNome>
								<ib:campoFormularioInput>
									<brHtml:brInputText id="cpfC" maxlength="18" title="cpfCnpj"
										value="#{selecionarChaveBean.transform.chaveCpfCnpj}"
										onkeypress="mascaraCpfCnpj(this);"								
										onkeyup="DelayedSubmission(this);">
									</brHtml:brInputText>
								</ib:campoFormularioInput>
							</ib:campoFormulario>
						</brArq:div>
					</t:div>
		
					<brArq:div styleClass="mt-32 ml-8" id="exibirAgConta">
							<ib:campoFormulario tipo="entrada" 
								styleClass="campos_form ">					
								<brHtml:brOutputText styleClass="p-pequeno"
									value="E qual é o banco de quem vai receber?" />					
									<ib:campoFormularioNome  styleClass="rotulo">
										<brHtml:brOutputText value="#{messageBundle.label_ag_conta}"/>
									</ib:campoFormularioNome>
									<ib:campoFormularioInput>
									<h:inputText id="selectedBanco" styleClass="p-pequeno"
										value="#{selecionarChaveBean.transform.bancoSelecionado}" readonly="true"/> 
								</ib:campoFormularioInput> 
		
								<t:div styleClass="input-acao">
								
									<brHtml:brCommandLink title="#{messageBundle.label_selecionar_banco}"
										id="selectBanco" action="#{selecionarChaveBean.selecionarBanco}"
										styleClass="tabindex ml-32 link "
										target="modal_infra_estrutura">
		
										<h:outputText value="#{messageBundle.label_selecionar_banco}"/>
									</brHtml:brCommandLink>
								</t:div>						
								</ib:campoFormulario> 
					</brArq:div>	
		
						<t:div id="titular" styleClass="ml-8 mb-16">
							<brArq:div id="mesmaTitularidade">
								<brHtml:brOutputText styleClass="p-pequeno" value="Titularidade" />
								<t:div styleClass="mb-8">
									<brHtml:brOutputLabel styleClass="btn-radio mr-24" onclick="habilitarBtn();">
										<ib:radioButton overrideName="true" name="tipoTitularidade" itemValue="1" 
											id="titularidade" value="#{selecionarChaveBean.transform.titularidade}"
											title="#{messageBundle.label_selecionar_chave_mesma_titularidade}"
											styleClass="tabindex frmRadio mr0"  />
										<brHtml:brOutputLabel
											value="#{messageBundle.label_selecionar_chave_mesma_titularidade}" for="titularidade"/>
									</brHtml:brOutputLabel>
		
									<brHtml:brOutputLabel styleClass="btn-radio" onclick="habilitarBtn();">
										<ib:radioButton overrideName="true" name="tipoTitularidade" itemValue="2"
											id="titularidade2" value="#{selecionarChaveBean.transform.titularidade}"
											title="#{messageBundle.label_selecionar_chave_outra_titularidade}"
											styleClass="tabindex frmRadio mr0" />
										<brHtml:brOutputLabel
											value="#{messageBundle.label_selecionar_chave_outra_titularidade}" for="titularidade2" />
									</brHtml:brOutputLabel>
								</t:div>
							</brArq:div>
						</t:div>
					</brArq:div>
				
		        		
					<brArq:div id="divBotoes" styleClass="radioCustom mt-32 ml-8">
					<brArq:div styleClass="col-7">
						<brHtml:brPanelGroup>
							<h:commandButton id="btnContinuar"
								action="#{selecionarChaveBean.continuar}"
								disabled="true"
								styleClass="tabindex mb-16 btn-default-1" title="Continuar"
								value="Continuar">
							</h:commandButton>		
							<h:commandButton title="voltar" style="border:0"
								styleClass="tabindex ml-32 mb-16 link" value="Voltar"
								action="#{selecionarChaveBean.voltar}">
							</h:commandButton>
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
<brArq:validatorScript functionName="validateForm" />
</h:form>