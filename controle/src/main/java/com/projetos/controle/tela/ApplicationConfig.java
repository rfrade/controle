package com.projetos.controle.tela;

import org.springframework.context.ApplicationContext;

public class ApplicationConfig {

	private static ApplicationContext applicationContext;

	public synchronized static void setContext(ApplicationContext appContext) {
		ApplicationConfig.applicationContext = appContext;
	}

	public static <T> T getBean(Class<T> classe) {
		return applicationContext.getBean(classe);
	}

}
