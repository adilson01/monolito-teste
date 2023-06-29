<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components"
	prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/lote_component" prefix="lote"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<a4j:jsFunction name="ocultarSaldoClass"
	action="#{transferenciaForm.ocultarSaldo}"
	reRender="selecaoAgenciaContaForm:idOcultarSaldo" immediate="true"></a4j:jsFunction>

<a4j:jsFunction name="mostrarSaldo"
	action="#{transferenciaForm.mostrarSaldo}"
	reRender="selecaoAgenciaContaForm:idOcultarSaldo" immediate="true"></a4j:jsFunction>

<t:div styleClass="col-12 horariosLimites">

	<brArq:div id="pai2">
		<brArq:div id="idDivAgenciaSaldo">
			<brArq:brDiv styleClass="mb-16">
				<brHtml:brOutputText styleClass="p-medio" value="PIX" />
			</brArq:brDiv>

			<ib:lista>
				<ib:itemLista>
					<h:outputText styleClass="rotulo" value="Agência | Conta:" />
					<h:outputText
						value="#{transferenciaForm.contaFormatadaSelecionada}"
						styleClass="itens-lista-direita rotulo" />
				</ib:itemLista>
				<ib:itemLista styleClass="linha mt-16">
					<h:outputText styleClass="rotulo" value="Saldo:" />
					<h:outputText id="idSaldo"
						value="R$ #{transferenciaForm.horariosSaldoLimiteModel.saldo}"
						styleClass="itens-lista-direita p-pequeno" />
				</ib:itemLista>
			</ib:lista>

		</brArq:div>

		<t:htmlTag value="hr" style="width:100%" styleClass="mt-16 mb-16" />

		<ib:lista>
			<ib:itemLista>
				<h:outputText styleClass="rotulo" value="Limite diário:" />
				<h:outputText
					value="R$ #{transferenciaForm.horariosSaldoLimiteModel.limiteDiario}"
					styleClass="itens-lista-direita p-pequeno " />
			</ib:itemLista>
			<ib:itemLista styleClass="linha mt-16">
				<h:outputText styleClass="rotulo" value="Limite disponível:" />
				<h:outputText
					value="R$ #{transferenciaForm.horariosSaldoLimiteModel.limiteDisponivel}"
					styleClass="itens-lista-direita p-pequeno " />
			</ib:itemLista>
		</ib:lista>


		<t:htmlTag value="hr" styleClass="mt-16 mb-16" />
		<!-- linha meio -->

		<brArq:div>
			<t:div styleClass="row">
				<brArq:div>
					<brArq:div styleClass="ico_relogio"></brArq:div>
				</brArq:div>
				<brArq:div styleClass="legenda  ml-8 mt-4">
					<brHtml:brOutputText value="24h" />
				</brArq:div>
			</t:div>
		</brArq:div>

		<f:verbatim>
			</li>
			</ul>
			<!-- FIM CARD -->
		</f:verbatim>
	</brArq:div>

</t:div>