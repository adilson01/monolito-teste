<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:verbatim>
	<script language="javascript">
		jQuery(document).ready(function() {

		});
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<brArq:div styleClass="fr invisivelImpressao">
	<ib:seiBotoes></ib:seiBotoes>
</brArq:div>

<h:form id="frmValidacaoPosse">

	<a4j:jsFunction name="novaTransferencia"
		actionListener="#{transferenciaComprovanteBean.novaTransferencia}">
	</a4j:jsFunction>

	<brArq:div styleClass="margens_laterais mt-48">

		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD 1 -->
		</f:verbatim>


		<t:div styleClass="margens_laterais after mb-16">
			<t:div styleClass="box-sucesso after">
				<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
					<h:outputText escape="true" styleClass="tituloAlerta mt-16"
						value="Transferência concluída" />
					<h:outputText escape="true" styleClass="mensagemAlerta" value="" />
				</t:div>
			</t:div>


			<f:verbatim>
				<ul class="card clearfix mt-24">
					<li class="cardList card-regular sombra-nivel200">
						<!-- INICIO CARD 2 -->
			</f:verbatim>

			<t:div styleClass="margens_laterais after mb-16">

				<!-- HEADER INICIO -->

				<t:div styleClass="headerConclusao" style=" width:100%;">

					<t:div
						style="width: 30.3%;height: 100px;display: inline-block;float: left;">

						<t:graphicImage value="facelift/logo.png" />

					</t:div>

					<t:div
						style="width:39.3%;height: 100px;display: inline-block;float: left;text-align:center;">
						<brArq:div>
							<brHtml:brOutputText styleClass="title-medio"
								value="Comprovante da transferência - PIX" />
						</brArq:div>
					</t:div>

					<t:div
						style="width: 30.34%; display: inline-block; text-align:right">


						<ib:lista styleClass="lista">

							<ib:itemLista style="padding:0px">
								<t:div styleClass="itens-lista-esquerda">
									<h:outputText styleClass="legenda mr-24"
										value="Data da operação" />
									<h:outputText styleClass="legenda " value="Horário" />
								</t:div>
							</ib:itemLista>
							<ib:itemLista style="padding:0px">
								<t:div styleClass="itens-lista-esquerda">
									<h:outputText styleClass="p-pequeno mr-40"
										value="#{transferenciaComprovanteBean.dtOperacao}" />
									<h:outputText styleClass="p-pequeno ml-16"
										value="#{transferenciaComprovanteBean.horarioOperacao}" />
								</t:div>
							</ib:itemLista>

						</ib:lista>

					</t:div>

				</t:div>

				<!-- HEADER FIM  -->

				<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />

				<!-- SUB-HEADER INICIO  -->

				<t:div styleClass="headerConclusao" style=" width:100%;">

					<t:div
						style="width: 30.3%;height: 50px;display: inline-block;float: left;">
						<t:div styleClass="ml-8">

							<ib:lista styleClass="lista">

								<ib:itemLista style="padding:0px">

									<h:outputText styleClass="legenda" value="Operação: " />
									<h:outputText styleClass="p-pequeno"
										value="#{transferenciaComprovanteBean.tipoOperacao}" />

								</ib:itemLista>

							</ib:lista>

						</t:div>
					</t:div>

					<t:div
						style="width:39.3%;height: 50px;display: inline-block;float: left;text-align:center;">
						<brArq:div>

							<ib:lista styleClass="lista">

								<ib:itemLista style="padding:0px">

									<h:outputText styleClass="legenda" value="CNTRL: " />
									<h:outputText styleClass="p-pequeno"
										value="#{transferenciaComprovanteBean.cntrl}" />

								</ib:itemLista>

							</ib:lista>

						</brArq:div>
					</t:div>

					<t:div
						style="width: 30.34%;height: 50px; display: inline-block;float: left;text-align:center;">
						<brArq:div styleClass="ml-40">

							<ib:lista styleClass="lista">

								<ib:itemLista style="padding:0px">
									<h:outputText styleClass="legenda ml-56" value="Documento: " />
									<h:outputText styleClass="p-pequeno"
										value="#{transferenciaComprovanteBean.numDocumento}" />
								</ib:itemLista>

							</ib:lista>

						</brArq:div>
					</t:div>

				</t:div>

				<!-- SUB-HEADER FIM  -->


				<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />

				<!-- BODY  -->
				<!-- Dados do pagador INICIO  -->

				<brHtml:brOutputText styleClass="title-medio"
					value="Dados da empresa" />
				<brArq:brDiv id="dadosEmpresa" styleClass="mt-16">

					<h:panelGroup  binding="#{transferenciaComprovanteBean.dadosEmpresa}"></h:panelGroup>


				</brArq:brDiv>
				<!-- Dados do pagador  FIM -->

				<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />

				<!--Dados do recebedor Inicio -->
				<brArq:brDiv
					rendered="#{transferenciaComprovanteBean.habilitaDadosRecebedor}">

					<brHtml:brOutputText styleClass="title-medio"
						value="Dados de quem recebeu" />
					<brArq:brDiv id="dadosRecebedor" styleClass="mt-16">

						<h:panelGroup  binding="#{transferenciaComprovanteBean.dadosRecebedor}"></h:panelGroup>


					</brArq:brDiv>

					<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />
				</brArq:brDiv>
				<!--Dados do recebedor Fim -->

				<!--Dados da transferencia Inicio -->

				<brHtml:brOutputText styleClass="title-medio"
					value="Dados da transferencia" />
				<brArq:brDiv id="dadosTransferencia" styleClass="mt-16">

					<h:panelGroup  binding="#{transferenciaComprovanteBean.dadosTransacao}"></h:panelGroup>


				</brArq:brDiv>
				<!--Dados da transferencia Fim -->

				<!-- Body FIM  -->
				<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />

				<!-- Autenticação INICIO  -->

				<brArq:brDiv styleClass="mt-24 alinhamento-centro" id="autenticacao">

					<brHtml:brOutputText styleClass="title-medio" value="Autenticação" />

				</brArq:brDiv>

				<brArq:brDiv style="text-align:center" id="autenticacao">

					<h:panelGroup  binding="#{transferenciaComprovanteBean.dadosAutenticacao}"></h:panelGroup>

				</brArq:brDiv>
				<!-- Autenticação FIM  -->

				<t:htmlTag value="hr" style="width:100%" styleClass="mt-24 mb-24" />


				<!-- FOOTER INICIO  -->

				<brArq:div style="width: 100%; display: inline-block;float: left;">

					<brArq:div styleClass="mr-42"
						style=" width: 60%; display: inline-block;float: left;">

						<ib:lista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="p-pequeno"
									value="#{messageBundle.label_footer_sac}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_alo}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_deficiencia}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_cancelamento}" />
							</ib:itemLista>

						</ib:lista>

					</brArq:div>

					<brArq:div style="width: 40%; display: inline-block;float: left;">

						<ib:lista styleClass="ml-56">

							<ib:itemLista>
								<brHtml:brOutputText styleClass="p-pequeno"
									value="#{messageBundle.label_footer_ouvidoria}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_telefone}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_dias}" />
							</ib:itemLista>

							<ib:itemLista>
								<brHtml:brOutputText styleClass="legenda"
									value="#{messageBundle.label_footer_demais}" />
							</ib:itemLista>

						</ib:lista>

					</brArq:div>

				</brArq:div>
				<!-- FOOTER FIM  -->

			</t:div>

		</t:div>


		<f:verbatim>
			</li>
			</ul>
			<!--  FIM CARD 2 -->
		</f:verbatim>
		<f:verbatim>
			</li>
			</ul>
			<!--  FIM CARD 1 -->
		</f:verbatim>


	</brArq:div>


	<brArq:div id="divBotoes" styleClass="margens_laterais">

		<brArq:div>
			<brArq:div styleClass="fr invisivelImpressao ml-48 mt-16">
				<ib:seiBotoes></ib:seiBotoes>
			</brArq:div>

			<ib:lista id="linksRodape" styleClass="listaVoltar mtb10">

				<ib:itemLista styleClass="container-btn-circular pt-16">

					<%-- <h:outputLink styleClass="lnkVoltar tabindex" onclick="novaTransferencia()">
						<h:outputText value="#{messageBundle.label_nova_transferencia}"/>
					</h:outputLink> --%>

					<h:commandLink title="voltar" style="border:0"
						styleClass="lnkVoltar tabindex"
						value="#{messageBundle.label_nova_transferencia}"
						action="#{transferenciaComprovanteBean.transferido}">
					</h:commandLink>

				</ib:itemLista>

				<ib:itemLista styleClass="container-btn-circular pt-16">
				
					<h:outputLink styleClass="lnkVoltar tabindex"
					value="#{propBean.config.properties['url.pix']}?CTRL=#{sessionScope['ibpf_session_ctl']}"
					target="paginaCentral">
						<h:outputText value="#{messageBundle.label_voltar_comprovante}" />
					</h:outputLink>
				</ib:itemLista>

			</ib:lista>

		</brArq:div>

	</brArq:div>


</h:form>