package com.projetos.controle_negocio.services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Logradouro;

public class ClienteServiceTest extends BaseServiceTest {

	@Test
	public void filtrarTest() {
		Cliente filtro = new Cliente();
		Logradouro logradouro = new Logradouro();
		filtro.setLogradouro(logradouro);
		List<Cliente> lista = clienteService.filtrar(filtro );
		Assert.assertFalse(lista.isEmpty());
		log.debug(lista);
	}

}
