package br.com.bradesco.web.ibpj.service.business.exceptions;

import br.com.bradesco.web.aq.application.error.BradescoApplicationException;

/**
 * Exce��o dos servi�os de transa��o de autoriza��o/desautoriza��o
 */
public class TransacaoException extends BradescoApplicationException {
    /** Serial Version UID */
    private static final long serialVersionUID = 7772551989270181929L;
    
    /**
     * Construtor
     * 
     * @param msg
     * @param cause
     */
    public TransacaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
