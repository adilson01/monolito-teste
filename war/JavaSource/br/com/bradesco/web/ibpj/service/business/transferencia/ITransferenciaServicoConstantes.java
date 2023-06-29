package br.com.bradesco.web.ibpj.service.business.transferencia;

public interface ITransferenciaServicoConstantes {

	public static final String MASCARA_DATA_BARRAS = "dd/MM/yyyy";
    
    public static final int TAMANHO_CODIGO_AUTORIZANTE = 15;
    
    public static final int TAMANHO_DE_DIGITOCPF = 2;
    
    public static final int TAMANHO_DE_NUMEROCPF = 9;
    
    public static final String FILIAL_CPF_ZERADA = "0000";
    
    public static final String CTRL = "CTRL";
    
    //INFORMACOES DA EMPRESA
    public static final String NOME_PROCURADOR = "NOMEPROCURADOR";
    
    public static final String AGENCIA_DEB = "AGDEB";
    
    public static final String CONTA_DEB = "CONTDEB";
    
    public static final String DIG_CONTA_DEB = "DIGCONTDEBITO";
    
    public static final String TIPO_CONTA_DEB = "TIPOCONTDEB";
    
    public static final String EMPRESA_CNPJ_FORMAT = "EMPRESACNPJFORMAT";
    
    public static final String AGENCIA_CONTA_FORMAT_DEB = "AGCONTFRMATDEB";
    
    //INFORMACOES DA CHAVE DO RECEBEDOR
    public static final String RECEB_TIPO_CHAVE = "RECEBTPCHAVE";
    
    public static final String RECEB_NUM_CPF_CNPJ_FORMAT = "RECEBNUMCPFCNPJFORMAT";
    
    public static final String RECEB_FILIAL_CPF_CNPJ = "RECEBFILCPFCNPJ";
    
    public static final String RECEB_CONTRL_CPF_CNPJ = "RECEBCTRLCPFCNPJ";
    
    public static final String RECEB_CHAVE = "RECEBCHAVE";
    
    public static final String RECEB_NOME = "RECEBNOME";
    
    public static final String RECEB_INSTITUICAO = "RECEBINSTIT";
    
    public static final String RECEB_AGENCIA = "RECEBAG";
    
    public static final String RECEB_CONTA = "RECEBCONT";
    
    public static final String RECEB_DIG_CONTA = "RECEBDIGCONT";
    
    public static final String RECEB_TIPO_CONTA = "RECEBTPCONT";
    
    
    //DADOS DA TRANSFERENCIA
    public static final String VALOR_TARIFA = "VALTARIFA";
    
    public static final String ID_TRANSACAO = "IDTRANSAC";
    
    public static final String DATA_HORA = "DTHORA";
    
    public static final String DEBITADO_CONTA = "DEBITADOCONTA";
    
    public static final String DADOS_TRANSFERENCIA = "DADOSTRANSF";
    
}