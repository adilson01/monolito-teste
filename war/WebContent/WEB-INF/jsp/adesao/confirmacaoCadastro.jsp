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
	</script>
</f:verbatim>

<t:saveState value="#{adesaoForm}" />

<h:form id="frmValidacaoPosse">
	<brArq:div styleClass="margens_laterais">
		<f:verbatim>
			<ul class="card clearfix mt-24">
				<li class="cardList card-regular sombra-nivel200">
					<!-- INICIO CARD -->					
		
		</f:verbatim>

		<t:div styleClass="margens_laterais after mb-16" rendered="#{confirmacaoCadastroBean.confirmacao}">
			<t:div styleClass="box-sucesso after">
				<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
					<h:outputText escape="true" styleClass="tituloAlerta mt-16" value="#{confirmacaoCadastroBean.form.descricaoSucesso}" />
					<h:outputText escape="true" styleClass="mensagemAlerta"
						value="" />
				</t:div>
			</t:div>
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16" rendered="#{confirmacaoCadastroBean.pendente}">
			<t:div styleClass="box-alerta pendente after">
				<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
					<h:outputText escape="true" styleClass="tituloAlerta mt-16"
						value="#{messageBundle.confirmacaoCadastro_alerta_pendente_titulo}" />
					<h:outputText escape="true" styleClass="mensagemAlerta"
						value="#{messageBundle.confirmacaoCadastro_alerta_pendente_subtitulo}" />
				</t:div>
			</t:div>
		</t:div>
		
		
		
		
		<t:div styleClass="margens_laterais after mb-16" rendered="#{confirmacaoCadastroBean.erroOutroUsuario}">
			<t:div styleClass="box-alerta after">
				<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
					<h:outputText escape="true" styleClass="tituloAlerta mt-16"
						value="#{messageBundle.erroCadastro_aletra_outroUsuario_titulo}" />
					<h:outputText escape="true" styleClass="mensagemAlerta"
						value="#{messageBundle.erroCadastro_aletra_outroUsuario_subtitulo}" />
				</t:div>
			</t:div>
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16" rendered="#{confirmacaoCadastroBean.erroOutroPSP}">
			<t:div styleClass="box-alerta after">
				<t:div styleClass="ctn-box after tabindex tabfirst padding-titulo">
					<h:outputText escape="true" styleClass="tituloAlerta mt-16"
						value="#{messageBundle.erroCadastro_alerta_outraInstituicao_titulo}" />
					<h:outputText escape="true" styleClass="mensagemAlerta"
						value="#{messageBundle.erroCadastro_alerta_outraInstituicao_subtitulo}" />
				</t:div>
			</t:div>
		</t:div>
		
		<t:div styleClass="margens_laterais after mb-16" rendered="#{confirmacaoCadastroBean.erroOutroUsuario || confirmacaoCadastroBean.erroOutroPSP }">
			<brHtml:brCommandLink
							title="#{messageBundle.erroCadastro_bt_tentarOutraChave}"
							styleClass="tabindex ml-32 mb-16 link" immediate="true"
							target="">
							<brHtml:brOutputText
								value="#{messageBundle.erroCadastro_bt_tentarOutraChave}" />
						</brHtml:brCommandLink>
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