package com.exemplo.projeto.api.dto;

import java.util.Objects;

public class FiltroPessoasDTO {
	
	private String termo;

	public String getTermo() {
		return Objects.isNull(termo) ? "" : termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}	

}
