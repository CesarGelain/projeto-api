package com.exemplo.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.projeto.api.exception.ResultadoNaoEncontradoException;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.ContatoRepository;
import com.exemplo.projeto.api.repository.PessoaRepository;
import com.exemplo.projeto.api.validation.PessoaValidation;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaValidation validation;
	
	private static final String MENSAGEM_PESSOA_NAO_ENCONTRADA = "Pessoa ID %d não encontrada";
	
	public Pessoa buscaPorId(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new ResultadoNaoEncontradoException(String.format(MENSAGEM_PESSOA_NAO_ENCONTRADA, id)));
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		validation.objetoValido(pessoa);
		pessoaRepository.save(pessoa);
		pessoa.getContatos().forEach(c -> {
			 c.setPessoa(pessoa);
			 contatoRepository.save(c);			 			
		});		
		return pessoa;
	}

}
