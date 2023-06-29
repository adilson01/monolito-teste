/**
 */
package br.com.bradesco.web.ibpj.view.utils.enums;


import java.io.Serializable;

import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

/**
 * Capgemini. TipoChaveEnum
 *
 * @author 
 * @date
 * @version 1.0
 * @see
 */
public enum TipoChaveEnum implements Serializable {

	/** Atributo Celular. */
	CELULAR(1, "Celular"),
	
	/** Atributo E-mail. */
	EMAIL(2, "E-mail"),
	
	/** Atributo CNPJ. */
	CNPJ(3, "CNPJ"),
	
	/** Atributo chave aleatoria. */
	EVP(4, "Chave aleatória");

	/** Atributo codigo. */
	private int codigo = 0;
	
	/** Atributo descricao. */
	private String descricao = null;
	
	/**
	 * Construtor Padrão
	 * Propósito: Instancia um novo objeto TipoChaveEnum.
	 *
	 * @param paramCodigo the param codigo
	 * @param paramDescricao the param descricao
	 * @see
	 */
	private TipoChaveEnum(int paramCodigo, String paramDescricao) {
		this.codigo = paramCodigo;
		this.descricao = paramDescricao;
	}

	/**
	 * Nome: getCodigo
	 * Propósito: Recupera o valor do atributo 'codigo'.
	 *
	 * @return valor do atributo 'codigo'
	 * @see
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Nome: setCodigo
	 * Propósito: Registra o valor do atributo 'codigo'.
	 *
	 * @param codigo valor do atributo codigo
	 * @see
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	/**
	 * Nome: getDescricao
	 * Propósito: Recupera o valor do atributo 'descricao'.
	 *
	 * @return valor do atributo 'descricao'
	 * @see
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Nome: setDescricao
	 * Propósito: Registra o valor do atributo 'descricao'.
	 *
	 * @param descricao valor do atributo descricao
	 * @see
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Nome: getCodigoDescricao
	 * Propósito: Recupera o valor do atributo 'codigoDescricao'.
	 *
	 * @return valor do atributo 'codigoDescricao'
	 * @see
	 */
	public String getCodigoDescricao() {
		return codigo + " - " + descricao;
	}
	
	/**
	 * Nome: toString
	 * Propósito: To string.
	 *
	 * @return string
	 * @see
	 */
	@Override
	public String toString() {
		return getDescricao();
	}
	
	/**
	 * Nome: getByCodigo
	 * Propósito: Recupera o valor do atributo 'byCodigo'.
	 *
	 * @param codigo the codigo
	 * @return valor do atributo 'byCodigo'
	 * @see
	 */
	public static TipoChaveEnum getByCodigo(int codigo) {

        if (SiteUtil.isEmptyOrNull(codigo)) {
            return null;
        }
        for (TipoChaveEnum enumRetorno : TipoChaveEnum.values()) {
            if (enumRetorno.codigo == codigo) {
                return enumRetorno;
            }
        }
        return null;
	}	
	
	/**
	 * Nome: getByCodigo
	 * Propósito: Recupera o valor do atributo 'byCodigo'.
	 *
	 * @param codigo the codigo
	 * @return valor do atributo 'byCodigo'
	 * @see
	 */
	public String getDescricaoPorCod(int codigo) {

        if (SiteUtil.isEmptyOrNull(codigo)) {
            return "";
        }
        for (TipoChaveEnum enumRetorno : TipoChaveEnum.values()) {
            if (enumRetorno.codigo == codigo) {
                return enumRetorno.getDescricao();
            }
        }
        return "";
	}
}
