package com.projetos.controle_util.conversao;

import java.math.BigDecimal;


public class NumberUtil {

	public static Double convertStringToDouble(String valor) {
		String value = valor.replace(",", ".");
		return Double.parseDouble(value);
	}
	
	public static String convertDoubleToString(Double d) {
		BigDecimal bigDecimal = BigDecimal.valueOf(d);
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_FLOOR);

		return bigDecimal.toString().replace(".", ",");
	}

}