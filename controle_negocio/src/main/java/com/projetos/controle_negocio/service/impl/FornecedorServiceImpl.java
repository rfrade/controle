package com.projetos.controle_negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.QFornecedor;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.FornecedorRepository;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_util.collection.ColecaoUtil;

@Service
public class FornecedorServiceImpl extends EntidadeServiceImpl<Fornecedor> implements FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> filtrar(Fornecedor filtro) {
		QFornecedor qFornecedor = QFornecedor.fornecedor;
		BooleanExpression firma = qFornecedor.firma.contains(filtro.getFirma());
		BooleanExpression ativo = qFornecedor.ativo.eq(filtro.isAtivo());
		BooleanExpression cidade = qFornecedor.logradouro.cidade.contains(filtro.getLogradouro().getCidade());
		Iterable<Fornecedor> resultado = fornecedorRepository.findAll(firma.and(ativo).and(cidade));
		return ColecaoUtil.converterParaLista(resultado);
	}

	@Override
	protected EntidadeRepository<Fornecedor> getRepository() {
		return fornecedorRepository;
	}

}
