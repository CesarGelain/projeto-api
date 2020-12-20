package com.exemplo.projeto.api.validation;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exemplo.projeto.api.exception.RegraDeNegocioException;
import com.exemplo.projeto.api.modelo.Contato;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.repository.PessoaRepository;

@Component
public class PessoaValidation {
	
	@Autowired
	private PessoaRepository repository;
	
	private static final String MENSAGEM_CPF_IVALIDO = "CPF inválido";
	private static final String MENSAGEM_CPF_JA_CADASTRADO = "CPF já cadastrado para o ID %d";
	private static final String MENSAGEM_TELEFONE_INVALIDO = "Telefone não pode conter letras";
	private static final String MENSAGEM_DATA_NASCIMENTO_INVALIDA = "Data de nascimento igual ou superior a data atual";

	public boolean objetoValido(Pessoa pessoa) {
		StringBuilder error = new StringBuilder();
		error.append(new ValidateHibernate<>().validaAnotacoes(pessoa));
		error.append(validarCpf(pessoa));
		error.append(validarContatos(pessoa.getContatos()));
		error.append(dataNascimentoFutura(pessoa.getDataNascimento()));
		if (error.length() > 0) {
			throw new RegraDeNegocioException(error.toString());			
		}
		return true;
	}
	
	private String validarCpf(Pessoa pessoa) {
		StringBuilder error = new StringBuilder();
		if (!isNull(pessoa.getCpf())) {
			error.append(!cpfValido(pessoa.getCpf()) ? addLineSeparator(MENSAGEM_CPF_IVALIDO) : "");
			Pessoa p = repository.findByCpf(pessoa.getCpf());
			error.append(!isNull(p) && !p.equals(pessoa) ?  addLineSeparator(String.format(MENSAGEM_CPF_JA_CADASTRADO, p.getId())) : "");
		}
		return error.toString();
	}

	private String validarContatos(List<Contato> contatos) {
		StringBuilder error = new StringBuilder();
		if (!isNull(contatos) && !contatos.isEmpty()) {
			for (Contato c : contatos) {
				error.append(new ValidateHibernate<>().validaAnotacoes(c));
				error.append(!isNull(c.getTelefone()) && c.getTelefone().matches("[^0-9]") ? addLineSeparator(MENSAGEM_TELEFONE_INVALIDO) : "");
			}
		} else {
			error.append("Nenhum contato informado" + System.lineSeparator());
		}
		return error.toString();
	}

	private String dataNascimentoFutura(LocalDate dataNascimento) {
		StringBuilder error = new StringBuilder();
		error.append(!isNull(dataNascimento) && LocalDate.now().compareTo(dataNascimento) <= 0 ? addLineSeparator(MENSAGEM_DATA_NASCIMENTO_INVALIDA) : "");
		return error.toString();
	}

	private boolean cpfValido(String cpf) {
		boolean valido = true;
		if (getSequenciasInvalidasDeCpf().contains(cpf)) {
			valido = false;			
		}
		if (cpf.length() == 11) {
			int[] num = cpf.chars().map(Character::getNumericValue).toArray();
			int calculoPrimeiroDigito = 0;
			int v1 = 10;
			for (int i = 0; i < 9; i++) {
				calculoPrimeiroDigito += (num[i] * v1);
				v1--;
			}
			int primeiroDigito = (11 - (calculoPrimeiroDigito % 11)) > 9 ? 0 : (11 - (calculoPrimeiroDigito % 11));
			int calculoSegundoDigito = 0;
			int v2 = 11;
			for (int i = 0; i < 9; i++) {
				calculoSegundoDigito += (num[i] * v2);
				v2--;
			}
			calculoSegundoDigito = ((primeiroDigito * 2) + calculoSegundoDigito);
			int segundoDigito = (11 - (calculoSegundoDigito % 11)) > 9 ? 0 : (11 - (calculoSegundoDigito % 11));
			if (num[9] != primeiroDigito || num[10] != segundoDigito) {
				valido = false;
			}
		} else {
			valido = false;
		}
		return valido;
	}
	
	private List<String> getSequenciasInvalidasDeCpf() {
		List<String> cpfs = new ArrayList<String>();
		for (Integer i = 0; i < 9; i++) {
			cpfs.add(duplicarCaracteres(i.toString(), 11));			
		}		
		return cpfs;		
	}
	
	private String duplicarCaracteres(String caracter, int quantidadeDeCaracteres) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < quantidadeDeCaracteres; i++) {
			builder.append(caracter);
		}
		return builder.toString();
	}

	private String addLineSeparator(String string) {
		return string.concat(System.lineSeparator());
	}

}
