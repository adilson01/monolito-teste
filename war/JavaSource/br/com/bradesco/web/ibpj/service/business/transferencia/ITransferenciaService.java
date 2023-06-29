package br.com.bradesco.web.ibpj.service.business.transferencia;

import br.com.bradesco.web.ibpj.bean.CodigoServicoTipoOperacao;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.model.EfetivaTransferenciaModel;

public interface ITransferenciaService {

	Transacao criarTransacaoTransferencia(Conta conta, Procurador procurador, 
			CodigoServicoTipoOperacao trinca,
			EfetivaTransferenciaModel dados);
	
}
