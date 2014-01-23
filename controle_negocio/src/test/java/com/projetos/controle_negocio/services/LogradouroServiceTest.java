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
		
		Logradouro logradouroConsulta = logradouroService.findById(logradouro.getId());
		
		Assert.assertEquals(bairro, logradouroConsulta.getBairro());
		clienteService.remover(cliente);
		logradouroService.remover(logradouro);
	}

	private Cliente novoCliente() {
		Cliente cliente = new Cliente();
		cliente.setFirma("Junit: " + this.getClass().getSimpleName());
		return cliente;
	}
	
}
