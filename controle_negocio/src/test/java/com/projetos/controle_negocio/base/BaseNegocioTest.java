package com.projetos.controle_negocio.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projetos.controle_entities.Logradouro;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/services-test.xml" })
public class BaseNegocioTest {

	protected Logradouro novoLogradouro(String endereco) {
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
		logradouro1.setEmail("roberto@email.com");
		return logradouro1;
	}
	
	protected Logradouro novoLogradouro1() {
		Logradouro logradouro1 = new Logradouro();
		logradouro1.setEndereco("Rua Norberto Ferreira");
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

	protected Logradouro novoLogradouro2() {
		Logradouro logradouro1 = new Logradouro();
		logradouro1.setEndereco("Rua Ruy Braga");
		logradouro1.setNumero(1050);
		logradouro1.setComplemento("12");
		logradouro1.setBairro("Campo Limpo");
		logradouro1.setCep("02444000");
		logradouro1.setCidade("São Paulo");
		logradouro1.setEstado("SP");
		logradouro1.setDdd("11");
		logradouro1.setTelefone("37811333");
		logradouro1.setEmail("ruy@email.com");
		return logradouro1;
	}


}
