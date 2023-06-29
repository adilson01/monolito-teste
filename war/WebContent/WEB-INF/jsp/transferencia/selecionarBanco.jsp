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
		  
		  input = document.getElementById("frmSelecionarBanco:buscaTabela");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("frmSelecionarBanco:idListaBancos");
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

<h:form id="frmSelecionarBanco" style=" height:100%;">

<a4j:jsFunction name="selecionarBanco" 
    actionListener="#{selecionarBancoBean.ativarBtnSelecionarBanco}"
    reRender="btnSelecionarBanco" immediate="true">
</a4j:jsFunction>	

	<brArq:div styleClass="mt-24">

		<ib:campoFormularioNome styleClass="title-grande ">
			<brHtml:brOutputText value="#{messageBundle.balel_lista_bancos}" />
		</ib:campoFormularioNome>
	</brArq:div>

	<brArq:div styleClass="mt-24">
		<ib:campoFormularioNome styleClass="legenda">
			<brHtml:brOutputText value="#{messageBundle.label_titulo_selecionar_banco}" />
		</ib:campoFormularioNome>

		<t:div styleClass="campo-busca">
			<t:div styleClass="row">
				<t:div styleClass="col-12">
					<ib:campoFormulario tipo="entrada" id="campoFormId">
						<ib:campoFormularioNome styleClass="rotulo">
							<brHtml:brOutputText value="#{messageBundle.label_selecionar_banco}" />
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

		<ib:campoFormulario tipo="entrada">
			<brArq:div style="height: 240px; overflow: auto;">
				<t:dataTable 	
					styleClass="tabela_listagem_vv mt-16"
					id="idListaBancos" 
					var="banco" 
					rowIndexVar="linha"
					value="#{selecionarBancoBean.listaBancos}"
					rows="100" 
					cellpadding="1" cellspacing="1">					
					<t:column styleClass="campos_form" width="3%">
						<brArq:div>
							<brHtml:brOutputLabel styleClass="radioCustom " onclick="selecionarBanco();">
								<ib:radioButton 
									id="banco"
									name="banco" 
									itemValue="#{banco.nome}" 
									overrideName="true" 
									value="#{selecionarBancoBean.transform.bancoSelecionado}"
									itemLabel="#{banco.nome}"
									styleClass="tabindex"/>
								</brHtml:brOutputLabel>
							<t:htmlTag value="hr" styleClass="mt-24 mb-24"/><!-- linha meio -->					
						</brArq:div>
					</t:column>					
				</t:dataTable>				
			</brArq:div>		
		</ib:campoFormulario>

		<brArq:div id="divBotoes" styleClass="mt-40">
			<brArq:div styleClass="pt-16">
					<h:commandLink
						styleClass="closeModal btn-default-1 tabindex"
						target="paginaCentral"
						title="Selecionar Banco" id="btnSelecionarBanco"
						action="#{selecionarBancoBean.selecionarBanco}">
						<brHtml:brOutputText value="Selecionar Banco" />
					</h:commandLink>				
					<brHtml:brCommandLink 
						title="cancelar" 
						styleClass="closeModal tabindex ml-32 mb-16 link" 
						immediate="true"
						target="modal_infra_estrutura">
						<brHtml:brOutputText value="Cancelar" />
					</brHtml:brCommandLink>
			</brArq:div>
		</brArq:div>
	</brArq:div>
</h:form>