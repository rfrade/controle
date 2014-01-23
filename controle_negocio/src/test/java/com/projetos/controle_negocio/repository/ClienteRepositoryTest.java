package com.projetos.controle_negocio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.junit.Test;

import com.mysema.query.jpa.impl.JPAQuery;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.QCliente;

public class ClienteRepositoryTest extends BaseRepositoryTest {

	@PersistenceContext
	public EntityManager entityManager;

	@Test
	public void simpleQueryTest() {
		QCliente qCliente = QCliente.cliente;
		JPAQuery jpaQuery = new JPAQuery(entityManager);
		List<Cliente> resultado = (List<Cliente>) jpaQuery.from(QCliente.cliente).where(qCliente.logradouro.endereco.containsIgnoreCase("a")).list(qCliente);
		Assert.assertNotNull(resultado);
	}

}
