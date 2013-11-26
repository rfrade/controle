package com.projetos.controle_entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

/**
 * 25/11/13
 * Inicia o entityManager e realiza uma consulta.
 * 
 * @author Rafael Frade
 */
public class ConfiguracaoJPATeste {

	@Test
	@SuppressWarnings("unchecked")
	public void testarConfiguracaoJPA() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.inovacao.controle_entities");
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select t from com.projetos.controle_entities.Empresa t");
		List<Empresa> todoList = q.getResultList();
	    System.out.println(todoList);
	}
	
}
