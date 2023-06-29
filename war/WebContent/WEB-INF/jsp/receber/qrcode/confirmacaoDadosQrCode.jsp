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

<h:form id="confirmacaoDadosQrCodeForm">

	<brArq:div>
		<h:messages showDetail="true"></h:messages>

		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>

		<brArq:div styleClass="ml-40 ">
			<ib:campoFormulario styleClass="mb-16 " tipo="entrada">
				<!--  -->
				<brHtml:brOutputText styleClass="p-pequeno "
					value="#{messageBundle.dados_da_empresa}" />
			</ib:campoFormulario>
		</brArq:div>

		<brArq:div styleClass=" mt-16 mb-48">
			<!--  cabecalho inicio -->


			<brArq:div styleClass="mb-16 row ">
				<!-- info  -->

				<brArq:div styleClass=" col-3  alinhamento-direita">
					<brHtml:brOutputText styleClass="legenda ml-56"
						value="#{messageBundle.empresa_nome} | #{messageBundle.cnpj_empresa}" />
				</brArq:div>
				<brArq:div styleClass="col-9">
					<brHtml:brOutputText styleClass="legenda ml-32"
						value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.empresa.nomeEmpresa} | " />
					<brHtml:brOutputText styleClass="legenda"
						value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.empresa.cnpj.cnpjFormatado}" />
				</brArq:div>

			</brArq:div>
			<!-- fim info -->


			<brArq:div styleClass="mb-16 row ">
				<!-- info conta debito -->

				<brArq:div styleClass=" col-3  alinhamento-direita">
					<brHtml:brOutputText styleClass="legenda"
						value="#{messageBundle.conta_debito} " />
				</brArq:div>

				<brArq:div styleClass="col-9">
					<brHtml:brOutputText styleClass="legenda ml-32 "
						value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.conta.agencia} | #{messageBundle.conta_numero} #{confirmacaoDadosQrCodeBean.gerenciamentoForm.conta.numConta}-#{confirmacaoDadosQrCodeBean.digito}" />
					<brHtml:brOutputText styleClass="legenda ml-8 "
						value="| #{messageBundle.tipo_conta} conta-corrente" />
				</brArq:div>

			</brArq:div>
			<!-- fim info -->


		</brArq:div>
		<!-- cabecalho fim -->


		<t:htmlTag value="hr" styleClass="mt-24 mb-24" />
		<!-- linha meio -->

		<brArq:div styleClass="ml-40 ">
			<ib:campoFormulario styleClass="mb-16 " tipo="entrada">
				<!--  -->
				<brHtml:brOutputText styleClass="p-pequeno "
					value="#{messageBundle.label_dados_chave_qr_code}" />
			</ib:campoFormulario>
		</brArq:div>


		<!-- info celular/email-->
		<brArq:div id="tipoChaveSelecaoExclusaoCelularEmail" styleClass="row">

			<brArq:div styleClass="col-3 alinhamento-direita">
				<brHtml:brOutputText styleClass="legenda ml-56"
					value="#{messageBundle.label_chave}:" />
			</brArq:div>
			<brArq:div styleClass="col-9">
				<brHtml:brOutputText styleClass="legenda ml-32"
					value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.chaveSelecionada.chave}"
					rendered="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.chaveSelecionada.tipoChave != 5}" />
				<brHtml:brOutputText styleClass="legenda ml-32"
					value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.chaveSelecionada.iapldoAdsaoSpi}"
					rendered="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.chaveSelecionada.tipoChave == 5}" />
			</brArq:div>
		</brArq:div>
		<!-- fim celular/email-->

		<!-- info valor-->
		<brArq:div id="valor" styleClass="row">
			<brArq:div styleClass=" col-3  alinhamento-direita">
				<brHtml:brOutputText styleClass="legenda ml-56"
					value="#{messageBundle.label_inserir_valor}:" />
			</brArq:div>
			<brArq:div styleClass="col-9">
				<brHtml:brOutputText styleClass="legenda ml-32"
					value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.empresa.nomeEmpresa}" />
			</brArq:div>
		</brArq:div>
		<!-- fim valor -->

		<!-- info identificador-->
		<brArq:div id="identificador" styleClass="row">
			<brArq:div styleClass=" col-3  alinhamento-direita">
				<brHtml:brOutputText styleClass="legenda ml-56"
					value="#{messageBundle.label_identificador}:" />
			</brArq:div>
			<brArq:div styleClass="col-9">
				<brHtml:brOutputText styleClass="legenda ml-32"
					value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.empresa.nomeEmpresa}" />
			</brArq:div>
		</brArq:div>
		<!-- fim identificador -->

		<!-- info descricao-->
		<brArq:div id="descricao" styleClass="row">
			<brArq:div styleClass=" col-3  alinhamento-direita">
				<brHtml:brOutputText styleClass="legenda ml-56"
					value="#{messageBundle.label_inserir_descricao}:" />
			</brArq:div>
			<brArq:div styleClass="col-9">
				<brHtml:brOutputText styleClass="legenda ml-32"
					value="#{confirmacaoDadosQrCodeBean.gerenciamentoForm.empresa.nomeEmpresa}" />
			</brArq:div>
		</brArq:div>
		<!-- fim descricao -->

		<brArq:div>
			<brArq:div id="divBotoes" styleClass=" mt-32 ml-24">
				<brArq:div styleClass="col-5">
					<brHtml:brPanelGroup>

						<h:commandButton id="btnConfirmar"
							action="#{confirmacaoDadosQrCodeBean.botaoConfirmar}"
							styleClass="tabindex mb-16 btn-default-1" title="Confirmar"
							value="Confirmar">
						</h:commandButton>
						<h:commandButton title="Voltar"
							styleClass="tabindex ml-32 mb-16 link border0" value="voltar"
							action="#{confirmacaoDadosQrCodeBean.botaoCancelar}">
						</h:commandButton>
					</brHtml:brPanelGroup>
				</brArq:div>
			</brArq:div>
		</brArq:div>

	</brArq:div>
</h:form>