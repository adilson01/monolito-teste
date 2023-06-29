<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<t:saveState value="#{vincularChaveForm}" />


<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {

		});
	</script>
</f:verbatim>

<h:form id="vincularChaveForm">

	<t:div styleClass="row">
		<brArq:div id="filho1" style="height: 10px;" styleClass="col-7">
			<f:verbatim>
				<ul class="card clearfix mt-16">
					<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD -->
			</f:verbatim>


			<brArq:div>
				<t:div styleClass="campo-busca">
					<t:div styleClass="row">
						<t:div styleClass="col-6">
							<ib:campoFormulario tipo="entrada" id="chave">
								<ib:campoFormularioNome styleClass="rotulo">
									<brHtml:brOutputText value="Chave" />
								</ib:campoFormularioNome>
								<ib:campoFormularioInput>
									<brHtml:brInputText id="buscaLista" value="" title=""
										styleClass="busca input-search-lista">
										<brHtml:brOutputText styleClass="busca ico_buscar" />
										<brHtml:brOutputText styleClass="fechar ico_fechar" />
									</brHtml:brInputText>
								</ib:campoFormularioInput>
							</ib:campoFormulario>
						</t:div>
					</t:div>
			    <!--  	/**SUA LISTA**/ -->
					<ib:lista styleClass="itensBusca">
						<ib:itemLista styleClass="">
						  
							<t:dataList layout="unorderedList"
								value="#{vincularChaveBean.listachaves}" var="item">
								<h:outputText value="#{item.chave}" />
							</t:dataList>
						</ib:itemLista>
					</ib:lista>
				</t:div>
			</brArq:div>
		</brArq:div>
	</t:div>
</h:form>