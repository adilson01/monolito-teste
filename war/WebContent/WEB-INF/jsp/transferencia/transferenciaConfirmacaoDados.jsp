<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {

		});
		
		function desabilitarBtnConfirmar(){
			var btn = document.getElementById("confirmacaoDadosForm:btnConfirmar");
			btn.disabled = false;
		}
		
			
	</script>
	
</f:verbatim>

<style>

</style>

<t:saveState value="#{transferenciaForm}" />

<h:form id="transicaoConfirmacaoDadosForm">

	<h:messages showDetail="true"></h:messages>

	<brArq:div>
	
		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->					
		
		</f:verbatim> <!--  chave/celular -->
		
		
							<brArq:div styleClass="title-medio ml-24 mt-32 "> <!-- inicio dados empresas -->
									<brHtml:brOutputText styleClass="title-medio" value="#{messageBundle.dados_da_empresa}" ></brHtml:brOutputText>
							</brArq:div><!-- fim dados empresas -->	
							
							<brArq:div  styleClass=" mt-16 mb-48"><!--  cabecalho inicio -->
							
										  
							<brArq:div styleClass="mb-16"><!-- info nome Empresa/CNPJ -->
											
								<brArq:div styleClass="col-9">
									<brHtml:brPanelGroup>
										<brHtml:brOutputText  styleClass="legenda ml-56" value="#{messageBundle.empresa_nome} | #{messageBundle.cnpj_empresa}"/>
										<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaForm.empresa.nomeEmpresa} | "/>
										<brHtml:brOutputText  styleClass="legenda" value="#{transferenciaForm.empresa.cnpj.cnpjFormatado}"/>
									</brHtml:brPanelGroup>
								</brArq:div>
														
							 </brArq:div><!-- info nome Empresa/CNPJ  fim -->
										  
							<brArq:div styleClass="mb-16"><!-- info agencia/conta/tipoconta -->
									<brArq:div styleClass="col-12 ml6">
										<brHtml:brPanelGroup>
											<brHtml:brOutputText styleClass="legenda ml-48 "  value="#{messageBundle.conta_debito} "/>
											<brHtml:brOutputText styleClass="legenda ml-32 "  value="#{transferenciaForm.conta.agencia} | #{messageBundle.conta_numero} #{transferenciaForm.conta.numConta}-#{transferenciaForm.trasnferenciaModel.dac}"/>
											<brHtml:brOutputText styleClass="legenda ml-8 "  value="| #{messageBundle.tipo_conta} conta-corrente"/>
										</brHtml:brPanelGroup>
									</brArq:div>
							 </brArq:div><!-- info agencia/conta/tipoconta fim -->
										  
										
							</brArq:div>	<!-- cabecalho fim -->	
	
							<t:htmlTag value="hr" styleClass="mt-24 mb-24" /><!-- linha meio -->
	
	
							<brArq:div styleClass="title-medio ml-24 mt-48 "> <!-- inicio dados transferencia -->
									<brHtml:brOutputText styleClass="title-medio" value="#{messageBundle.dados_transferencias}" ></brHtml:brOutputText>
							</brArq:div><!-- fim dados transferencia -->	
							
						
							<brArq:div styleClass="row mb-16 mt-24"><!-- nome empresa -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.nome_empresa}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32 " value="#{transferenciaConfirmacaoDadosBean.transform.empresa.nomeEmpresa }"/>
								</brArq:div>
												
							</brArq:div><!--  nome empresa fim -->
							
							<brArq:div styleClass="mb-16"><!-- email/celular   -->
							
							<brArq:div styleClass="row" rendered="#{transferenciaConfirmacaoDadosBean.transform.chaveSelecionada == '1'}">
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.label_celular}:"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaConfirmacaoDadosBean.transform.chaveFinal}"/>
								</brArq:div>
							</brArq:div>
							<brArq:div styleClass="row" rendered="#{transferenciaConfirmacaoDadosBean.transform.chaveSelecionada == '2'}">
								<brArq:div styleClass="col-2" >
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.chave_email}:"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaConfirmacaoDadosBean.transform.chaveFinal}"/>
								</brArq:div>
							</brArq:div>
							<brArq:div styleClass="row" rendered="#{transferenciaConfirmacaoDadosBean.transform.chaveSelecionada == '3'}">
								<brArq:div styleClass="col-2" >
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.campo_cpf_cnpj}:"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaConfirmacaoDadosBean.transform.chaveFinal}"/>
								</brArq:div>
							</brArq:div>
												
							</brArq:div><!-- email/celular   -->
							
							<brArq:div styleClass="row mb-16"><!-- instituicao  -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.instituicao_financeira}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="Banco Bradesco SA"/>
								</brArq:div>
												
							</brArq:div><!-- instituicao  -->
							
							<brArq:div styleClass="row mb-16"><!-- CNPJ  -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.cnpj_empresa}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaForm.empresa.cnpj.cnpjFormatado}"/>
								</brArq:div>
												
							</brArq:div><!-- CNPJ fim -->
							
							<brArq:div styleClass="row mb-16"><!-- valor -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.valor_transferencia}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="#{transferenciaConfirmacaoDadosBean.transform.valor}"/>
								</brArq:div>
												
							</brArq:div><!-- fim valor -->
							
							<brArq:div styleClass="row mb-16"><!-- tarifa -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.valor_tarifa}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="R$ 0,10"/>
								</brArq:div>
												
							</brArq:div><!-- fim tarifa -->
							
							
							<brArq:div styleClass="row mb-16"><!-- data transacao  -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.data_transacao}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="20/06/2020"/>
								</brArq:div>
												
							</brArq:div><!-- data transacao fim -->
							
							
							<brArq:div styleClass="row mb-32"><!-- creditar em -->
							
								<brArq:div styleClass="col-2">
									<brHtml:brOutputText   styleClass="legenda alinhamento-direita" value="#{messageBundle.creditar}"/>
								</brArq:div>
								<brArq:div styleClass="col-10">
									<brHtml:brOutputText  styleClass="legenda ml-32" value="conta-corrente"/>
								</brArq:div>
												
							</brArq:div><!-- creditar em fim -->


							<t:div id="modelo" styleClass="box-checkbox"><!--  checklote -->
							 	<brHtml:brSelectBooleanCheckbox 
							 		styleClass="tabindex " onclick="">
							 	</brHtml:brSelectBooleanCheckbox>
							 	<brHtml:brOutputText styleClass="p-medio" value="Adicionar aos meus contatos do PIX"/>
							 	<t:div styleClass="box-checkbox-label" >								
									
								 </t:div> 
							 </t:div><!-- fim checklote -->
										 
							<brArq:div id="divBotoes" styleClass=" mt-32">
								<brArq:div styleClass="col-8">
									<brHtml:brPanelGroup >
					
										<h:commandButton id="btnConfirmar"
											action="#{transferenciaConfirmacaoDadosBean.confirmar}"
											styleClass="tabindex mt-24 mb-16 mr-32 btn-default-1" title="Confirmar"
											value="Confirmar">
										</h:commandButton>
										<brHtml:brOutputLink title="Cancelar"
												value="#{propBean.config.properties['url.pix']}?CTRL=#{sessionScope['ibpf_session_ctl']}"
												target="paginaCentral" styleClass="btn-secundario tabindex">
												<h:outputText value="Cancelar" />
										</brHtml:brOutputLink>
										
										<h:commandButton title="Voltar" 
											styleClass="tabindex ml-32 mb-16 link border0" value="Voltar "
											action="#{transferenciaConfirmacaoDadosBean.voltar}">
										</h:commandButton>
									</brHtml:brPanelGroup>
								</brArq:div>
							</brArq:div>				
		</brArq:div>	

</h:form>