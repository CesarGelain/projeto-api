package com.exemplo.projeto.api.specification;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.exemplo.projeto.api.modelo.Pessoa;

@Component
public class PessoaSpecification {
	
	@SuppressWarnings("serial")
	public Specification<Pessoa> filtrar(String termo) {
		return new Specification<Pessoa>() {
			@Override
			public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {				
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.like(root.get("cpf"), "%" + termo.toString().trim() + "%"));
				predicates.add(cb.like(cb.function("unaccent", String.class, cb.lower(root.get("nome"))), "%" + normalizarString(termo) + "%"));				
				return cb.or(predicates.toArray(new Predicate[] {}));
			}
		};
	}
	
	private String normalizarString(String string) {
		return Normalizer.normalize(string.trim().toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
}