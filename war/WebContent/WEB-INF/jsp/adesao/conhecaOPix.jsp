<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<script language="javascript">
		jQuery(document).ready(function() {
		});
				
	</script>
	
	
<h:form id="conhecaPixForm">
	<brArq:div>
		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>
		
		<brArq:div styleClass="ml-24 mb-48 col-11">
			<!-- text area -->

			<t:div styleClass="mt-24">
				<brHtml:brOutputText styleClass="title-medio"
					value="Um novo jeito de enviar e receber dinheiro"></brHtml:brOutputText>
			</t:div>
			<t:div>
				<brHtml:brOutputText
					value="Aqui, sua empresa faz e recebe transferências só com número de celular, CNPJ e e-mail – chamados de chaves Pix."></brHtml:brOutputText>
			</t:div>
			<t:div>
				<brHtml:brOutputText
					value="E os pagamentos são por QR Codes. É só criar e enviar para quem vai fazer o pagamento!"></brHtml:brOutputText>
			</t:div>
			<t:div styleClass="mb-8">
				<brHtml:brOutputText
					value="Funciona todos os dias, 24 horas e pra qualquer banco."></brHtml:brOutputText>
			</t:div>


			<t:div >
				<brHtml:brOutputText styleClass="title-medio"
					value="Conhecendo as chaves Pix "></brHtml:brOutputText>
			</t:div>
			<t:div>
				<brHtml:brOutputText
					value="A chave é usada para receber transferências, basta informá-la pra quem vai transferir para a empresa. Ela também funciona na hora de criar QR Codes."></brHtml:brOutputText>
			</t:div>
			<t:div styleClass="mb-8">
				<brHtml:brOutputText
					value="Cada chave é vinculada a uma conta e é possível ter até 20 chaves em cada conta."></brHtml:brOutputText>
			</t:div>
			
			
			
			<t:div >
				<brHtml:brOutputText styleClass="title-medio"
					value="Quer mais facilidade? "></brHtml:brOutputText>
			</t:div>
			<t:div>
				<brHtml:brOutputText
					value="As transferências e os pagamentos são mais rápidos e mais baratos."></brHtml:brOutputText>
			</t:div>
			<t:div>
				<brHtml:brOutputText
					value="Com todas essas vantagens, não tem como ficar de fora. Pra aproveitar todo o serviço, basta ter uma chave cadastrada."></brHtml:brOutputText>
			</t:div>


			<t:div styleClass="mb-8 alignCenter">
				<brHtml:brOutputText value=""></brHtml:brOutputText>
			</t:div>

		</brArq:div>

		
	</brArq:div>	
		<brArq:div id="divBotoes">
		<brArq:div styleClass="col-5">
			<ib:itemLista styleClass="container-btn-circular pt-16">
				<h:outputLink styleClass="lnkVoltar tabindex" 
				value="#{propBean.config.properties['url.pix']}?CTRL=#{sessionScope['ibpf_session_ctl']}"
				target="paginaCentral">
					<h:outputText
						value="#{messageBundle.confirmacaoCadastro_bt_voltar}" />
				</h:outputLink>
			</ib:itemLista>
		</brArq:div>

	</brArq:div>
</h:form>
