package com.projetos.controle_negocio.services;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projetos.controle_negocio.base.BaseNegocioTest;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.LogradouroService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/services-test.xml"})
public abstract class BaseServiceTest extends BaseNegocioTest {

	protected Logger log = Logger.getLogger(this.getClass());

	@Autowired
	protected LogradouroService logradouroService;
	
	@Autowired
	protected ClienteService clienteService;

	public BaseServiceTest() {
		log.info("Iniciando teste...");
	}

}
