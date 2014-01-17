package com.projetos.controle_negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.QCliente;
import com.projetos.controle_negocio.repositoy.ClienteRepository;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_util.collection.ColecaoUtil;

@Service
public class ClienteServiceImpl extends EntidadeServiceImpl<Cliente> implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> filtrar(Cliente filtro) {
		QCliente qCliente = QCliente.cliente;

		List<Predicate> predicados = new ArrayList<>();
		
		if (filtro.getFirma() != null && !filtro.getFirma().equals("")) {
			BooleanExpression firma = qCliente.firma.contains(filtro.getFirma());
		}
		BooleanExpression ativo = qCliente.ativo.eq(filtro.isAtivo());

		String textoCidade = filtro.getLogradouro().getCidade();
		if (textoCidade != null && !textoCidade.equals("")) {
			BooleanExpression cidade = qCliente.logradouro.cidade.contains(textoCidade);
		}

		Iterable<Cliente> resultado = clienteRepository.findAll(firma.and(ativo).and(textoCidade));
		return ColecaoUtil.converterParaLista(resultado);
	}

	@Override
	protected EntidadeRepository<Cliente> getRepository() {
		return clienteRepository;
	}

}
