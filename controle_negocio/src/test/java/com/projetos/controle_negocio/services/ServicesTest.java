package com.projetos.controle_negocio.services;

import java.util.List;

import org.junit.Test;

import com.projetos.controle_entities.Logradouro;

public class ServicesTest extends BaseServiceTest {

	@Test
	public void findAllTest() {
		List<Logradouro> lista = logradouroService.listar();
		for (Logradouro logradouro : lista) {
			
		}
	}

}
