package com.projetos.controle_entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class LogradouroTeste {

	private static Validator validator;

	@BeforeClass
	public static void setUp() throws Exception {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testeValidacao() {
		Logradouro logradouro = new Logradouro();
		logradouro.setEstado("XXX");
		Set<ConstraintViolation<Logradouro>> violations = validator.validate(logradouro);
		for (ConstraintViolation<Logradouro> constraintViolation : violations) {
			System.out.println(constraintViolation.getMessageTemplate() );/*constraintViolation.getMessage()*/
		}
	}

}
