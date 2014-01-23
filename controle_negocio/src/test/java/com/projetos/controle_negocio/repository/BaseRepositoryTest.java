package com.projetos.controle_negocio.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle_negocio.base.BaseNegocioTest;
import com.projetos.controle_negocio.repositoy.ClienteRepository;
import com.projetos.controle_negocio.repositoy.LogradouroRepository;

public abstract class BaseRepositoryTest extends BaseNegocioTest {

	@Autowired
	protected LogradouroRepository logradouroRepositoy;

	@Autowired
	protected ClienteRepository clienteRepositoy;
	
	protected Logger log = Logger.getLogger(this.getClass());

}
