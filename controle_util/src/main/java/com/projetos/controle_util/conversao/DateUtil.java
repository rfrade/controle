package com.projetos.controle_util.conversao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static Date convertStringToDate(String valor) {
		try {
		Locale local = new Locale("pt", "BR");  
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, local);
			return (Date)format.parseObject(valor);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao passar pra data o valor: " + valor);
		}
	}
	
	public static String convertDateToString(Date date) {
		Locale local = new Locale("pt", "BR");  
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, local);
		return format.format(date);
	}

}