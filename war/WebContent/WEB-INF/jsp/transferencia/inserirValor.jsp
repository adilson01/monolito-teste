<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<t:saveState value="#{transferenciaForm}" />
<t:jsValueSet name="flagErro" value="#{inserirValorBean.flagErro}" />
<t:jsValueSet name="msgErrorCodigoInvalido" value="#{messageBundle.erro_valor_nao_permitido}" />
	
<style>
	.list_infos li {
		float: none;
		display: inline-block;
	}
	
	.campos_form td.alignRight {
		white-space: normal;
	}
	
	.grupoCampos .titulo_grupo_dados_formulario {
		color: #333;
		font-weight: bold;
		padding: 15px 0;
	}
	
	.linkVejaAqui {
		color: #3b69ff !important;
	}
	
	.linkVejaAqui:hover {
		color: #142EFF !important;
	}

</style>	

<f:verbatim>
	<script language="javascript">
		
		jQuery(document).ready(function() {
			iniciarTela();
			ocultarSaldoIniciarTela();
		});
		
		//funcao card de saldo
		function ocultarSaldoIniciarTela() {
			var ocultarSaldo = document.getElementById("frmInserirValor:idOcultarSaldo");
			var campoSaldo = document.getElementById("frmInserirValor:idSaldo");

			if (ocultarSaldo.innerText == "true") {
				campoSaldo.innerText = "-";
			}
			document.getElementById("frmInserirValor:valor").focus();
		}
		
		
		function iniciarTela(){
			var valor = document.getElementById("frmInserirValor:valor").value;
			var btn = document.getElementById("frmInserirValor:continuar");
			
			if (valor != undefined && valor.trim() != "") {
				btn.disabled = false;
			} else {
				document.getElementById("frmInserirValor:valor").focus();
			}
		}
		
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
			var elementosComErros = jQuery(element).parents(".campos_form").find(".erro_msg strong:not(:empty)");
			jQuery(elementosComErros).filter(":gt(0)").remove();
			jQuery(elementosComErros).html("");
			jQuery(elementosComErros).parents(".campos_form").removeClass("form_erro");
			jQuery('#erro').addClass('none_i');
		}
			
		function mascaraMoeda(valor){
			
			valor.value = valor.value.replace(/\D/g,"")
			valor.value = valor.value.replace(/(\d{1})(\d{14})$/,"$1.$2");
			valor.value = valor.value.replace(/(\d{1})(\d{11})$/,"$1.$2");
			valor.value = valor.value.replace(/(\d{1})(\d{8})$/,"$1.$2");
			valor.value = valor.value.replace(/(\d{1})(\d{5})$/,"$1.$2");
	
			if(valor.value.length < 5 ){
				valor.value = (valor.value/100).toFixed(2) + '';
				valor.value = valor.value.replace(".", ",");
			}
			
			valor.value = valor.value.replace(/(\d{1})(\d{2})$/,"$1,$2");
		}
	
		//VALIDACAO DO FORMULARIO
		function validarFormulario() {
			//removerErros();
			var form = jQuery("form[id$=frmInserirValor]")[0];
			result = validateForm(form);
			if (result) {
				result = validarCodigoDigitado() && result;
			}
			return result;
		}
		
		function validarValorDigitado(valor, limite) {		
			
			var btn = document.getElementById("frmInserirValor:continuar");
			
			var v = valor.value.replace(".","").replace(".","").replace(".","").replace(".","");
			
			if (parseFloat(v.replace(",",".")) > limite) {			
				btn.disabled = true;	
				var campoErro = [ "frmInserirValor:valor" ];			
				var messageArray = [ msgErrorCodigoInvalido ];
				exibirErros(campoErro, messageArray);
				
			}else if(parseFloat(v.replace(",",".")) == 0.00) {
				btn.disabled = true;
			}else{
				btn.disabled = false;	
			}	
		}
		
		function ocultarValores(valor){			
			var campoSaldo = document.getElementById("frmInserirValor:idSaldo");
			var saldoModel = document.getElementById("frmInserirValor:idSaldoModel");
			var ocultarSaldo = document.getElementById("frmInserirValor:idOcultarSaldo");
			
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
		
	</script>
</f:verbatim>

<h:form id="frmInserirValor">

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

			<brArq:div >
				<brHtml:brOutputText 
					styleClass="legenda" 
					value="#{messageBundle.label_inf_inserir_valor} Empresa de Teste LTDA"/>
				
			</brArq:div>
				
			<brArq:div styleClass="mt-24">
	
				<ib:campoFormulario tipo="entrada" id="campoFormulario" styleClass="campos_form ">
				
					<ib:campoFormularioMensagemErro id="msgErroValor">
						<t:message for="campoFormulario" />
					</ib:campoFormularioMensagemErro>
					
					<ib:campoFormularioNome styleClass="rotulo">
						<brHtml:brOutputText value="#{messageBundle.label_inserir_valor}" />
					</ib:campoFormularioNome>
					
					<ib:campoFormularioInput>	
						
						<h:inputText
		                    id="valor"
		                    value="#{transferenciaForm.valor}"
		                    onkeyup="mascaraMoeda(this), validarValorDigitado(this, #{transferenciaForm.horariosSaldoLimiteModel.limiteDiarioFormatacao});"
		                    maxlength="20"
		                    styleClass="inputCelCodPais"/>
						
					</ib:campoFormularioInput>
					<brHtml:brOutputText value="R$"  styleClass="codPais linha"></brHtml:brOutputText>
			
				</ib:campoFormulario>
					
			</brArq:div>
				
			<ib:campoFormulario tipo="entrada" styleClass="mb-24">
				
				<ib:campoFormularioNome styleClass="rotulo">
					<brHtml:brOutputText value="#{messageBundle.label_inserir_descricao}" />
				</ib:campoFormularioNome>
				
				<ib:campoFormularioInput>
					<h:inputText style="margin-bottom-0px"
						id="desc"  
						value="#{transferenciaForm.descricaoValor}"
						maxlength="50" />
 				</ib:campoFormularioInput> 
				 <brHtml:brOutputText styleClass="input-texto-apoio" value="Opcional"></brHtml:brOutputText>
			</ib:campoFormulario>
			
			<brArq:div id="divBotoes" styleClass="radioCustom mt-32">
			
				<brArq:div styleClass="col-8">
					<brHtml:brPanelGroup>
					
						<h:commandButton 
							id="continuar" 
							styleClass="tabindex mb-16 btn-default-1"
							title="continuar" 
							value="Continuar"
							disabled="true"
							action="#{inserirValorBean.continuar}">
						</h:commandButton>
						
						<h:commandButton 
							title="voltar" 
							styleClass="tabindex ml-32 mb-16 link border0" 
							value="Voltar"
							action="#{inserirValorBean.voltar}">
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

