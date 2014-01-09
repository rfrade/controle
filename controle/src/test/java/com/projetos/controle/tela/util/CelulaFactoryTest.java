package com.projetos.controle.tela.util;

import javafx.scene.control.TableColumn.CellDataFeatures;
import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Logradouro;

public class CelulaFactoryTest {

	@Test
	public void getPropriedadeTest() {
		CelulaFactory<Cliente, String> factory = new CelulaFactory<Cliente, String>("firma");
		Cliente cliente = new Cliente();
		String firma = "JJ";
		cliente.setFirma(firma);
		CellDataFeatures<Cliente, String> celula = new CellDataFeatures<Cliente, String>(null, null, cliente );
		Assert.assertEquals(firma, factory.call(celula).getValue());
	
		CelulaFactory<Cliente, String> factoryLogradouro = new CelulaFactory<Cliente, String>("logradouro.endereco");
		Logradouro logradouro = new Logradouro();
		String endereco = "Rua Silva";
		logradouro.setEndereco(endereco);
		cliente.setLogradouro(logradouro);
		
		Assert.assertEquals(endereco, factoryLogradouro.call(celula).getValue());
		logradouro.setEndereco(null);
		Assert.assertEquals(null, factoryLogradouro.call(celula).getValue());
		
		
		factoryLogradouro = new CelulaFactory<Cliente, String>("logradouro.numero");
		int numero = 12;
		logradouro.setNumero(numero);
		cliente.setLogradouro(logradouro);
		Assert.assertEquals(numero, factoryLogradouro.call(celula).getValue());
	}

}
