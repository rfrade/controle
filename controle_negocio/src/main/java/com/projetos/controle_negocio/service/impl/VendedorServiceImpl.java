package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.VendedorRepository;
import com.projetos.controle_negocio.service.base.VendedorService;

@Service
public class VendedorServiceImpl extends EntidadeServiceImpl<Vendedor> implements VendedorService {

	@Autowired
	private VendedorRepository vendedorRepository;

	@Override
	protected EntidadeRepository<Vendedor> getRepository() {
		return vendedorRepository;
	}
}
