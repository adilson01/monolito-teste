<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>


<t:saveState value="#{gerenciamentoForm}" />

<f:verbatim>
	<script language="javascript">
	
	jQuery(document).ready(function() {
		mostrarDescricaoEmpresa();
		ordenarTabelas();
     });
	
	function mostrarDescricaoEmpresa(){
		var empresa = document.getElementById("consultaChavesPixForm:empresaCmb");
		
		if(empresa == null){
			jQuery('#consultaChavesPixForm\\:descricaoEmpresa').removeClass('none_i');
		} else {
			jQuery('#consultaChavesPixForm\\:descricaoEmpresa').addClass('none_i');
		}
	}
			
	function filtrarTabela() {
		  var input, filter, table, tr, td, i, txtValue;
		  
		  input = document.getElementById("consultaChavesPixForm:buscaTabela");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("consultaChavesPixForm:listagem");
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


<h:form id="consultaChavesPixForm">

<a4j:jsFunction name="carregarListaChaves"
			action="#{gerenciamentoChavesPixBean.recarregarListaChaves}" 
			reRender="consultaChavesPixForm:listaChaves,consultaChavesPixForm:boxErro"  
			immediate="true"></a4j:jsFunction>

	<brArq:div id="idDivConsulta">
		<f:verbatim>
			<ul class="card clearfix">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
		<t:div styleClass="campo-busca">
			<t:div styleClass="row">
				<t:div styleClass="col-6">
					<ib:campoFormulario tipo="entrada" id="exemplo">
						<ib:campoFormularioNome styleClass="rotulo">
							<brHtml:brOutputText value="#{messageBundle.busque_por_uma_chave}" />
						</ib:campoFormularioNome>
						<ib:campoFormularioInput>
							<brHtml:brInputText id="buscaTabela" value="" title=""
								styleClass="busca input-search-tabela" onkeyup="filtrarTabela();">
								<brHtml:brOutputText styleClass="busca ico_buscar" />
							</brHtml:brInputText>
						</ib:campoFormularioInput>
					</ib:campoFormulario>
				</t:div>
				<t:div styleClass="col-6">
				<brArq:div id="divBotoes" styleClass="radioCustom mt-48 alinhamento-direita">
					<h:commandButton id="btnCadastrarNovaChave" 
							action="#{gerenciamentoChavesPixBean.cadastrarNovaChave}"
							styleClass="tabindex btn-default-1" 
							title="Cadastrar chave"
							value="Cadastrar chave">
					</h:commandButton>
				</brArq:div>
				</t:div>
			</t:div>
		</t:div>

	</brArq:div>
	
	<brArq:div id="idDivLista">
		<f:verbatim>
			<ul class="card clearfix mt-16">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
			 <ib:grupoCampos id="comboContas" separadorAbaixo="false">
			  <%@include file="/WEB-INF/jsp/gerenciamento/empresaAgenciaContaGerenciamento.jsp"%>
			</ib:grupoCampos> 
	
	<t:div id="boxErro">		
		<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{gerenciamentoChavesPixBean.gerenciamentoForm.erroServico}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta"
						value="Sistema indisponível no momento" />
					<h:outputText escape="true" styleClass="mensagemAlerta"
						value="Por favor, tente mais tarde" />
				</t:div>
			</t:div>
		</t:div>
	</t:div>
	
	<t:div id="listaChaves">	
		<t:div forceId="true" styleClass="mt-24" rendered="#{!gerenciamentoChavesPixBean.gerenciamentoForm.erroServico}">
			
			<t:div styleClass="margens_laterais after mb-16 mt-8" rendered="#{gerenciamentoChavesPixBean.gerenciamentoForm.erroProcuradorSemPermissaoAcesso}">
				<t:div styleClass="box-erro after">
					<t:div styleClass="ctn-box after tabindex tabfirst">
						<h:outputText escape="true" styleClass="tituloAlerta" value="#{messageBundle.erroCadastro_alerta_procuradorSemPermissaoAcesso}" />
					</t:div>
				</t:div>
			</t:div>
			
			<ib:grupoCampos separador="true">	
				<t:dataTable
					styleClass="tabelaListagem mt0 tabelaOrdenada ordenacao"
					id="listagem" value="#{gerenciamentoChavesPixBean.gerenciamentoForm.listaChaves}" var="chave"
					rowIndexVar="linha" rowClasses=",odd" headerClass="sort" 
					cellpadding="1" cellspacing="1">
	
					<t:column styleClass="campos_form">
						<f:facet name="header">
							<brHtml:brOutputLink value="javascript:;">
								<brHtml:brOutputText value="Chaves Pix"	escape="false" />
								<brHtml:brOutputText styleClass="" />
							</brHtml:brOutputLink>
						</f:facet>
						<h:outputText value="#{chave.descricaoTipoChave}: "></h:outputText>
						<brHtml:brOutputTextBold value="#{chave.chaveFormatada}" styleClass="title-pequeno" rendered="#{chave.tipoChave != 5}"></brHtml:brOutputTextBold>
						<brHtml:brOutputTextBold value="#{chave.iapldoAdsaoSpi}" styleClass="title-pequeno" rendered="#{chave.tipoChave == 5}"></brHtml:brOutputTextBold>
					</t:column>
				
					<t:column styleClass="campos_form">
						<f:facet name="header">
							<brHtml:brOutputText value="Agência e conta" escape="false" />
							<brHtml:brOutputText styleClass="" />
						</f:facet>
						<h:outputText value="#{chave.agencia} | #{chave.conta}-#{chave.digConta}"></h:outputText>
					</t:column>
	
					<t:column styleClass="campos_form">
						<f:facet name="header">
								<brHtml:brOutputText value="Data de cadastro"	escape="false" />
								<brHtml:brOutputText styleClass="" />
						</f:facet>
						<h:outputText value="#{chave.hinclReg}"></h:outputText>
					</t:column>
					
					<t:column styleClass="campos_form">
						<f:facet name="header">
								<brHtml:brOutputText value="Situação"	escape="false" />
								<brHtml:brOutputText styleClass="" />
						</f:facet>
					<h:outputText value="#{chave.descricaoStatus}"></h:outputText>
					</t:column>
					
					<t:column styleClass="alignCenter campos_form" >
						<f:facet name="header">
						</f:facet>
						<t:div styleClass="alinhamento-centro">
							<brHtml:brCommandLink id="detalharLote"
								title="Excluir"
								styleClass="ico_lixeira fr tooltip auto_Tip tabindex"
								action="#{gerenciamentoChavesPixBean.abrirModalExcluir}">
								<f:param name="indexChaveExcluir" value="#{linha}" />
							</brHtml:brCommandLink>
						</t:div>
					</t:column>
					
				</t:dataTable>
				</ib:grupoCampos>
				
			</t:div>
		</t:div>

	</brArq:div>

</h:form>