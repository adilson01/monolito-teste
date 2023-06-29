package br.com.bradesco.web.ibpj.view.bundle;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import br.com.bradesco.web.aq.application.util.BradescoCommonServiceFactory;
import br.com.bradesco.web.aq.application.util.faces.BradescoFacesUtils;

public class MessagesPixResourceBundle extends ResourceBundle {
    /** Armazena as mensagens */
    private static ResourceBundle bundle;
    
    /**
     * Obter a refer�ncia para o Bundle.
     *
     * @return ResourceBundle contendo a refer�ncia
     */
    public static ResourceBundle getInstance() {
        MessagesPixResourceBundle.inicializarBundle();
        return MessagesPixResourceBundle.bundle;
    }
    
    @Override
    public Enumeration<String> getKeys() {
        return null;
    }
    
    @Override
    protected Object handleGetObject(String key) {
        String valor = key;
        try {
            MessagesPixResourceBundle.inicializarBundle();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (UIComponentTag.isValueReference(valor)) {
                ValueBinding valueBinding = facesContext.getApplication().createValueBinding(key);
                valor = (String) valueBinding.getValue(facesContext);
            }
            valor = bundle.getString(valor);
        } catch (Exception exception) {
            BradescoCommonServiceFactory.getLogManager().error(this, "Problemas para encontrar a chave (" + valor + ")",
                    exception);
        }
        // Em caso de n�o encontrar retorna a chave procurada
        return valor;
    }
    
    /**
     * <p>
     * Obt�m o class loader.
     * </p>
     *
     * @return Class loader
     */
    private static ClassLoader getClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }
    
    /**
     * <p>
     * Inicializa o bundle de manuten��o.
     * </p>
     *
     * @see
     */
    private static void inicializarBundle() {
        if (MessagesPixResourceBundle.bundle == null) {
            try {
                Locale locale = BradescoFacesUtils.getLocale();
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                bundle = ResourceBundle.getBundle(
                        MessagesPixResourceBundle.class.getPackage().getName() + "." + "Messages", locale,
                        getClassLoader());
            } catch (Exception exception) {
                BradescoCommonServiceFactory.getLogManager().error(MessagesPixResourceBundle.class,
                        "N�o foi poss�vel carregar o bundle com as mensagens", exception);
            }
        }
    }
}