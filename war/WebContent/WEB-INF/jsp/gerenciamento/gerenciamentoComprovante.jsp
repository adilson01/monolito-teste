
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
	
	
<h:form id="exibirMensagemExcluirForm">
	<brArq:div>
		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->
		</f:verbatim>

		<t:div styleClass="mt-8">
				<t:div id="exclusaoSucesso" rendered="#{gerenciamentoComprovanteBean.sucesso}">
					<t:div styleClass="margens_laterais after mb-16">
						<t:div styleClass="box-sucesso after">
							<t:div
								styleClass="ctn-box after tabindex tabfirst padding-titulo">
								<h:outputText escape="true" styleClass="tituloAlerta mt-16"	value="#{gerenciamentoComprovanteBean.descricaoSucesso}" />
								<h:outputText escape="true" styleClass="mensagemAlerta" value="" />
							</t:div>
						</t:div>
					</t:div>
				</t:div>

				<brArq:div id="exclusaoInsucesso" rendered="#{gerenciamentoComprovanteBean.insucesso}">
					<t:div styleClass="margens_laterais after mb-16">
						<t:div styleClass="box-erro after">
							<t:div
								styleClass="ctn-box after tabindex tabfirst padding-titulo">
								<h:outputText escape="true" styleClass="tituloAlerta mt-16"
									value="Erro na exclus�o da Chave PIX" />
								<h:outputText escape="true" styleClass="mensagemAlerta" value="" />
							</t:div>
						</t:div>
					</t:div>
				</brArq:div>

				<brArq:div id="exclusaoPendente" rendered="#{gerenciamentoComprovanteBean.pendente}">
					<t:div styleClass="margens_laterais after mb-16">
						<t:div styleClass="box-alerta pendente after">
							<t:div
								styleClass="ctn-box after tabindex tabfirst padding-titulo">
								<h:outputText escape="true" styleClass="tituloAlerta mt-16"
									value="Chave PIX pendente para exclus�o" />
								<h:outputText escape="true" styleClass="mensagemAlerta" value="" />
							</t:div>
						</t:div>
					</t:div>
				</brArq:div>
		</t:div>
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






