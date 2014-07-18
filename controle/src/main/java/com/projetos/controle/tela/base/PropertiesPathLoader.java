package com.projetos.controle.tela.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesPathLoader {

	public static final String RELATORIO_PEDIDO_CLIENTE = "caminho_pedido_cliente";
	public static final String RELATORIO_PEDIDOS = "caminho_relatorio_pedidos";

	
	public static String getProperty(String propriedade) {
		
		try {

			InputStream resource = Thread.class.getResourceAsStream("../path.properties");
			System.out.println(resource);
			Properties properties = new Properties();
			properties.load(resource);

		} catch (IOException e) {
			throw new RuntimeException("Problema ao carregar o arquivo path.properties. " + e.getMessage());
		}

		String property = "";
		if (property == null || property.equals("")) {
			property = "Propriedade não mapeada!";
		}
		return property;
	}

}
