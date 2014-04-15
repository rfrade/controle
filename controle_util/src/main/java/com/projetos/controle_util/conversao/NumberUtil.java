package com.projetos.controle_util.conversao;


public class NumberUtil {

	public static Double convertStringToDouble(String valor) {
		String value = valor.replace(",", ".");
		return Double.parseDouble(value);
	}
	
	public static String convertDoubleToString(Double d) {
		String valor = String.valueOf(d);
		return valor.replace(".", ",");
	}

}