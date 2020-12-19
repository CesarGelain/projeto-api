package com.exemplo.projeto.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.projeto.api.dto.FiltroPessoasDTO;
import com.exemplo.projeto.api.modelo.Pessoa;
import com.exemplo.projeto.api.service.PessoaService;

@RestController
@RequestMapping("/projeto-api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> buscaPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.buscaPorId(id));		
	}
	
	@GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Pessoa>> filtrar(FiltroPessoasDTO filtro, Pageable pageable) {				
		return ResponseEntity.ok().body(service.filtrar(filtro, pageable));		
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {		
		return ResponseEntity.ok(service.salvar(pessoa));		
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> atualizar(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
		pessoa.setId(id);
		return ResponseEntity.ok(service.atualizar(pessoa));		
	}

}
