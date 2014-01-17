package com.projetos.controle_negocio.services;

import org.junit.Test;

public class ClienteServiceTest extends BaseServiceTest {

	@Test
	public void filtrarTest() {
		
		clienteService.filtrar(filtro);
	}

}
