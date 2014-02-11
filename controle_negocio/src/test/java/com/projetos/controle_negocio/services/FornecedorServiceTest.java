package com.projetos.controle_negocio.services;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Fornecedor;

public class FornecedorServiceTest extends BaseServiceTest {

	@Test
	public void incluirFornecedorTest() {
		Fornecedor fornecedor1 = getFornecedorTest();
		fornecedorService.salvar(fornecedor1);
		Fornecedor fornecedor2 = getFornecedorTest2();
		fornecedorService.salvar(fornecedor2);
		Assert.assertNotNull(fornecedor1.getId());
		Assert.assertNotNull(fornecedor2.getId());
	}
	
	private Fornecedor getFornecedorTest() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setAtivo(true);
		fornecedor.setCnpj("1239874623");
		fornecedor.setComprador("Amadeus");
		fornecedor.setFirma("Lojas Gincobiloba");
		return fornecedor;
	}
	
	private Fornecedor getFornecedorTest2() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setAtivo(true);
		fornecedor.setCnpj("9999999");
		fornecedor.setComprador("Emanoteu");
		fornecedor.setFirma("Abumina Comércio Ltda");
		return fornecedor;
	}

}
