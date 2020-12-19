package com.exemplo.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.projeto.api.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
