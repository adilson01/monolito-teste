package br.com.bradesco.web.ibpj.view.utils.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

/**
 * <p>
 * <b>Title:</b> Arquitetura Bradesco Canal Intranet.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * Inserir aqui a descrição do Tipo.
 * </p>
 * @author BRQ IT Services<BR/>
 *         created 10/07/2013 <BR/>
 * @version 1.0
 */
public class CNPJConverter implements
    Converter {
    public Object getAsObject(FacesContext context,
            UIComponent component,
            String value) throws ConverterException {
        /*
         * Irá converter CNPJ formatado para um sem pontos, traço e barra. Ex.
         * de retorno: 00.000.000/0001-00
         */
        String cnpj = value;
        if (value != null && !value.equals(""))
            cnpj = value.replaceAll("\\.",
                    "")
                    .replaceAll("\\-",
                            "")
                    .replaceAll("/",
                            "");

        return cnpj;
    }

    public String getAsString(FacesContext context,
            UIComponent component,
            Object value) throws ConverterException {
        /*
         * Irá converter Base de CNPJ com 8 dígitos + filial + dígito. Ex.:
         * 00.000.000/0001-00
         */
        String cnpj = "";
        if (value != null) {
            cnpj = value.toString();
            cnpj = SiteUtil.completarZerosEsquerda(14,
                    cnpj);

            cnpj = cnpj.substring(0,
                    2) + "."
                    + cnpj.substring(2,
                            5)
                    + "."
                    + cnpj.substring(5,
                            8)
                    + "/"
                    + cnpj.substring(8,
                            12)
                    + "-"
                    + cnpj.substring(12,
                            14);
        }
        return cnpj;
    }
}
