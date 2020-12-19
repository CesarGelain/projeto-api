package com.exemplo.projeto.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exemplo.projeto.api.ResponseErrorDTO;

@ControllerAdvice
public class ExceptionHandlerResource extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {RegraDeNegocioException.class})
	public ResponseEntity<Object> handleRegraDeNegocioException(HttpServletRequest req, RegraDeNegocioException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new ResponseErrorDTO(ex.getMessage()));
	}
	
	@ExceptionHandler(value = {ResultadoNaoEncontradoException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(HttpServletRequest req, ResultadoNaoEncontradoException ex) {		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ResponseErrorDTO(ex.getMessage()));
	}

}
