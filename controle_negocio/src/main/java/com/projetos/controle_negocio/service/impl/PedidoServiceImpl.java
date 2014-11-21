package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.QPedido;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.PedidoRepository;
import com.projetos.controle_negocio.service.base.PedidoService;

@Service
@Lazy
public class PedidoServiceImpl extends EntidadeServiceImpl<Pedido> implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	protected EntidadeRepository<Pedido> getRepository() {
		return pedidoRepository;
	}

	@Override
	public EntityPathBase<Pedido> getEntityPathBase() {
		return QPedido.pedido;
	}

}
