package com.projetos.controle.tela.base;

import org.junit.Test;

public class PropertiesPathLoaderTest {

	@Test
	public void getPropriedadeTest() {
		
		String property = PropertiesPathLoader.getProperty(PropertiesPathLoader.RELATORIO_PEDIDO_CLIENTE);
		
		System.out.println(property);
			
	}

}