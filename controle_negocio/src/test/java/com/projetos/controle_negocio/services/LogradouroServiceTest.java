package com.projetos.controle_negocio.services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Logradouro;

public class LogradouroServiceTest extends BaseServiceTest {

	@Test
	public void salvarTeste() throws Exception {
		try {

			int id = 0;

			Logradouro logradouro1 = novoLogradouro1();
			Assert.assertFalse(logradouro1.getId() != null);
			logradouroService.salvar(logradouro1);
			Assert.assertFalse(logradouro1.getId() == 0);
			id = logradouro1.getId();
			log.debug(id);

			logradouroService.remover(id);

			List<Logradouro> lista = logradouroService.listar();
			log.debug(lista);
			for (Logradouro logradouro : lista) {
				Assert.assertFalse(logradouro.getId().equals(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void salvarAlteracaoTeste() {
		Cliente cliente = novoCliente();
		Logradouro logradouro = novoLogradouro1();
		cliente.setLogradouro(logradouro);
		clienteService.salvar(cliente);
		
		String bairro = "novo bairro";
		logradouro.setBairro(bairro);
		clienteService.salvar(cliente);
		
		Logradouro logradouroConsulta = logradouroService.getById(logradouro.getId());
		
		Assert.assertEquals(bairro, logradouroConsulta.getBairro());
		clienteService.remover(cliente);
		logradouroService.remover(logradouro);
	}

	private Cliente novoCliente() {
		Cliente cliente = new Cliente();
		cliente.setFirma("Junit: " + this.getClass().getName());
		return cliente;
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
