package br.com.bradesco.web.ibpj.service.business.gerenciamento;

import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;
import br.com.bradesco.web.ibpj.service.model.GerenciamentoChaveModel;

public interface IGerenciamentoService {

	Transacao criarTransacao(Conta conta, Procurador procurador,
			GerenciamentoChaveModel chaveSelecionada);

}
