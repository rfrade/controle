package com.projetos.controle_negocio.services;

import org.junit.Test;

import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Pedido;

public class PedidoServiceTest extends BaseServiceTest {

	@Test
	public void removerItemPedidoTest() {
		Pedido pedido = pedidoService.findById(3);
		ItemPedido item = new ItemPedido();
		item.setPedido(pedido);
		itemPedidoService.salvar(item);
		
		itemPedidoService.remover(item);
		pedido = pedidoService.findById(3);
		pedidoService.salvar(pedido);
		log.info(pedido.getItensPedido());
	}
	
}
