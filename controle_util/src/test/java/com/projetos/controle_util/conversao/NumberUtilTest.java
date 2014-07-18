package com.projetos.controle_util.conversao;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void convertStringToDoubleTest() {
		String value = "13.34".replace(",", ".");
//		System.out.println(Double.parseDouble(value));
	}

	@Test
	public void convertDoubleToStringTest() {
		Double d = 123456789012.7773d;
		BigDecimal bigDecimal = new BigDecimal(d);
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_FLOOR);

		System.out.println(bigDecimal.toString());
	}

}
