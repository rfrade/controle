package com.projetos.controle.tela;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InicioAplicacao {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	public static void main(String[] args) {
		log.info("Aplicação iniciada em " + Calendar.getInstance().getTime());

		ApplicationContext context = new ClassPathXmlApplicationContext("");

	}

}