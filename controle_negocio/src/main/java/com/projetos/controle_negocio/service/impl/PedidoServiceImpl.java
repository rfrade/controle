package com.projetos.controle_negocio.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.QPedido;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.PedidoRepository;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;

@Service
@Lazy
public class PedidoServiceImpl extends EntidadeServiceImpl<Pedido> implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private RecebimentoService recebimentoService;
	
	@PersistenceContext
	protected EntityManager entityManager;

	
	@Override
	protected EntidadeRepository<Pedido> getRepository() {
		return pedidoRepository;
	}

	@Override
	public EntityPathBase<Pedido> getEntityPathBase() {
		return QPedido.pedido;
	}

	@Override
	public Pedido consultarPedido(Integer id) {		
		Pedido pedido = pedidoRepository.findOne(id);
		//getItensPedido().size() serve pra consultar os itens
		pedido.getItensPedido().size();
		pedido.getRecebimentos().size();
		
		return pedido;
	}
	

}
