package com.exemplo.projeto.api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = NomeTabela.CONTATO)
public class Contato extends BaseCadastro {
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	
	@NotEmpty(message = "telefone não informado")
	@Size(min = 8, max = 15, message = "telefone inválido mínimo 8 e máximo 15 dígitos")
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Email(message = "formato de email inválido")
	@NotEmpty(message = "email não informado")
	@Size(max = 200, message = "email: Limite máximo de caracteres excedido - máximo = 200")
	@Column(name = "email", nullable = false)
	private String email;
	
	public Contato() { }
	
	public Contato(Long id, String nome, Pessoa pessoa, String telefone, String email) {
		super(id, nome);
		this.pessoa = pessoa;
		this.telefone = telefone;
		this.email = email;
	}	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}