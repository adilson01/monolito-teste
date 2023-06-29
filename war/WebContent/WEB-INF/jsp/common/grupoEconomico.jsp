<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml" %>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>

<h:panelGroup rendered="#{grupoEmpresaSessionBean.listaGrupoEmpresaServico != null}">
<a4j:region id="ajaxComboEmpresa">
	<brArq:div styleClass="margens_laterais">
		<ib:grupoCampos styleClass="grupoCampos pl30 pb20 pt10">
			<ib:campoFormulario tipo="entrada" id="campoComboEmpresa">
				<t:buffer into="#{chaveTituloComboConta}"><h:outputText value="listaEmpresaServico.titulo.#{grupoEmpresaSessionBean.tipoListaEmpresa}"/></t:buffer>
				<t:buffer into="#{chavePaddingComboConta}"><h:outputText value="listaEmpresaServico.padding.#{grupoEmpresaSessionBean.tipoListaEmpresa}"/></t:buffer>
				<ib:campoFormularioMensagemErro>
					<brHtml:brMessage for="empresaServico" />				
				</ib:campoFormularioMensagemErro>
				<brHtml:brOutputLabel for="empresaServico" value="#{messageBundleInfra[chaveTituloComboConta]}:"/>
				<t:div style="padding-top: #{messageBundleInfra[chavePaddingComboConta]}">
					<ib:campoFormularioInput>
						<brHtml:brSelectOneMenu forceId="true" immediate="true" id="empresaServico" styleClass="combo cmbEmpresa tabindex" value="#{grupoEmpresaBean.idEmpresa}">
							<f:selectItems value="#{grupoEmpresaSessionBean.listaGrupoEmpresaServico}"/>
							<f:validator validatorId="grupoEmpresaValidator"/>
						</brHtml:brSelectOneMenu >
					</ib:campoFormularioInput>
					<ib:campoFormularioTooltip texto="#{conteudo.tip_cmbEmpresa}" />
				</t:div>
			</ib:campoFormulario>
		</ib:grupoCampos>
	</brArq:div>
</a4j:region>
<%-- <t:div id="contaCmbDebito" ></t:div> --%>

<f:verbatim>
<script type="text/javascript">
	function atualizaItensDinamicosEmpresa(){
		try {
			assyncronous_call = false;			
			
			ajaxComboEmpresaServico();
		
		} finally {
			assyncronous_call = true;
		}
		atualizaItensDinamicos();
	}
</script>
</f:verbatim>
</h:panelGroup>