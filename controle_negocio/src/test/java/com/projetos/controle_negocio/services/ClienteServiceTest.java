package com.projetos.controle_negocio.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.Ordenacao;
import com.projetos.controle_negocio.filtro.Ordenacao.TipoOrdenacao;
import com.projetos.controle_negocio.filtro.TipoFiltro;

public class ClienteServiceTest extends BaseServiceTest {

	@Test
	public void filtrarTest() {
		Cliente cliente1 = new Cliente();
		cliente1.setAtivo(true);
		cliente1.setFirma("Empresa Teste");
		
		Cliente cliente2 = new Cliente();
		cliente2.setAtivo(false);
		cliente2.setFirma("Empresa Teste 2");
		
		clienteService.salvar(cliente1);
		clienteService.salvar(cliente2);
		
		List<Filtro> filtros = new ArrayList<>();
		Filtro filtro1 = new Filtro("firma", TipoFiltro.STRING, Comparador.CONTAINS_IGNORE_CASE, "Teste");
		filtros.add(filtro1);
		List<Cliente> lista1 = clienteService.filtrar(filtros);
		Assert.assertTrue(lista1.size() >= 2);
		filtros.clear();
		
		Filtro filtro2 = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, false);
		filtros.add(filtro2);
		List<Cliente> lista2 = clienteService.filtrar(filtros);
		boolean encontrouCliente2 = false;
		for (Cliente cliente : lista2) {
			if (cliente.getFirma().equals(cliente2.getFirma())) {
				encontrouCliente2 = true;
				return;
			}
			Assert.assertFalse(cliente.isAtivo());
		}
		Assert.assertTrue(encontrouCliente2);

		clienteService.remover(cliente1);
		clienteService.remover(cliente2);

	}

}
