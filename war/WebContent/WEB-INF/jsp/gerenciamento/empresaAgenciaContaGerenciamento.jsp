<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>

<a4j:jsFunction name="ajaxComboEmpresaServico"
	oncomplete="jQuery('#contaDebito').combo(); atualizaItensDinamicos();"
	reRender="contaCmbDebito" immediate="true"></a4j:jsFunction>

<t:div styleClass="row">
	<t:div styleClass="col-7">
	
	 <brArq:div id="descricaoEmpresa" styleClass="mt-24">
			<t:div styleClass="row">
				<brArq:div styleClass="legenda">
					<brHtml:brOutputText value="Empresa | CNPJ  " />
				</brArq:div>
				<brArq:div styleClass="bold ml-4">
					<brHtml:brOutputText
						value="#{gerenciamentoForm.empresa.nomeEmpresa} | #{gerenciamentoForm.empresa.cnpj.cnpjFormatado}" />
				</brArq:div>
			</t:div>
		</brArq:div> 
		
		<a4j:region id="ajaxComboEmpresa">
			<ib:campoFormulario tipo="entrada"
				rendered="#{grupoEmpresaSessionBean.listaGrupoEmpresaServico != null}"
				id="empresaCmb">
				<t:buffer into="#{chaveTituloComboConta}">
					<h:outputText
						value="listaEmpresaServico.titulo.#{grupoEmpresaSessionBean.tipoListaEmpresa}" />
				</t:buffer>
				<t:buffer into="#{chavePaddingComboConta}">
					<h:outputText
						value="listaEmpresaServico.padding.#{grupoEmpresaSessionBean.tipoListaEmpresa}" />
				</t:buffer>
				<ib:campoFormularioMensagemErro>
					<brHtml:brMessage for="empresaServico" />
				</ib:campoFormularioMensagemErro>
				<brHtml:brOutputLabel for="empresaServico" value="Empresa | CNPJ" />
				<t:div
					style="padding-top: #{messageBundleInfra[chavePaddingComboConta]}">
					<ib:campoFormularioInput>
						<brHtml:brSelectOneMenu forceId="true" immediate="true"
							id="empresaServico" style="width: auto !important"
							styleClass="combo cmbEmpresa tabindex"
							value="#{grupoEmpresaBean.idEmpresa}">
							<f:selectItems
								value="#{grupoEmpresaSessionBean.listaGrupoEmpresaServico}" />
							<f:validator validatorId="grupoEmpresaValidator" />
						</brHtml:brSelectOneMenu>
					</ib:campoFormularioInput>
				</t:div>
			</ib:campoFormulario>
		</a4j:region>
	</t:div>

	<t:div styleClass="col-5">
		<a4j:region id="ajaxPersonalizarConta">
			<ib:campoFormulario tipo="entrada"
				rendered="#{contaEmpresaSessionBean.tipoListaConta != null}"
				id="contaCmbDebito">
				<brArq:buffer into="#{chaveTituloComboConta}">
					<h:outputText
						value="listaContaServico.titulo.#{contaEmpresaSessionBean.tipoListaConta}" />
				</brArq:buffer>
				<brArq:buffer into="#{chavePaddingComboConta}">
					<h:outputText
						value="listaContaServico.padding.#{contaEmpresaSessionBean.tipoListaConta}" />
				</brArq:buffer>
				<ib:campoFormularioMensagemErro>
					<brHtml:brMessage for="contaDebito" />
				</ib:campoFormularioMensagemErro>
				<brHtml:brOutputLabel for="contaDebito" value="Agência e conta" />
				<brArq:div>
					<ib:campoFormularioInput>
						<brHtml:brSelectOneMenu style="height:20px;" forceId="true"
							id="contaDebito" styleClass="combo cmbConta tabindex"
							value="#{contaEmpresaBean.idContaDebito}">
							<f:validator validatorId="contaEmpresaValidator" />
							<f:selectItems
								value="#{contaEmpresaSessionBean.listaContaServico}" />
						</brHtml:brSelectOneMenu>
					</ib:campoFormularioInput>
				</brArq:div>
			</ib:campoFormulario>
		</a4j:region>
	</t:div>
</t:div>

<f:verbatim>
	<script type="text/javascript">
		function atualizaItensDinamicosEmpresa() {
			bCancel = true;
			ajaxComboEmpresaServico();
			
		}

		function atualizaItensDinamicos() {
			carregarListaChaves();
		}
	</script>
</f:verbatim>