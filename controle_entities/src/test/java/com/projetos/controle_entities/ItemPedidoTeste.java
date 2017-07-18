package com.projetos.controle_entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class ItemPedidoTeste {

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
		ItemPedido item = new ItemPedido();
		Set<ConstraintViolation<ItemPedido>> violations = validator.validate(item);
		for (ConstraintViolation<ItemPedido> constraintViolation : violations) {
			System.out.println(constraintViolation.getMessageTemplate() );/*constraintViolation.getMessage()*/
		}
	}

}
