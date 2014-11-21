package com.projetos.controle_util.conversao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * TODO: Tirar a classe do controle_util e mudar a classe para o projeto controle devido ao Java 8.
 * 
 * @author Rafael Frade
 */
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
	
	/**
	 * @return data no formato ano_mes_dia_hora_minuto_segundo
	 */
	public static String fullDateTime() {
		Locale locale = new Locale("pt", "BR");  
		SimpleDateFormat format = new SimpleDateFormat("y_M_d_H_m_s", locale);
		return format.format(new Date());
	}

}