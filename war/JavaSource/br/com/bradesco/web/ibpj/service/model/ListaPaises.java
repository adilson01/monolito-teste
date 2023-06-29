package br.com.bradesco.web.ibpj.service.model;

import java.io.Serializable;
import java.util.List;

public class ListaPaises implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Pais> paises;

	public List<Pais> getPaises(){
		return paises;
	}

	public void setPaises(List<Pais> paises){
		this.paises = paises;
	}

}
