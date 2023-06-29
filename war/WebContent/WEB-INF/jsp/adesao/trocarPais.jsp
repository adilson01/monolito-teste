<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<f:verbatim>
	<script language="javascript">
	
	function filtrarTabela() {
		  var input, filter, table, tr, td, i, txtValue;
		  
		  input = document.getElementById("frmTrocarPais:buscaTabela");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("frmTrocarPais:idListaPaises");
		  tr = table.getElementsByTagName("tr");
		  
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[0];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		  
		}
		
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<t:saveState value="#{transferenciaForm}" />

<h:form id="frmTrocarPais">

	<a4j:jsFunction name="selecionarDDI" 
	    actionListener="#{trocarPaisBean.selecionarPais}"
	 	reRender="continuar" immediate="true">
	</a4j:jsFunction>	

	<brArq:div styleClass="mt-24">
		<ib:campoFormularioNome styleClass="title-grande ">
			<brHtml:brOutputText value="#{messageBundle.titulo_trocar_pais}" />
		</ib:campoFormularioNome>
	</brArq:div>

	<brArq:div styleClass="mt-24">

		<t:div styleClass="campo-busca">
			<t:div styleClass="row">
				<t:div styleClass="col-12">
					<ib:campoFormulario tipo="entrada" id="exemplo">
						<ib:campoFormularioNome styleClass="rotulo">
							<brHtml:brOutputText value="#{messageBundle.label_buscar_pais}" />
						</ib:campoFormularioNome>
						<ib:campoFormularioInput>
							<brHtml:brInputText id="buscaTabela" value="" title=""
								styleClass="busca input-search-tabela"
								onkeyup="filtrarTabela();">
								<brHtml:brOutputText styleClass="busca ico_buscar" />
							</brHtml:brInputText>
						</ib:campoFormularioInput>
					</ib:campoFormulario>
				</t:div>
			</t:div>
		</t:div>

		<ib:campoFormulario tipo="entrada" id="codigoDeArea">
			<brArq:div style="height: 240px; overflow: auto;">
				<t:dataTable 	
					styleClass="tabela_listagem_vv mt-8"
					id="idListaPaises" 
					var="pais" 
					rowIndexVar="linha"
					value="#{trocarPaisBean.form.listaPaises}" 
					rows="100" 
					cellpadding="1" cellspacing="1">
					
					<t:column styleClass="campos_form" width="3%">
						<brArq:div>
							<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarDDI();">
								<ib:radioButton 
									id="pais_ddi"
									name="pais_ddi" 
									itemValue="#{pais.ddi}" 
									overrideName="true" 
									value="#{trocarPaisBean.ddi}"
									itemLabel="#{pais.nome} (+#{pais.ddi})"
									styleClass="tabindex"/>
								</brHtml:brOutputLabel>
							<t:htmlTag value="hr" styleClass="mt-24 mb-24"/>
						</brArq:div>
					</t:column>
					
				</t:dataTable>
			</brArq:div>
		</ib:campoFormulario>

		<brArq:div id="divBotoes" styleClass="mt-40">
			<t:div styleClass="row col-12">
                <h:commandLink styleClass="closeModal bt_fechar btn-default-1 tabindex "
                   title="Continuar"
                   id="btn_continuar"
                   target="paginaCentral"
                   action="#{trocarPaisBean.trocarPais}">
                   <brHtml:brOutputText value="Continuar" />
                </h:commandLink>
				<h:commandLink title="cancelar"
					styleClass="closeModal tabindex ml-32 link"
					id="btnCancelar" target="paginaCentral"
					action="#{trocarPaisBean.cancelar}">
					<brHtml:brOutputText value="Cancelar" />
				</h:commandLink>
			</t:div>
		</brArq:div>

	</brArq:div>
</h:form>