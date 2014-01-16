package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.repositoy.ClienteRepository;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.service.base.ClienteService;

@Service
public class ClienteServiceImpl extends EntidadeServiceImpl<Cliente> implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void filtrar() {
		Cliente cliente;
		/*QCliente qcliente;*/
	}

	@Override
	protected EntidadeRepository<Cliente> getRepository() {
		return clienteRepository;
	}

}
