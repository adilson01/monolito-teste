<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %> 
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://bradesco.com.br/ib/components" prefix="ib" %>
<%@ taglib uri="http://bradesco.com.br/ib/componentes/comprovante" prefix="ibc"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:useAttribute name="css_sei" classname="java.util.List" scope="request" ignore="true"/>
<tiles:useAttribute name="js_sei" classname="java.util.List" scope="request" ignore="true"/>

<style>

.ne-tabela-expansivel .baseSac {
	width: 100%;
}
</style>

<h:form id="form">
       <t:div>
             <ib:sei>     
                    <ibc:comprovante dados="#{comprovantePendenteBean.comprovante}" tipo="transacao" renderBanner="false"/>
             </ib:sei>
             <ib:seiForm />      
       </t:div>
</h:form>
