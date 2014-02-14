package com.projetos.controle_negocio.repository;

import junit.framework.Assert;

import org.junit.Test;

import com.mysema.query.types.expr.BooleanExpression;
import com.projetos.controle_entities.Logradouro;
import com.projetos.controle_entities.QLogradouro;

public class LogradouroRepositoryTest extends BaseRepositoryTest {

	@Test
	public void queryDSLTest() {
		Logradouro novoLogradouro1 = novoLogradouro("Rua Norberto Ferreira");
		Logradouro novoLogradouro2 = novoLogradouro("Clovis 12");
		logradouroRepositoy.save(novoLogradouro1);
		logradouroRepositoy.save(novoLogradouro2);

		QLogradouro qLogradouro = QLogradouro.logradouro;
		String param = "a";
		BooleanExpression contains = qLogradouro.endereco.contains(param);
		Iterable<Logradouro> list = logradouroRepositoy.findAll(contains);
		for (Logradouro logradouro : list) {
			log.info(logradouro);
			Assert.assertTrue(logradouro.getEndereco().toUpperCase().contains(param.toUpperCase()));
			Assert.assertFalse(logradouro.getEndereco().equals(novoLogradouro2.getEndereco()));
		}
		logradouroRepositoy.delete(novoLogradouro1);
	}

}
