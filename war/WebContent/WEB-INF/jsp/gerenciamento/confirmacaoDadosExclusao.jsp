<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:saveState value="#{gerenciamentoForm}" />

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
		});
	</script>
</f:verbatim>

<h:form id="confirmacaoDadosExclusaoForm">

	<brArq:div>
		<h:messages showDetail="true"></h:messages>

		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
		<brArq:div  styleClass=" mt-16 mb-48"><!--  cabecalho inicio -->
							
										  
							<brArq:div styleClass="mb-16 row "><!-- info  -->
									
								<brArq:div styleClass=" col-3  alinhamento-direita">
									 <brHtml:brOutputText  styleClass="legenda ml-56" value="#{messageBundle.empresa_nome} | #{messageBundle.cnpj_empresa}"/>
								</brArq:div>			
								<brArq:div styleClass="col-9">	
										<brHtml:brOutputText  styleClass="legenda ml-32" value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.empresa.nomeEmpresa} | "/>
										<brHtml:brOutputText  styleClass="legenda" value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.empresa.cnpj.cnpjFormatado}"/>
								</brArq:div>
														
							 </brArq:div><!-- fim info -->
					
							 
							<brArq:div styleClass="mb-16 row "><!-- info conta debito -->
									
								<brArq:div styleClass=" col-3  alinhamento-direita">
									 <brHtml:brOutputText styleClass="legenda"  value="#{messageBundle.conta_debito} "/>
								</brArq:div>	
										
								<brArq:div styleClass="col-9">	
									<brHtml:brOutputText styleClass="legenda ml-32 "  value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.conta.agencia} | #{messageBundle.conta_numero} #{confirmacaoDadosExclusaoBean.gerenciamentoForm.conta.numConta}-#{confirmacaoDadosExclusaoBean.digito}"/>
									<brHtml:brOutputText styleClass="legenda ml-8 "  value="| #{messageBundle.tipo_conta} conta-corrente"/>
								</brArq:div>
														
							</brArq:div><!-- fim info -->
										  
										
		</brArq:div>	<!-- cabecalho fim -->	
		

		<t:htmlTag value="hr" styleClass="mt-24 mb-24" />
		<!-- linha meio -->

		<brArq:div styleClass="ml-40 ">
			<ib:campoFormulario styleClass="mb-16 " tipo="entrada" >
				<!--  -->
				<brHtml:brOutputText styleClass="p-pequeno "
					value="#{messageBundle.label_chave}" />
			</ib:campoFormulario>
		</brArq:div>

			
				<!-- info celular/email-->
				<brArq:div id="tipoChaveSelecaoExclusaoCelularEmail" styleClass="row">
				
						<brArq:div styleClass="col-3 alinhamento-direita">
							<brHtml:brOutputText 
								styleClass="legenda "
								value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.descricaoTipoChave}:" />
						</brArq:div>	
						<brArq:div styleClass="col-9">			
							<brHtml:brOutputText styleClass="legenda ml-32"
								value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.chaveFormatada}"  
								rendered="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.tipoChave != 5}"/>
								<brHtml:brOutputText styleClass="legenda ml-32"
								value="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.iapldoAdsaoSpi}"  
								rendered="#{confirmacaoDadosExclusaoBean.gerenciamentoForm.chaveSelecionada.tipoChave == 5}"/>
						</brArq:div>			
				</brArq:div>
				<!-- fim celular/email-->
				
			<brArq:div>
				<brArq:div id="divBotoes" styleClass=" mt-32 ml-24">
					<brArq:div styleClass="col-5">
						<brHtml:brPanelGroup>
	
							<h:commandButton id="btnConfirmar"
								action="#{confirmacaoDadosExclusaoBean.botaoConfirmar}"
								styleClass="tabindex mb-16 btn-default-1" title="Confirmar"
								value="Confirmar">
							</h:commandButton>
							<h:commandButton title="Voltar" 
								styleClass="tabindex ml-32 mb-16 link border0" value="voltar"
								action="#{confirmacaoDadosExclusaoBean.botaoCancelar}">
							</h:commandButton>
						</brHtml:brPanelGroup>
					</brArq:div>
				</brArq:div>
			</brArq:div>
		
	</brArq:div>
</h:form>