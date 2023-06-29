package br.com.bradesco.web.ibpj.service.business;

import br.com.bradesco.web.ibpj.pix.service.exception.PixApiException;
import br.com.bradesco.web.ibpj.service.business.exceptions.ErroModel;

public class BusinessServiceBase {

	public Object tratarExcecao(Exception e) {
		ErroModel erro = null;
		if (e instanceof PixApiException) {
			PixApiException pixe = (PixApiException) e;
			
//			if(pixe.getPixErrorResponse() != null) {
//				// esta quebrando
//				return pixe.getPixErrorResponse();
//			}
			
			Integer codigo = pixe.getCode() != null ? Integer.valueOf(pixe.getCode()): null;
			erro = new ErroModel(codigo, null, pixe.getLocalizedMessage());
		} else {
			erro = new ErroModel(e.getLocalizedMessage());
		}

		// esta quebrando
		return erro;
	}
	
}
