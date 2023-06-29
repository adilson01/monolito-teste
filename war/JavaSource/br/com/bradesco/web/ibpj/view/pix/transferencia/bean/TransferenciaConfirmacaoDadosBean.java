package br.com.bradesco.web.ibpj.view.pix.transferencia.bean;

import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.service.business.adesao.IAdesaoService;
import br.com.bradesco.web.ibpj.service.business.dadocliente.DadoClienteFactory;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.business.transferencia.ITransferenciaService;
import br.com.bradesco.web.ibpj.view.utils.Constantes;
import br.com.bradesco.web.ibpj.view.utils.NavegacaoUtils;

public class TransferenciaConfirmacaoDadosBean {
	private static final String NAV_TRANSFERENCIA_CONFIRMACAO_DADOS = "transferenciaConfirmacaoDados";

	private static final String NAV_AUTENTICADOR = "autenticador";

	private TransferenciaForm transform;

	private ITransferenciaService transferenciaService;

	public String init() {
		return NAV_TRANSFERENCIA_CONFIRMACAO_DADOS;
	}

	/**
	 * 32 é o grupo 1 é o serviço 2 é o tipo serviço
	 * 
	 * DsTipoServico é a descrição do serviço 32 cdGrupo 1 cdServico 3 TpServico
	 * 
	 * @return
	 */
	public String confirmar() {
		CodigoServicoTipoOperacao trinca = new CodigoServicoTipoOperacao();
		trinca.setCodigoGrupo(Constantes.COD_GRUPO);
		trinca.setCodigoTipoServico(Constantes.COD_TIPO_SERVICO);
		trinca.setCodigoOperacao(0);
		trinca.setDescricao(Constantes.COD_DS_TIPO_SERVICO_TRANSFERENCIA);

		Transacao transacao = this.transferenciaService.criarTransacaoTransferencia(
				this.transform.getConta(),
				DadoClienteFactory.getInfoAcessoService().obterProcuradorAcesso(), 
				trinca, 
				this.transform.getTrasnferenciaModel());

		this.transform.setNumeroTransacao(transacao.getNumeroTransacao());

		return NAV_AUTENTICADOR;
	}
	
	public String voltar() {
		return NavegacaoUtils.getInserirValorBean().recarregarTela();
	}

	public TransferenciaForm getTransform() {
		return transform;
	}

	public void setTransform(TransferenciaForm transform) {
		this.transform = transform;
	}

	public ITransferenciaService getTransferenciaService() {
		return transferenciaService;
	}

	public void setTransferenciaService(ITransferenciaService transferenciaService) {
		this.transferenciaService = transferenciaService;
	}

}
