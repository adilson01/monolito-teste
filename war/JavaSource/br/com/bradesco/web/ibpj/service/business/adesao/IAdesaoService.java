package br.com.bradesco.web.ibpj.service.business.adesao;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Procurador;
import br.com.bradesco.web.ibpj.service.business.operacao.transacao.bean.Transacao;

public interface IAdesaoService {

	Transacao criarTransacao(Conta conta, Procurador procurador,
			CriaChaveUnicaRequest criaChaveUnicaRequest);
}
