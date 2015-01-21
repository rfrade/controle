package com.projetos.controle.tela.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource("classpath:/mensagem.properties")
public class PropertiesLoader {

	@Autowired
	private Environment environment;

	public String getProperty(String propriedade) {
		String property = environment.getProperty(propriedade);
		return property;
	}

}
