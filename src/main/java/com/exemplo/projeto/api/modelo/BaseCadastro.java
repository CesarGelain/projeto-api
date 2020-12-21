package com.exemplo.projeto.api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseCadastro {
	
	private static final String GENERATOR = "id_generator";
	private static final String SEQUENCE = "sequence";	
	
	@Id
	@SequenceGenerator(name = GENERATOR, sequenceName = SEQUENCE, allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
	@Column(name = "id", nullable = false, unique = true, updatable = false)	
	private Long id;
	
	@NotEmpty(message = "nome não informado")
	@Size(max = 100, message = "nome: Limite máximo de caracteres excedido - máximo = 100")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	public BaseCadastro() {	}
	
	public BaseCadastro(String nome) {		
		this.nome = nome;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseCadastro other = (BaseCadastro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
