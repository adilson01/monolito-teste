<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:saveState value="#{transferenciaForm}" />

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
			mostrarDescricaoEmpresa();
			ocultarSaldoIniciarTela();
		});

		function mostrarDescricaoEmpresa() {
			var empresa = document
					.getElementById("selecaoAgenciaContaForm:empresaCmb");

			if (empresa == null) {
				jQuery('#selecaoAgenciaContaForm\\:descricaoEmpresa')
						.removeClass('none_i');
			} else {
				jQuery('#selecaoAgenciaContaForm\\:descricaoEmpresa').addClass(
						'none_i');
			}
		}

		function ocultarSaldoIniciarTela() {
			var ocultarSaldo = document.getElementById("selecaoAgenciaContaForm:idOcultarSaldo");
			var campoSaldo = document.getElementById("selecaoAgenciaContaForm:idSaldo");

			if (ocultarSaldo.innerText == "true") {
				campoSaldo.innerText = "-";
			}

		}

		function ocultarValores(valor) {
			var campoSaldo = document.getElementById("selecaoAgenciaContaForm:idSaldo");
			var saldoModel = document.getElementById("selecaoAgenciaContaForm:idSaldoModel");
			var ocultarSaldo = document.getElementById("selecaoAgenciaContaForm:idOcultarSaldo");

			if (valor.checked == true) {
				campoSaldo.innerText = "-";
				ocultarSaldo.innerText = true;
			} else {
				campoSaldo.innerText = "R$ " + saldoModel.innerText;
				ocultarSaldo.innerText = false;
			}

		}
	</script>

</f:verbatim>

<h:form id="selecaoAgenciaContaForm">

	<h:outputText id="idOcultarSaldo" style="display:none"
		value="#{transferenciaForm.horariosSaldoLimiteModel.ocultarSaldo}"></h:outputText>
	<h:outputText id="idSaldoModel" style="display:none"
		value="#{transferenciaForm.horariosSaldoLimiteModel.saldo}"></h:outputText>

	<a4j:jsFunction name="teste2"
		action="#{selecaoAgenciaContaTransferenciaBean.recarregarHorariosLimitesAgenciaConta}"
		reRender="selecaoAgenciaContaForm:idSaldoModel,selecaoAgenciaContaForm:infoSaldo"
		immediate="true"></a4j:jsFunction>


	<t:div styleClass="row">
		<t:div id="filho1" styleClass="col-8">
			<f:verbatim>
				<ul class="card clearfix mr-24 mb-24 mt-24">
					<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD -->
			</f:verbatim>

			<t:div styleClass="margens_laterais after mb-16 mt-8"
				rendered="#{selecaoAgenciaContaTransferenciaBean.transform.erroProcuradorSemPermissaoAcesso}">
				<t:div styleClass="box-erro after">
					<t:div styleClass="ctn-box after tabindex tabfirst">
						<h:outputText escape="true" styleClass="tituloAlerta"
							value="#{messageBundle.erroCadastro_alerta_procuradorSemPermissaoAcesso}" />
					</t:div>
				</t:div>
			</t:div>

			<brArq:div id="descricaoEmpresa" styleClass="mt-24">
				<t:div styleClass="row">
					<brArq:div styleClass="legenda mt-4">
						<ib:campoFormularioNome value="Empresa | CNPJ:" />
					</brArq:div>
					<brArq:div>
						<brHtml:brOutputText styleClass="ml-8 col-10 title-pequeno"
							value="#{selecaoAgenciaContaTransferenciaBean.transform.empresa.nomeEmpresa} | #{selecaoAgenciaContaTransferenciaBean.transform.empresa.cnpj.cnpjFormatado}" />
					</brArq:div>
				</t:div>
			</brArq:div>

			<!-- Combo de contas e empresas -->
			<ib:grupoCampos id="comboContas" separadorAbaixo="false">
				<%@include file="/WEB-INF/jsp/adesao/contaServico.jsp"%>
			</ib:grupoCampos>

			<brArq:div id="divBotoes" styleClass="radioCustom mt-48">
				<brArq:div>
					<brHtml:brPanelGroup>
						<h:commandButton id="btnContinuar"
							disabled="#{selecaoAgenciaContaTransferenciaBean.transform.btnContAgenciaConta}"
							action="#{selecaoAgenciaContaTransferenciaBean.continuar}"
							styleClass="tabindex mb-16 btn-default-1" title="Continuar"
							value="Continuar">
						</h:commandButton>
						<brHtml:brOutputLink title="Voltar"
							value="#{propBean.config.properties['url.pix']}?CTRL=#{sessionScope['ibpf_session_ctl']}"
							target="paginaCentral" styleClass="link tabindex ml-48">
							<h:outputText value="Voltar" />
						</brHtml:brOutputLink>
					</brHtml:brPanelGroup>
				</brArq:div>

			</brArq:div>

			<f:verbatim>
				</li>
				</ul>
				<!-- FIM CARD -->
			</f:verbatim>
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