package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.QCliente;
import com.projetos.controle_negocio.repositoy.ClienteRepository;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.service.base.ClienteService;

@Service
public class ClienteServiceImpl extends EntidadeServiceImpl<Cliente> implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	protected EntidadeRepository<Cliente> getRepository() {
		return clienteRepository;
	}

	@Override
	public EntityPathBase<Cliente> getEntityPathBase() {
		return QCliente.cliente;
	}

}
