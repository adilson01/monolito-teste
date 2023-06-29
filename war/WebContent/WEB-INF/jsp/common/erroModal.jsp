<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib"%>
<%@ taglib uri="http://bradesco.com.br/arq_components" prefix="brArq"%>
<%@ taglib uri="http://bradesco.com.br/html_custom_components" prefix="brHtml"%>
<%@ taglib uri="http://bradesco.com.br/ib/seiLote" prefix="ibsei"%>

<t:div styleClass="margens_laterais after mb-16">
	<t:div styleClass="box-erro after">
		<t:div styleClass="ctn-box after tabindex tabfirst">
			<h:outputText escape="true" styleClass="tituloAlerta" value="Sistema indisponível no momento" />
			<h:outputText escape="true" styleClass="mensagemAlerta" value="Por favor, tente mais tarde" />
		</t:div>
	</t:div>
</t:div>