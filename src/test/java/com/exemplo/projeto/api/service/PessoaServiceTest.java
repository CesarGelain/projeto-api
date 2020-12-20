package com.exemplo.projeto.api.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.exemplo.projeto.api.BaseTest;
import com.exemplo.projeto.api.exception.RegraDeNegocioException;
import com.exemplo.projeto.api.exception.ResultadoNaoEncontradoException;
import com.exemplo.projeto.api.modelo.Contato;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.ContatoRepository;
import com.exemplo.projeto.api.repository.PessoaRepository;
import com.exemplo.projeto.api.specification.PessoaSpecification;
import com.exemplo.projeto.api.validation.PessoaValidation;

@RunWith(SpringRunner.class)
public class PessoaServiceTest extends BaseTest {
	
	@TestConfiguration
	static class PessoaServiceTestConfiguration {
		@Bean
		public PessoaService pessoaService() {
			return new PessoaService();
		}
	}
	
	@Autowired
	PessoaService sut;
	
	@MockBean
	PessoaRepository pessoaRepository;
	
	@MockBean
	ContatoRepository contatoRepository;
	
	@MockBean
	private PessoaValidation validation;
	
	@MockBean
	private PessoaSpecification specification;	
	
	@Before 
	public void setup() {
		this.pessoa = new Pessoa(PESSOA_NOME, PESSOA_CPF, LocalDate.of(1990, Month.JANUARY, 1));		
		this.pessoa.setContatos(Arrays.asList(new Contato(CONTATO_NOME, pessoa, CONTATO_TELEFONE, CONTATO_EMAIL)));
		
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));		
	}
	
	@Test
	public void deve_buscar_pessoa_por_id() {		
		Pessoa p = sut.buscaPorId(1L);
		assertEquals(p, this.pessoa);
	}
	
	@Test(expected = ResultadoNaoEncontradoException.class)
	public void deve_retornar_resultado_nao_encontrado_exception() {		
		sut.buscaPorId(2L);	
	}
	
	@Test
	public void deve_salvar_pessoa() {
		sut.salvar(this.pessoa);
		verify(pessoaRepository).save(this.pessoa);						
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void nao_deve_salvar_pessoa_sem_contato() {
		this.pessoa.setContatos(new ArrayList<>());
		when(pessoaRepository.save(this.pessoa)).thenThrow(RegraDeNegocioException.class);
		sut.salvar(this.pessoa);
		verify(pessoaRepository).save(this.pessoa);
	}
	
	@Test
	public void deve_atualizar_pessoa() {
		this.pessoa.setId(1L);
		sut.atualizar(this.pessoa);
		verify(pessoaRepository).save(this.pessoa);
	}
	
	@Test(expected = ResultadoNaoEncontradoException.class)
	public void nao_deve_atualizar_pessoa_nao_encontrada() {
		when(pessoaRepository.findById(2L)).thenThrow(ResultadoNaoEncontradoException.class);
		sut.atualizar(this.pessoa);
	}
	
	@Test
	public void deve_excluir_pessoa() {		
		sut.excluir(1L);
		verify(pessoaRepository).delete(this.pessoa);
	}
	
	@Test(expected = ResultadoNaoEncontradoException.class)
	public void nao_deve_excluir_pessoa() {
		sut.excluir(2L);		
	}
}