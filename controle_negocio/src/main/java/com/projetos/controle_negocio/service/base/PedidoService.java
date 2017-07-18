package com.projetos.controle_negocio.service.base;

import com.projetos.controle_entities.Pedido;

public interface PedidoService extends EntidadeService<Pedido> {

	/**
	 * @return pedido com itens e recebimentos
	 */
	public Pedido consultarPedido(Integer id);
	
}
