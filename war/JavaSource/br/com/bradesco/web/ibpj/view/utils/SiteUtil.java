
package br.com.bradesco.web.ibpj.view.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import util.format.FormatadorString;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public final class SiteUtil {

	private static final String MASCARA_VALOR = "#,##0.00";
	
    /** Atributo decimal simbolos. */
    private static DecimalFormatSymbols decimalSimbolos = null;
	 
	/**
	 * 
	 * Construtor privado da classe SiteUtil.
	 * 
	 */
	private SiteUtil() {
	}
	
	public static HttpServletRequest getRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpServletRequest) facesContext.getExternalContext().getRequest();
	}
	
	public static String getUserAgent() {
		try {
			return getRequest().getHeader("User-Agent");
		} catch (Exception e) {
			return "ERRO";
		}
	}

	/**
	 * Nome: isEmptyOrNull Propósito: Verifica se é empty or null.
	 *
	 * @param object
	 *            the object
	 * @return true, se for empty or null senão retorna false
	 * @see
	 */
	public static boolean isEmptyOrNull(Object object) {

		// Verifica se a instância é do tipo coleção
		if (object instanceof Collection<?>) {
			Collection<?> list = (Collection<?>) object;
			// Condição
			return (list == null) || 0 == list.size();
		} else {
			// retorna instãncia de um objeto
			return (object == null) || object.toString().trim().equals("");
		}
	}

	/**
	 * Nome: tratarParametroString Propósito: Tratar parametro string.
	 *
	 * @param param
	 *            the param
	 * @return string
	 * @exception @throws
	 * @see
	 */
	public static String tratarParametroString(String param) {
		try {
			if (SiteUtil.isEmptyOrNull(param)) {
				return "";
			} else {
				return param;
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static String tratarParametroString(Integer param) {
		try {
			return tratarParametroString(param.toString());
		} catch (Exception e) {
			return "0";
		}
	}

	public static String tratarParametroString(Object param) {
		try {
			return tratarParametroString(param.toString());
		} catch (Exception e) {
			return "0";
		}
	}

	public static String tratarParametroString(Long param) {
		try {
			return tratarParametroString(param.toString());
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * Nome: tratarParametroLong Propósito: Tratar parametro long.
	 *
	 * @param param
	 *            the param
	 * @return long
	 * @see
	 */
	public static long tratarParametroLong(Object param) {
		try {
			if (SiteUtil.isEmptyOrNull(param)) {
				return 0;
			} else {
				return Long.parseLong(param.toString());
			}
		} catch (Exception e) {
			return 0;
		}
	}

	public static int tratarParametroInteger(Object param) {
		try {
			if (SiteUtil.isEmptyOrNull(param)) {
				return 0;
			} else {
				return Integer.parseInt(param.toString());
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Nome: retirarSinaisCpfCnpj Propósito: Retirar sinais cpf cnpj.
	 *
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return string
	 * @see
	 */
	public static String retirarSinaisCpfCnpj(String cpfCnpj) {
		if (isNotEmptyOrNull(cpfCnpj)) {
			return cpfCnpj.replaceAll("[^0-9]", "");
		} else {
			return null;
		}
	}

	public static String removeNaoNumericos(final String valor) {
		if (valor == null) {
			return "";
		}
		return valor.replaceAll("[^0-9]", "");
	}

	/**
	 * Nome: isNotEmptyOrNull Propósito: Verifica se é not empty or null.
	 *
	 * @param valor
	 *            the valor
	 * @return true, se for not empty or null senão retorna false
	 * @see
	 */
	public static boolean isNotEmptyOrNull(final Object valor) {

		// Verfica se o objeto é uma instância de coleção
		if (valor instanceof Collection<?>) {
			Collection<?> coll = (Collection<?>) valor;
			// Condição
			return (coll != null) && 0 < coll.size();
		} else {
			// validar se objeto recebido esta vazio o nulo.
			return (valor != null) && (!"".equals(valor));
		}
	}

	public static String montarAgenciaConta(Conta conta) {
		StringBuilder agenciaContaFormatada = new StringBuilder();

		try {
			agenciaContaFormatada.append(conta.getAgencia());
			agenciaContaFormatada.append(" | ");
			agenciaContaFormatada.append(conta.getNumConta());
			agenciaContaFormatada.append("-");
			agenciaContaFormatada.append(conta.obterContaDig());

			return agenciaContaFormatada.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String montarAgenciaConta(Integer agencia, Long conta, Long digito) {
		StringBuilder agenciaContaFormatada = new StringBuilder();

		try {
			agenciaContaFormatada.append(agencia);
			agenciaContaFormatada.append(" | ");
			agenciaContaFormatada.append(conta);
			agenciaContaFormatada.append("-");
			agenciaContaFormatada.append(digito);

			return agenciaContaFormatada.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	// para adesao pix TODO: não alterar
    public static String gerarDataHoraTimeStamp(){
    	StringBuilder builder = new StringBuilder();
    	try {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");  
            Date date = new Date();  
            builder.append(dateFormat.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return builder.toString();
    }

	public static int obterDDD(String celular) {
		String [] saida = celular.split(" ");
		return tratarParametroInteger(saida[0]);
	}
	
	public static Long obterNumeroCelularSemDDD(String celular) {
		StringBuilder builder = new StringBuilder("");
		String [] saida = celular.split(" ");
		try {
			builder.append(saida[1]);
			builder.append(saida[2]);
		} catch (Exception e) {
			return 0L;
		}
		return tratarParametroLong(builder.toString());
	}
	
	public static String ocultarCPF(String cpf) {
		return cpf.substring(0, 3) + ".***.***-" + cpf.substring(9, 11);
	}

	/**
    * Formata um número com uma certa quantidade de dígitos.
    * @param numero
    *      Número a formatar.
    * @param tamanho
    *      Quantidade de dígitos.
    * @return
    *      Número formatado.
    */
   public static String formatarNumero(Object numero, int tamanho) {
       return FormatadorString.expandir(numero.toString(), tamanho, '0', false);
   }
   
   /**
    * Formata um texto com uma certa quantidade de espaços à direita.
    * @param texto
    *      Texto a formatar.
    * @param tamanho
    *      Quantidade de caracteres.
    * @return
    *      Texto formatado.
    */
   public static String formatarTexto(String texto, int tamanho) {
       return FormatadorString.expandir(texto, tamanho, true);
   }
   
   
   public static String formatarTextoAutenticado(String autenticada)
   {
     StringBuffer ret = new StringBuffer();
     ret.append("<pre>");
     ret.append(autenticada.charAt(0));
     for (int i = 1; i < autenticada.length() - 1; i++)    {
       if (i % 64 == 0) {
         ret.append("</pre>\n<pre>");

       } else if ((i % 8 == 0) && 
         (i < autenticada.length())) {
         ret.append(" ");


       }
       ret.append(autenticada.charAt(i));
     }
     ret.append(autenticada.charAt(autenticada.length() - 1));
     ret.append("</pre>");
     return ret.toString();
   }
   
   public static String formatarTextoAutDigital(String textoAutDigital) {
       String aux = textoAutDigital;
                 
       StringBuffer stringBuffer = new StringBuffer();
       char[] digits = aux.toCharArray();
      
       for (int i = 0; i < digits.length; i++) {
           stringBuffer.append(digits[i]);
          
           if (i != 0 && (i + 1) % 64 == 0) {
               stringBuffer.append("\n");
           } else if (i != 0 && (i + 1) % 8 == 0) {
               stringBuffer.append(" ");
           }
       }
       
       return stringBuffer.toString();
   }
   
   public static String formatarValorApresentacao(Object valor) {
       try {
           if (isEmptyOrNull(valor)) {
               return "";
           }
           BigDecimal valoDecimal = new BigDecimal(valor.toString());
           DecimalFormat df = new DecimalFormat(MASCARA_VALOR, getDecimalFormatSymbols());
           return (df.format(valoDecimal.setScale(2, BigDecimal.ROUND_DOWN)));
       } catch (NumberFormatException e) {
           return "";
       }
   }
   
   /**
    * Nome: getDecimalFormatSymbols Recupera o valor do atributo 'decimalFormatSymbols'.
    * 
    * @return valor do atributo 'decimalFormatSymbols'
    * @see
    */
   public static DecimalFormatSymbols getDecimalFormatSymbols() {
       if (decimalSimbolos == null) {
           decimalSimbolos = new DecimalFormatSymbols();
           decimalSimbolos.setDecimalSeparator(',');
           decimalSimbolos.setGroupingSeparator('.');
       }
       return decimalSimbolos;
   }
   
   /**
    * Introduzir aqui os comentarios necessarios para o método.
    * @param cnpj - O valor do campo a determinar.
    * @return String
    */
   public static String formatarCnpj(String cnpj) {
       while (cnpj.length() < 14) {
           cnpj = cnpj + "0";
       }
       return formatar(cnpj,
               "##.###.###/####-##");
   }
   
   public static String formatar(final String valor,
           final String mascara) {

       String dado = "";
       // remove caracteres nao numericos
       for (int i = 0; i < valor.length(); i++ ) {
           char c = valor.charAt(i);
           if (Character.isDigit(c)) {
               dado += c;
           }
       }

       int indMascara = mascara.length();
       int indCampo = dado.length();

       for (; indCampo > 0 && indMascara > 0;) {
           if (mascara.charAt(--indMascara) == '#') {
               indCampo-- ;
           }
       }

       String saida = "";
       for (; indMascara < mascara.length(); indMascara++ ) {
           if (mascara.charAt(indMascara) == '#') {
               saida += dado.charAt(indCampo++ );
           } else {
               saida += mascara.charAt(indMascara);
           }
       }
       return saida;
   }
   
   public static String completarZerosEsquerda(Integer qtdZeros,
           String numero) {
       while (numero.length() < qtdZeros.intValue()) {
           numero = "0" + numero;
       }
       return numero;
   }
   
   public static String formatarCelular(String celular) {
       StringBuilder c = new StringBuilder();
        try {
        	int tam = celular.length();
	        String p2 = celular.substring(tam - 4, tam);
	        String p1 = celular.substring( tam - 9, tam - 4);
	        String oper = celular.substring( tam - 11, tam - 9);
	        String codPais = celular.substring( 0, tam -11);
		
	        c.append(codPais +" ");
	        c.append(oper +" ");
	        c.append(p1 +" ");
	        c.append(p2);
	        
		} catch (Exception e) {
			return celular;
		}
       return c.toString();
   }
}