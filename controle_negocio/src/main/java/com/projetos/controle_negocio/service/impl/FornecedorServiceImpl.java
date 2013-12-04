package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.FornecedorRepository;
import com.projetos.controle_negocio.service.base.FornecedorService;

@Service
public class FornecedorServiceImpl extends EntidadeServiceImpl<Fornecedor> implements FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Override
	protected EntidadeRepository<Fornecedor> getRepository() {
		return fornecedorRepository;
	}

}
