package com.projetos.controle_negocio.services;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Vendedor;

public class VendedorServiceTest extends BaseServiceTest {

	@Test
	public void incluirFornecedorTest() {
		Vendedor vendedor = getVendedorTest();
		vendedorService.salvar(vendedor);
		Assert.assertNotNull(vendedor.getId());
		Assert.assertNotNull(vendedor.getId());
	}
	
	private Vendedor getVendedorTest() {
		Vendedor vendedor = new Vendedor();
		vendedor.setNome("JC Martins");
		return vendedor;
	}
	
}
