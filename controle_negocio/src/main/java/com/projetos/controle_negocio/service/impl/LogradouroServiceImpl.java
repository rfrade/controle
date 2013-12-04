package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.controle_entities.Logradouro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.LogradouroRepositoy;
import com.projetos.controle_negocio.service.base.LogradouroService;

@Service
public class LogradouroServiceImpl extends EntidadeServiceImpl<Logradouro> implements LogradouroService {

	@Autowired
	private LogradouroRepositoy logradouroRepositoy;

	@Override
	protected EntidadeRepository<Logradouro> getRepository() {
		return logradouroRepositoy;
	}

}