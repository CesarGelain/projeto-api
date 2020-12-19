package com.exemplo.projeto.api.exception;

public class ResultadoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ResultadoNaoEncontradoException(String msg) {
		super(msg);
	}

	public ResultadoNaoEncontradoException(String msg, Throwable exception) {
		super(msg, exception);
	}
}