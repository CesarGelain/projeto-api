package com.exemplo.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.projeto.api.exception.ResultadoNaoEncontradoException;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	private static final String MENSAGEM_PESSOA_NAO_ENCONTRADA = "Pessoa ID %d não encontrada";
	
	public Pessoa buscaPorId(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new ResultadoNaoEncontradoException(String.format(MENSAGEM_PESSOA_NAO_ENCONTRADA, id)));
	}

}
