package com.projetos.controle_negocio.repository;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.jpa.impl.JPAQuery;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.QPedido;
import com.projetos.controle_negocio.repositoy.PedidoRepository;
import com.projetos.controle_negocio.service.base.PedidoService;

public class PedidoRepositoryTest extends BaseRepositoryTest {

	@PersistenceContext
	public EntityManager entityManager;
	
	@Autowired
	private PedidoService pedidoService;

	@Test
	public void listarTest() {
		long ini = Calendar.getInstance().getTimeInMillis();
		pedidoService.listar();
		long fim = Calendar.getInstance().getTimeInMillis();
		log.info(fim - ini);
	}
	
//	@Test
	public void simpleQueryTest() {
		QPedido qPedido = QPedido.pedido;
		JPAQuery jpaQuery = new JPAQuery(entityManager);
		//.where(qPedido.logradouro.endereco.containsIgnoreCase("a"))
		long ini = Calendar.getInstance().getTimeInMillis();
		List<Pedido> lista = (List<Pedido>) jpaQuery.from(qPedido)
				.list(qPedido);
		//Comparador.GREATER_OR_EQUALS;
		long fim = Calendar.getInstance().getTimeInMillis();

		log.info(fim - ini);
		log.info(lista.get(0).getItensPedido().size());
		Assert.assertNotNull(lista);
	}

}
