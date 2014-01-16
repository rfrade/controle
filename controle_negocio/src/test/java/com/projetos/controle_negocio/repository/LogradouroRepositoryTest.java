package com.projetos.controle_negocio.repository;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysema.query.types.expr.BooleanExpression;
import com.projetos.controle_entities.Logradouro;
import com.projetos.controle_entities.QLogradouro;
import com.projetos.controle_negocio.repositoy.LogradouroRepositoy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/services-test.xml" })
public class LogradouroRepositoryTest {

	@Autowired
	private LogradouroRepositoy logradouroRepositoy;

	protected Logger log = Logger.getLogger(this.getClass());

	@Test
	public void queryDSLTest() {
		Logradouro novoLogradouro1 = novoLogradouro("Rua Norberto Ferreira");
		Logradouro novoLogradouro2 = novoLogradouro("Clovis 12");
		logradouroRepositoy.save(novoLogradouro1);
		logradouroRepositoy.save(novoLogradouro2);

		QLogradouro qLogradouro = QLogradouro.logradouro;
		String param = "a";
		qLogradouro.endereco.contains(param);
		BooleanExpression contains = qLogradouro.endereco.contains(param);
		Iterable<Logradouro> list = logradouroRepositoy.findAll(contains);
		for (Logradouro logradouro : list) {
			log.info(logradouro);
			Assert.assertTrue(logradouro.getEndereco().contains(param));
			Assert.assertFalse(logradouro.getEndereco().equals(novoLogradouro2.getEndereco()));
		}
		logradouroRepositoy.delete(novoLogradouro1);
	}

	private Logradouro novoLogradouro(String endereco) {
		Logradouro logradouro1 = new Logradouro();
		logradouro1.setEndereco(endereco);
		logradouro1.setNumero(48);
		logradouro1.setComplemento("");
		logradouro1.setBairro("Jabaquara");
		logradouro1.setCep("23551000");
		logradouro1.setCidade("Ribeirão Pires");
		logradouro1.setEstado("SP");
		logradouro1.setDdd("11");
		logradouro1.setTelefone("32014568");
		logradouro1.setEmail("noberto@email.com");
		return logradouro1;
	}

}
