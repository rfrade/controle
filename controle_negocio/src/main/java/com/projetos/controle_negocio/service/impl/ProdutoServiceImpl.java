package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.QProduto;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.ProdutoRepository;
import com.projetos.controle_negocio.service.base.ProdutoService;

@Service
public class ProdutoServiceImpl extends EntidadeServiceImpl<Produto> implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	protected EntidadeRepository<Produto> getRepository() {
		return produtoRepository;
	}

	@Override
	public EntityPathBase<Produto> getEntityPathBase() {
		return QProduto.produto;
	}

	

}
