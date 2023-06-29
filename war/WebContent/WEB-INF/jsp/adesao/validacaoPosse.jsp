
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<t:jsValueSet name="flagErro" value="#{validacaoPosseBean.flagErro}" />
<t:jsValueSet name="msgErrorCodigoInvalido"	value="#{messageBundle.validacaoPosse_erro_codigo_invalido}" />


<script>
	var $ = jQuery.noConflict();
	jQuery(window).load(
			function() {
				if (flagErro) {
					jQuery('#erro').removeClass('none_i');
				} else {
					jQuery('#erro').addClass('none_i');
				}

				// Remove todos os estilos de erro quando o usuário interage com algum input
				jQuery(document).find("input, select, a, .UICalendarPeriod")
						.live("click", function() {
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
			}
			
	);

	jQuery(document).ready(function() {
		validarFormulario();
     });
	
	
	function removerErros(element) {
		var elementosComErros = jQuery(element).parents(".campos_form").find(".erro_msg strong:not(:empty)");
		jQuery(elementosComErros).filter(":gt(0)").remove();
		jQuery(elementosComErros).html("");
		jQuery(elementosComErros).parents(".campos_form").removeClass("form_erro");
		jQuery('#erro').addClass('none_i');
	}

	function habilitarBtn(conteudo) {

		if (conteudo.value.length == 6) {
			document.getElementById("frmValidacaoPosse:btContinuar").disabled = false;
		} else {
			document.getElementById("frmValidacaoPosse:btContinuar").disabled = true;
		}
	}

	function limparCampoCodigo() {
		document.getElementById("frmValidacaoPosse:codigo").value = '';
	}

	//VALIDACAO DO FORMULARIO
	function validarFormulario() {
		//removerErros();
		var form = jQuery("form[id$=frmValidacaoPosse]")[0];
		result = validateForm(form);
		if (result) {
			result = validarCodigoDigitado() && result;
		}
		return result;
	}

	function validarCodigoDigitado() {

		var valido = true;
		var erroCodigo = document.getElementById("frmValidacaoPosse:erroValidacaoPosse");

		if (erroCodigo.innerText == "true") {
			document.getElementById("frmValidacaoPosse:btContinuar").disabled = false;
			var campoErro = [ "frmValidacaoPosse:codigo" ];
			var messageArray = [ msgErrorCodigoInvalido ];
			exibirErros(campoErro, messageArray);
			valido = false;
		} else {
			document.getElementById("frmValidacaoPosse:btContinuar").disabled = true;

		}
		return valido;
	}
</script>


<t:saveState value="#{adesaoForm}" />

<h:form id="frmValidacaoPosse">

<h:outputText id="erroValidacaoPosse" style="display:none" value="#{validacaoPosseBean.form.codigoErradoPosse}"></h:outputText>

	<brArq:div>
		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
				
		<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{validacaoPosseBean.form.erroEnviarCodValidaPosse}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="Erro ao enviar o código de 6 digitos." />
					<h:outputText escape="true" styleClass="mensagemAlerta" value="Tente reenviar outro código." />
				</t:div>
			</t:div>
		</t:div>

		<brArq:div id="alertSucesso" rendered="#{validacaoPosseBean.exibirBanner}">
			<t:div styleClass="margens_laterais after mb-16">
				<t:div styleClass="box-sucesso after">
					<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
						<h:outputText escape="true" styleClass="tituloAlerta" value="Código reenviado com sucesso" />
						<h:outputText escape="true" styleClass="mensagemAlerta" value="" />
					</t:div>
				</t:div>
			</t:div>
		</brArq:div>

		<brHtml:brOutputText styleClass="legenda" value="Ele ficará ativo por 15 minutos." />
		
		<ib:campoFormulario tipo="entrada" id="inputCodigo"	styleClass="campos_form">
			<ib:campoFormularioMensagemErro id="msgErroValor">
				<t:message for="inputCodigo" />
			</ib:campoFormularioMensagemErro>
			
			<ib:campoFormularioNome styleClass="rotulo">
				<brHtml:brOutputText value="#{messageBundle.label_codigo_seis_digitos}" />
			</ib:campoFormularioNome>
			
			<ib:campoFormularioInput>
				<brHtml:brInputText id="codigo" maxlength="6" title="codigo" value="#{validacaoPosseBean.form.senhaPosse}" onkeyup="habilitarBtn(this)">
				</brHtml:brInputText>
			</ib:campoFormularioInput>

			<t:div styleClass="input-acao">
				<brHtml:brCommandLink id="lnkReenviar"
					action="#{validacaoPosseBean.reenviarCodigo}"
					styleClass="tabindex ml-32 mb-16 link" immediate="true"
					onclick="limparCampoCodigo()">
					<h:outputText value="#{messageBundle.label_reenviar}" />
				</brHtml:brCommandLink>
			</t:div>

		</ib:campoFormulario>

		<brArq:div id="divBotoes">
			<brArq:div styleClass="col-5">
				<brHtml:brPanelGroup>
					<h:commandButton styleClass="tabindex mb-16 btn-default-1"
						id="btContinuar" title="continuar" value="Continuar"
						disabled="true" action="#{validacaoPosseBean.continuar}">
					</h:commandButton>
					<h:commandButton title="voltar" style="border:0"
						styleClass="tabindex ml-32 mb-16 link" value="Voltar"
						action="#{validacaoPosseBean.voltar}">
					</h:commandButton>
				</brHtml:brPanelGroup>
			</brArq:div>
		</brArq:div>
		
	</brArq:div>
	<brArq:validatorScript functionName="validateForm" />
</h:form>






