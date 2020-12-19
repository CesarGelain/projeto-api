package com.exemplo.projeto.api;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrorDTO {	
	
	private List<String> erros;
	
	public ResponseErrorDTO() {
		
	}
	
	public ResponseErrorDTO(String mensagemDeErro) {
		String[] errors = mensagemDeErro.split(System.lineSeparator());
		int e = errors.length;
		this.erros = new ArrayList<String>();
		for (int i = 0; i < e; i++) {
			this.erros.add(new String(errors[i]));						
		}		
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}	

}