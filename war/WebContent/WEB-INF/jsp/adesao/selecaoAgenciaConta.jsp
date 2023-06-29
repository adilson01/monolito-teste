<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:saveState value="#{adesaoForm}" />

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
			mostrarDescricaoEmpresa();
         });
		
		function mostrarDescricaoEmpresa(){
			var empresa = document.getElementById("selecaoAgenciaContaForm:empresaCmb");
			
			if(empresa == null){
				jQuery('#selecaoAgenciaContaForm\\:descricaoEmpresa').removeClass('none_i');
			} else {
				jQuery('#selecaoAgenciaContaForm\\:descricaoEmpresa').addClass('none_i');
			}
		}
		
	</script>
</f:verbatim>


<h:form id="selecaoAgenciaContaForm">

	<brArq:div id="idDivPrincipal">
		<f:verbatim>
			<ul class="card clearfix mt-8">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
		<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{selecaoAgenciaContaBean.form.erroServico}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="Sistema indisponível no momento" />
					<h:outputText escape="true" styleClass="mensagemAlerta"	value="#{selecaoAgenciaContaBean.form.mensagemErro}" />
				</t:div>
			</t:div>
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{selecaoAgenciaContaBean.form.contaNaoPodeOperarPix}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="#{selecaoAgenciaContaBean.form.mensagemErro}" />
				</t:div>
			</t:div>
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{selecaoAgenciaContaBean.form.erroProcuradorSemPermissaoAcesso}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="#{messageBundle.erroCadastro_alerta_procuradorSemPermissaoAcesso}" />
				</t:div>
			</t:div>
		</t:div>
		
		<brArq:div id="descricaoEmpresa" styleClass="mt-24">
			<t:div styleClass="row">
				<brArq:div styleClass="legenda mt-4">
					<ib:campoFormularioNome value="Empresa | CNPJ " />
				</brArq:div>
				<brArq:div styleClass="ml-8 col-10 title-pequeno">
					<brHtml:brOutputText
						value="#{selecaoAgenciaContaBean.form.empresa.nomeEmpresa} | #{selecaoAgenciaContaBean.form.empresa.cnpj.cnpjFormatado}" />
				</brArq:div>
			</t:div>
		</brArq:div>

		<!-- Combo de contas e empresas -->
		<ib:grupoCampos id="comboContas" separadorAbaixo="false">
			<%@include file="/WEB-INF/jsp/adesao/contaServico.jsp"%>
		</ib:grupoCampos>

		<brArq:div id="divBotoes" styleClass="radioCustom mt-32">
			<brArq:div styleClass="col-12">
					<h:commandButton id="btnContinuar"
						action="#{selecaoAgenciaContaBean.continuar}"
						disabled="#{selecaoAgenciaContaBean.form.btnContAgenciaConta}"
						styleClass="tabindex mb-16 btn-default-1" title="Continuar"
						value="Continuar">
					</h:commandButton>
					<brHtml:brOutputLink title="Voltar"
						value="#{propBean.config.properties['url.pix']}?CTRL=#{sessionScope['ibpf_session_ctl']}"
						target="paginaCentral" styleClass="link tabindex ml-48">
						<h:outputText value="Voltar" />
					</brHtml:brOutputLink>
			</brArq:div>

		</brArq:div>

	</brArq:div>

</h:form>