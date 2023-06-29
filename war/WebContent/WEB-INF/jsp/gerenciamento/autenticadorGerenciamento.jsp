<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/autenticador_component"
	prefix="brAuten"%>

<t:saveState value="#{gerenciamentoForm}" />

<brArq:form id="formAutenticador">

	<f:verbatim>
		<ul class="card clearfix">
			<li class="cardList  card-regular sombra-nivel200">
				<!--  INICIO CARD -->
	</f:verbatim>

	<brArq:div styleClass="margens_laterais">
		<ib:grupoCampos separadorAbaixo="false">
		
			<brAuten:autenticador lote="false"
								  CTRL="#{sessionScope['ibpf_session_ctl']}"
				                  idObjetoConfirmacao="botaoConfirmar"
				                  nuTransacaoLote="#{gerenciamentoForm.numeroTransacao}"
				                  tpAssinatura="A" />
				
				
			<brArq:div styleClass="campos_form margens_laterais">
				<h:commandButton id="botaoConfirmar" styleClass="btn-default-1"
					value="Confirmar" alt="#{messageBundle.label_confirmar}"
					title="Confirmar">
				</h:commandButton>
				<h:commandLink action="#{confirmacaoDadosExclusaoBean.recarregarPagina}"
					styleClass="margens_laterais link tabindex ml-32" value="Voltar"
					title="Voltar">
				</h:commandLink>
			</brArq:div>
		</ib:grupoCampos>
	</brArq:div>

	<f:verbatim>
		</li>
		</ul>
		<!--  FIM CARD -->
	</f:verbatim>
</brArq:form>


