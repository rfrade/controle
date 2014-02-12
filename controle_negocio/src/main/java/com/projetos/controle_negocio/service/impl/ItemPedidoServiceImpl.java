package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.QItemPedido;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.ItemPedidoRepository;
import com.projetos.controle_negocio.service.base.ItemPedidoService;

@Service
public class ItemPedidoServiceImpl extends EntidadeServiceImpl<ItemPedido> implements ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	protected EntidadeRepository<ItemPedido> getRepository() {
		return itemPedidoRepository;
	}

	@Override
	public EntityPathBase<ItemPedido> getEntityPathBase() {
		return QItemPedido.itemPedido;
	}

}
