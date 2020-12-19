package com.exemplo.projeto.api.validation;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateHibernate<T> {
	
	public ValidateHibernate() { }
	
	public String validaAnotacoes(T obj) {
		StringBuilder error = new StringBuilder();		
		Optional<String> erro = Optional.ofNullable(trataConstraintsValidator(obj));
		erro.ifPresent(e -> error.append(e));
		return error.toString();		
	}

	public String trataConstraintsValidator(T obj) {
		Set<ConstraintViolation<T>> validate = getValidator().validate(obj, new Class[0]);
		if (!validate.isEmpty()) {
			StringBuffer erro = new StringBuffer();
			for (ConstraintViolation<T> constraintViolation : validate) {
				erro.append(constraintViolation.getMessage() + System.lineSeparator());
			}
			return erro.toString();
		}
		return null;
	}

	private Validator getValidator() {
		ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		return validator;
	}

}
