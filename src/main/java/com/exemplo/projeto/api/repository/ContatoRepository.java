package com.exemplo.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.projeto.api.modelo.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}

