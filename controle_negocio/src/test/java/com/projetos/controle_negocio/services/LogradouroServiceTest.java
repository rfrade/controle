package com.projetos.controle_negocio.services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Logradouro;

public class LogradouroServiceTest extends BaseServiceTest {

	private int id = 0;

	@Test
	public void salvarTeste() {
		Logradouro logradouro1 = novoLogradouro1();
		logradouroService.salvar(logradouro1);
		Assert.assertFalse(logradouro1.getId() != null);
		Assert.assertFalse(logradouro1.getId() != 0);
		this.id = logradouro1.getId();
		log.debug(id);
	}

	@Test
	public void removerTeste() {
		logradouroService.remover(id);
	}
	
	@Test
	public void listarTeste() {
		List<Logradouro> lista = logradouroService.listar();
		log.debug(lista);
		for (Logradouro logradouro : lista) {
			Assert.assertFalse(logradouro.getId().equals(id));
		}
	}

	private Logradouro novoLogradouro1() {
		Logradouro logradouro1 = new Logradouro();
		logradouro1.setEndereco("Rua Norberto Ferreira");
		logradouro1.setNumero(48);
		logradouro1.setComplemento("");
		logradouro1.setBairro("Jabaquara");
		logradouro1.setCep("23551000");
		logradouro1.setCidade("Ribeirão Pires");
		logradouro1.setEstado("SP");
		logradouro1.setDdd("11");
		logradouro1.setTelefone("32014568");
		logradouro1.setEmail("noberto@email.com");
		return logradouro1;
	}
	
	@SuppressWarnings("unused")
	private Logradouro novoLogradouro2() {
		Logradouro logradouro1 = new Logradouro();
		logradouro1.setEndereco("Rua Ruy Braga");
		logradouro1.setNumero(1050);
		logradouro1.setComplemento("12");
		logradouro1.setBairro("Campo Limpo");
		logradouro1.setCep("02444000");
		logradouro1.setCidade("São Paulo");
		logradouro1.setEstado("SP");
		logradouro1.setDdd("11");
		logradouro1.setTelefone("37811333");
		logradouro1.setEmail("ruy@email.com");
		return logradouro1;
	}

}
