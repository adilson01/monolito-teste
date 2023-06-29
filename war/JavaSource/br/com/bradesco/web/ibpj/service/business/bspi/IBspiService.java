package br.com.bradesco.web.ibpj.service.business.bspi;

import java.util.List;

import br.com.bradesco.web.ibpj.pix.service.chave.model.request.ConsultaListaChavesBradescoRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.CriaChaveUnicaRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.request.IntencaoCadastroChaveRequest;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.ConsultaListaChavesBradescoResponse;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.CriaChaveUnicaResponse;
import br.com.bradesco.web.ibpj.pix.service.chave.model.response.IntencaoCadastroChaveResponse;
import br.com.bradesco.web.ibpj.pix.service.conta.model.response.ValidaContaResponse;
import br.com.bradesco.web.ibpj.pix.service.posse.model.response.ValidaPosseResponse;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Conta;
import br.com.bradesco.web.ibpj.service.business.dadocliente.bean.Empresa;
import br.com.bradesco.web.ibpj.view.utils.enums.TipoChaveEnum;

public interface IBspiService {

	ValidaContaResponse validaConta(Conta conta, Empresa empresa);
	
	List<ConsultaListaChavesBradescoResponse> consultaListaChaves(ConsultaListaChavesBradescoRequest dados);

	ValidaPosseResponse validaPosse(Conta conta, 
									   Empresa empresa, 
									   TipoChaveEnum chave, 
									   String token,
									   String ddi, 
									   String celular, 
									   String email);

	CriaChaveUnicaResponse incluirChaveUnica(CriaChaveUnicaRequest chave);
	
	IntencaoCadastroChaveResponse criarIntencaoCadastroChaveUnica(IntencaoCadastroChaveRequest chave);
}
