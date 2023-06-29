<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<t:saveState value="#{adesaoForm}" />

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {
		});
	</script>
</f:verbatim>

<h:form id="confirmacaoDadosForm">

	<brArq:div>

		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		<!--  chave/celular -->
		
		<t:div styleClass="margens_laterais after mr-24 mb-24 mt-24 ml-40" rendered="#{confirmacaoDadosBean.form.erroIntencao}">
			<t:div styleClass="box-erro after">
				<t:div styleClass="ctn-box after tabindex tabfirst">
					<h:outputText escape="true" styleClass="tituloAlerta" value="Sistema indisponível no momento" />
					<h:outputText escape="true" styleClass="mensagemAlerta"	value="#{confirmacaoDadosBean.mensagemErroIntencao}" />
				</t:div>
			</t:div>
		</t:div>
		
		<brArq:div  styleClass=" mt-16 mb-48 ml-32" id="grupoInformacoesDaempresaExclusao"><!--  cabecalho inicio -->
							
										  
							<brArq:div styleClass="mb-16 row "><!-- Nome da Empresa e CNPJ  -->
									
								<brArq:div styleClass=" ">
									 <brHtml:brOutputText  styleClass="legenda " value="#{messageBundle.empresa_nome} | #{messageBundle.cnpj_empresa}"/>
								</brArq:div>			
								<brArq:div styleClass="">	
										<brHtml:brOutputText styleClass="legenda ml-8" value="#{confirmacaoDadosBean.form.empresa.nomeEmpresa} | " />
							<brHtml:brOutputText styleClass="legenda" value="#{confirmacaoDadosBean.form.empresa.cnpj.cnpjFormatado}" />
								</brArq:div>
														
							 </brArq:div><!-- fim Nome da Empresa e CNPJ -->
					
							 
							<brArq:div styleClass="mb-16 row "><!-- Informações agencia/conta/tipoconta -->
									
								<brArq:div styleClass=" ">
									 <brHtml:brOutputText styleClass="legenda"  value="#{messageBundle.conta_debito} "/>
								</brArq:div>	
										
								<brArq:div styleClass="">	
									<brHtml:brOutputText styleClass="legenda ml-8 "  value="#{confirmacaoDadosBean.form.adesaoModel.agencia} | #{messageBundle.conta_numero} #{confirmacaoDadosBean.form.adesaoModel.conta}-#{confirmacaoDadosBean.form.adesaoModel.dac}"/>
									<brHtml:brOutputText styleClass="legenda ml-8 "  value="| #{messageBundle.tipo_conta} #{confirmacaoDadosBean.form.tipoConta.descricao}"/>
								</brArq:div>
														
							</brArq:div><!--  Informações agencia/conta/tipoconta fim -->
										  
										
				</brArq:div>	<!-- cabecalho fim -->					

		<t:htmlTag value="hr" styleClass="mt-24 mb-24" />
		<!-- linha meio -->
 
		<brArq:div styleClass="ml-40 ">
			<ib:campoFormulario styleClass="ml-16" tipo="entrada" id="chaveIndentificacao">
				<!--  comeco chave/celular -->
				<brHtml:brOutputText styleClass="p-pequeno ml-8"
					value="#{messageBundle.dados_chave}" />
			</ib:campoFormulario>
		</brArq:div>

		<!-- informações tipo de chave  celular/email-->
				<brArq:div id="tipoChaveSelecao" styleClass="row mb-24 ml-56 ">
				
						<brArq:div styleClass="">
							<brHtml:brOutputText styleClass="legenda "
								value="#{confirmacaoDadosBean.form.chaveEnderecamento.descricao}:" />
						</brArq:div>	
						<brArq:div styleClass="">			
							<brHtml:brOutputText styleClass="legenda ml-8"
								value="#{confirmacaoDadosBean.form.ddiFormatado} #{confirmacaoDadosBean.form.chave}" />
						</brArq:div>			
				</brArq:div>
				<!--  informações tipo de chave  celular/email fim-->

		<t:htmlTag value="hr" styleClass="mb-24" />
		<!-- linha meio -->


				<brArq:div styleClass="mb-24 col-10">
						<t:div styleClass="">
							<brHtml:brOutputText styleClass="legenda" value="Ao registrar uma chave Pix, serão armazenados e disponibilizados aos usuários que consultarem essa chave, no momento do envio de um Pix para a empresa, os seguintes dados:" />
						</t:div>
						<t:div styleClass="mt-8 ">
							<brHtml:brOutputText styleClass="legenda" value="- Nome da empresa" />
						</t:div>
						<t:div styleClass=" ">
							<brHtml:brOutputText styleClass="legenda" value="- CNPJ" />
						</t:div>
						<t:div styleClass="">
							<brHtml:brOutputText styleClass="legenda" value="- O nome do prestador de serviços ao qual a chave está vinculada" />
						</t:div>
				</brArq:div>
		
		<t:div styleClass="row">
			<t:div styleClass="">
				<brHtml:brSelectBooleanCheckbox id="checkTermoUso"
					value="#{confirmacaoDadosBean.form.termoUsoLido}"
					styleClass="tabindex">
				</brHtml:brSelectBooleanCheckbox>
				<brHtml:brOutputText styleClass="p-medio "
					value=" #{messageBundle.leitura_termos}">
				</brHtml:brOutputText>
			
				<brHtml:brCommandLink title="#{messageBundle.termo_uso}"
					id="termoDeUso" action="#{confirmacaoDadosBean.termoDeUso}"
					styleClass="tabindex mb-24 link"
					style="font:16px montserratsemibold;" 
					target="modal_infra_estrutura">
					<h:outputText value="#{messageBundle.termo_uso}" />
				</brHtml:brCommandLink>
			</t:div>
		</t:div>
		
		<brArq:div styleClass="invisivelImpressao"><!-- botões de voltar e confirmar -->
						<brArq:div id="divBotoes" styleClass="radioCustom mt-32">
							<brArq:div styleClass="col-5">
								<brHtml:brPanelGroup>
			
									<h:commandButton id="btnConfirmar" rendered="#{!confirmacaoDadosBean.form.erroIntencao}"
										action="#{confirmacaoDadosBean.confirmar}"
										disabled="#{confirmacaoDadosBean.form.botaoConfirmarDadosAdesao}"
										styleClass="tabindex mb-16 btn-default-1" title="Confirmar"
										value="Confirmar">
									</h:commandButton>
									<h:commandButton title="Voltar" style="border:0"
										styleClass="tabindex ml-32 mb-16 link" value="Voltar"
										action="#{confirmacaoDadosBean.voltar}">
									</h:commandButton>
								</brHtml:brPanelGroup>
							</brArq:div>
						</brArq:div>
					</brArq:div><!-- botões de voltar e confirmar fim --> 
		
	</brArq:div>
</h:form>