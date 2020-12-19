package com.exemplo.projeto.api.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = NomeTabela.PESSOA)
public class Pessoa extends BaseCadastro {
	
	@NotEmpty(message = "cpf não informado")	
	@Size(min = 11, max = 11, message = "cpf não contém 11 dígitos")
	@Column(name = "cpf", nullable = false, length = 20, unique = true)
	private String cpf;
	
	@NotNull(message = "dataNascimento não informada")
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy="pessoa")
	@NotNull(message = "Nenhum contato informado")
	private List<Contato> contatos;
	
	public Pessoa() {}
	
	public Pessoa(Long id, String nome, String cpf, LocalDate dataNascimento) {
		super(id, nome);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
}