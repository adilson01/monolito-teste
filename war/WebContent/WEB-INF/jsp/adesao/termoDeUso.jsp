<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://bradesco.com.br/ib/seiLote" prefix="ibsei"%>

<t:saveState value="#{adesaoForm}" />

<h:form id="frmTermoDeUso"> 

	<ib:sei>

		<brArq:div>

			<ib:campoFormulario tipo="entrada"
				styleClass="alignLeft ml-24 mt-32 ">
				<brHtml:brOutputText styleClass="title-medio mr-16"
					value="Termo de Consentimento" />
			</ib:campoFormulario>


			<brArq:div styleClass="ml-24 mb-48 col-11">
				<!-- text area -->

				<t:div styleClass="mb-8">
					<brHtml:brOutputText
						styleClass="legenda"
						value="Ao clicar em Li e Concordo, você"></brHtml:brOutputText>
					<brHtml:brOutputText
						styleClass="p-pequeno"
						value=" autoriza "></brHtml:brOutputText>
					<brHtml:brOutputText
						styleClass="legenda"
						value="que o Banco Bradesco S.A., 
					instituição financeira de direito privado, com sede no Núcleo “Cidade de Deus”, 
					s/nº, Vila Yara, Osasco/SP, inscrito no CNPJ/MF sob o nº 60.746.948/0001-12, faça 
					o cadastro da sua conta a uma Chave Pix (Pagamentos Instantâneos), do Banco 
					Central do Brasil (BCB)."></brHtml:brOutputText>
				</t:div>

				<t:div styleClass="mb-8">
				<brHtml:brOutputText
						styleClass="p-pequeno"
						value="Você só poderá realizar um Pix a partir de 16 de novembro, "></brHtml:brOutputText>
					<brHtml:brOutputText
						 styleClass="legenda"
						value=" porém, de acordo com a regulamentação
						 do BCB, alguns clientes poderão realizar o 
						Pix em uma operação restrita do dia 03 até o dia 15 de novembro, conforme 
						disponibilidade a ser informada no ato da sua realização."></brHtml:brOutputText>
				</t:div>

				<t:div styleClass="mb-8">
					<brHtml:brOutputText
						 styleClass="legenda"
						value="Para o cadastro no Diretório de Identificadores de Contas Transacionais 
						(“DICT”) poderão ser armazenados os seguintes dados: (a) pessoa física: CPF, 
						telefone celular, e-mail e chave aleatória; (b) pessoa jurídica: CNPJ, e-mail, 
						telefone celular e chave aleatória."></brHtml:brOutputText>
				</t:div>

				<t:div styleClass="mb-8">
					<brHtml:brOutputText
						styleClass="legenda"
						value="Se no momento do cadastro a Chave Pix já tiver sido cadastrada para 
						outra conta, a efetivação do cadastro não será realizada. Porém, você poderá 
						solicitar a portabilidade ou reinvindicação da sua Chave Pix."></brHtml:brOutputText>
				</t:div>

				<t:div styleClass="mb-8">
					<brHtml:brOutputText
						styleClass="legenda"
						value="Quaisquer dúvidas relacionadas ao Pix podem ser sanadas em 
						nossa Central de Atendimento pelo telefone 4002-0022."></brHtml:brOutputText>
				</t:div>
				
				<t:div styleClass="mb-8">
				<t:div>
					<brHtml:brOutputTextBold styleClass="p-pequeno" value="Alo Bradesco"></brHtml:brOutputTextBold>
					</t:div>
					<t:div>
					<brHtml:brOutputTextBold styleClass="p-pequeno" value="SAC - Serviço de Apoio ao Cliente "></brHtml:brOutputTextBold>
					</t:div>
					<t:div>
					<brHtml:brOutputText  styleClass="legenda" value="Cancelamentos, Reclamações e Informações "></brHtml:brOutputText>
					</t:div>
					<t:div>
					<brHtml:brOutputTextBold   styleClass="p-pequeno" value="0800 704 8383 "></brHtml:brOutputTextBold>
					</t:div>
					<t:div>
					<brHtml:brOutputText  styleClass="legenda" value="Deficiente Auditivo ou de Fala - "></brHtml:brOutputText>
					<brHtml:brOutputText styleClass="p-pequeno" value="0800 722 0099 "></brHtml:brOutputText>
					</t:div>
					<t:div>
					<brHtml:brOutputText  styleClass="legenda" value="Atendimento 24 horas, 7 dias por semana "></brHtml:brOutputText>
					</t:div>
					<t:div>
					<brHtml:brOutputTextBold styleClass="p-pequeno" value="Ouvidoria - 0800 727 9933 "></brHtml:brOutputTextBold>
					</t:div>
				</t:div>
				
				<t:div styleClass="mb-8 alignCenter">
					 <brHtml:brOutputText  styleClass="legenda"  value="Atendimento de segunda a sexta-feira das 8h às 18h, exceto feriados "></brHtml:brOutputText>
				</t:div>
				
			</brArq:div>

		</brArq:div>
	</ib:sei>
	<!-- text fim area -->

	<brArq:div id="divBotoes" styleClass="mt-40 ">
		<t:div styleClass="row col-12">
			<brArq:div>
				<h:commandLink styleClass="closeModal btn-default-1 tabindex"
					target="paginaCentral" title="Selecionar Banco" id="btnConcordo"
					action="#{termoDeUsoBean.confirmar}">
					<brHtml:brOutputText value="Concordo" />
				</h:commandLink>
				<h:commandLink title="cancelar"
					styleClass="closeModal tabindex ml-32 mb-16 link" id="btnCancelar"
					target="paginaCentral" action="#{termoDeUsoBean.cancelar}">
					<brHtml:brOutputText value="Cancelar" />
				</h:commandLink>
			</brArq:div>

			<brArq:div styleClass="fr invisivelImpressao ml-48">
				<ib:seiBotoes></ib:seiBotoes>
			</brArq:div>

		</t:div>
	</brArq:div>


</h:form>
<ib:seiForm id="formularioSalvarEnviarImprimir"/>  
