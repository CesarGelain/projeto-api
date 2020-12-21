package com.exemplo.projeto.api.validation;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.exemplo.projeto.api.BaseTest;
import com.exemplo.projeto.api.exception.RegraDeNegocioException;
import com.exemplo.projeto.api.modelo.Contato;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.PessoaRepository;

@RunWith(SpringRunner.class)
public class PessoaValidationTest extends BaseTest {
	
	@TestConfiguration
	static class PessoaValidationTestConfiguration {
		@Bean
		public PessoaValidation pessoaValidation() {
			return new PessoaValidation();
		}
	}
	
	@Autowired
	PessoaValidation sut;
	
	@MockBean
	private PessoaRepository repository;

	@Before 
	public void setup() {
		this.pessoa = new Pessoa(PESSOA_NOME, PESSOA_CPF, LocalDate.of(1990, Month.JANUARY, 1));		
		this.contato = new Contato(CONTATO_NOME, pessoa, CONTATO_TELEFONE, CONTATO_EMAIL);
		this.pessoa.setId(1L);
		this.pessoa.setContatos(Arrays.asList(this.contato));
		
		when(repository.findByCpf(PESSOA_CPF)).thenReturn(this.pessoa);
	}
	
	@Test
	public void objeto_validado_com_sucesso() {				
		Assertions.assertTrue(sut.objetoValido(this.pessoa));	
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void objeto_invalido_atributos_nao_preenchidos() {
		Pessoa p = new Pessoa();
		Contato c = new Contato();
		p.setContatos(Arrays.asList(c));
		sut.objetoValido(p);				
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void objeto_invalido_nenhum_contato_informado() {
		Pessoa p = new Pessoa(PESSOA_NOME, PESSOA_CPF, LocalDate.of(1980, Month.MARCH, 10));
		Contato c = new Contato();
		p.setContatos(Arrays.asList(c));
		sut.objetoValido(p);				
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void objeto_invalido_com_cpf_duplicado() {
		Pessoa p = new Pessoa(PESSOA_NOME, PESSOA_CPF, LocalDate.of(1980, Month.MARCH, 10));		
		p.setContatos(Arrays.asList(this.contato));		
		sut.objetoValido(p);		
	}
	
	@Test(expected = RegraDeNegocioException.class )
	public void objeto_invalido_com_data_de_nascimento_futura() {
		Pessoa p = new Pessoa(PESSOA_NOME, PESSOA_CPF, LocalDate.of(2099, Month.MARCH, 10));		
		p.setContatos(Arrays.asList(this.contato));		;
		sut.objetoValido(p);
	}
	
	@Test(expected = RegraDeNegocioException.class )
	public void objeto_invalido_email_invalido() {
		Contato c = new Contato(CONTATO_NOME, this.pessoa, CONTATO_TELEFONE, EMAIL_INVALIDO);
		this.pessoa.setContatos(Arrays.asList(c));
		sut.objetoValido(this.pessoa);		
	}

}
