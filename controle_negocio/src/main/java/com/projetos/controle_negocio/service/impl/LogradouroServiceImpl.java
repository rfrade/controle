package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Logradouro;
import com.projetos.controle_entities.QLogradouro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.LogradouroRepository;
import com.projetos.controle_negocio.service.base.LogradouroService;

@Service
@Lazy
public class LogradouroServiceImpl extends EntidadeServiceImpl<Logradouro> implements LogradouroService {

	@Autowired
	private LogradouroRepository logradouroRepositoy;

	@Override
	protected EntidadeRepository<Logradouro> getRepository() {
		return logradouroRepositoy;
	}

	@Override
	public EntityPathBase<Logradouro> getEntityPathBase() {
		return QLogradouro.logradouro;
	}

}