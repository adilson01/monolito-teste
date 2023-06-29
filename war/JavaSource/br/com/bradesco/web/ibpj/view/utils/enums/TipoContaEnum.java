/**
 */
package br.com.bradesco.web.ibpj.view.utils.enums;


import java.io.Serializable;

import br.com.bradesco.web.ibpj.view.utils.SiteUtil;

/**
 * Capgemini. TipoContaEnum
 *
 * @author 
 * @date
 * @version 1.0
 * @see
 */
public enum TipoContaEnum implements Serializable {

	CORRENTE(1, "conta-corrente"),
	
	POUPANCA(2, "conta-poupan�a");

	/** Atributo codigo. */
	private int codigo = 0;
	
	/** Atributo descricao. */
	private String descricao = null;
	
	/**
	 * Construtor Padr�o
	 * Prop�sito: Instancia um novo objeto TipoConta.
	 *
	 * @param paramCodigo the param codigo
	 * @param paramDescricao the param descricao
	 * @see
	 */
	private TipoContaEnum(int paramCodigo, String paramDescricao) {
		this.codigo = paramCodigo;
		this.descricao = paramDescricao;
	}

	/**
	 * Nome: getCodigo
	 * Prop�sito: Recupera o valor do atributo 'codigo'.
	 *
	 * @return valor do atributo 'codigo'
	 * @see
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Nome: setCodigo
	 * Prop�sito: Registra o valor do atributo 'codigo'.
	 *
	 * @param codigo valor do atributo codigo
	 * @see
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	/**
	 * Nome: getDescricao
	 * Prop�sito: Recupera o valor do atributo 'descricao'.
	 *
	 * @return valor do atributo 'descricao'
	 * @see
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Nome: setDescricao
	 * Prop�sito: Registra o valor do atributo 'descricao'.
	 *
	 * @param descricao valor do atributo descricao
	 * @see
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Nome: getCodigoDescricao
	 * Prop�sito: Recupera o valor do atributo 'codigoDescricao'.
	 *
	 * @return valor do atributo 'codigoDescricao'
	 * @see
	 */
	public String getCodigoDescricao() {
		return codigo + " - " + descricao;
	}
	
	/**
	 * Nome: toString
	 * Prop�sito: To string.
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
	 * Prop�sito: Recupera o valor do atributo 'byCodigo'.
	 *
	 * @param codigo the codigo
	 * @return valor do atributo 'byCodigo'
	 * @see
	 */
	public static TipoContaEnum getByCodigo(int codigo) {

        if (SiteUtil.isEmptyOrNull(codigo)) {
            return null;
        }
        for (TipoContaEnum enumRetorno : TipoContaEnum.values()) {
            if (enumRetorno.codigo == codigo) {
                return enumRetorno;
            }
        }
        return null;
	}	
	
	/**
	 * Nome: getByCodigo
	 * Prop�sito: Recupera o valor do atributo 'byCodigo'.
	 *
	 * @param codigo the codigo
	 * @return valor do atributo 'byCodigo'
	 * @see
	 */
	public String getDescricaoPorCod(int codigo) {

        if (SiteUtil.isEmptyOrNull(codigo)) {
            return "";
        }
        for (TipoContaEnum enumRetorno : TipoContaEnum.values()) {
            if (enumRetorno.codigo == codigo) {
                return enumRetorno.getDescricao();
            }
        }
        return "";
	}
}
