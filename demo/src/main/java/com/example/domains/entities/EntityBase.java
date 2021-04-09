package com.example.domains.entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EntityBase<T> {
	@Autowired
	Validator validator;
	
	@JsonIgnore
	public Set<ConstraintViolation<T>> getErrors() {
		if(validator == null)
			validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate((T)this);
	}
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}
	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}
}
