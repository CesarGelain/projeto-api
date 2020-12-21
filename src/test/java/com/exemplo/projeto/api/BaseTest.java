package com.exemplo.projeto.api;

import com.exemplo.projeto.api.modelo.Contato;
import com.exemplo.projeto.api.modelo.Pessoa;

public abstract class BaseTest {
	
	protected static final String PESSOA_NOME = "Pessoa Nome";
	protected static final String PESSOA_CPF = "09740945015";
	protected static final String CONTATO_NOME = "Contato Nome";
	protected static final String CONTATO_TELEFONE = "4412341234";
	protected static final String CONTATO_EMAIL = "contato@email.com";
	protected static final String EMAIL_INVALIDO = "email@";
	
	protected Pessoa pessoa;
	
	protected Contato contato;
	
	protected abstract void setup();

}
