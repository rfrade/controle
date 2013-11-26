package com.projetos.controle_negocio.services;

import org.apache.log4j.Logger;

import com.projetos.controle_negocio.service.base.LogradouroService;
import com.projetos.controle_negocio.service.impl.LogradouroServiceImpl;

public abstract class BaseServiceTest {

	protected Logger log = Logger.getLogger(this.getClass());
	protected LogradouroService logradouroService;

	public BaseServiceTest() {
		log.info("Iniciando teste...");
		logradouroService = new LogradouroServiceImpl();
	}

}
