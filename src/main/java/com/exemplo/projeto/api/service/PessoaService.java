package com.exemplo.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.exemplo.projeto.api.dto.FiltroPessoasDTO;
import com.exemplo.projeto.api.exception.ResultadoNaoEncontradoException;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.ContatoRepository;
import com.exemplo.projeto.api.repository.PessoaRepository;
import com.exemplo.projeto.api.specification.PessoaSpecification;
import com.exemplo.projeto.api.validation.PessoaValidation;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaValidation validation;
	
	@Autowired
	private PessoaSpecification specification;
	
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
	
	public Pessoa atualizar(Pessoa pessoa) {
		buscaPorId(pessoa.getId());
		salvar(pessoa);
		return pessoa;
	}
	
	public Page<Pessoa> filtrar (FiltroPessoasDTO filtro, Pageable pageable) {		
		Specification<Pessoa> spec = specification.filtrar(filtro.getTermo());		
		return pessoaRepository.findAll(spec, pageable);		
	}
	
	public void excluir(Long id) {
		Pessoa pessoa = buscaPorId(id);
		pessoaRepository.delete(pessoa);
	}

}
