package com.projetos.controle_negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.jpa.impl.JPAQuery;
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

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cliente> filtrar(Cliente filtro) {
		QCliente qCliente = QCliente.cliente;

		List<BooleanExpression> predicados = new ArrayList<>();
		
		if (filtro.getFirma() != null && !filtro.getFirma().equals("")) {
			BooleanExpression firma = qCliente.firma.contains(filtro.getFirma());
			predicados.add(firma);
		}

		
		BooleanExpression ativo = qCliente.ativo.eq(filtro.isAtivo());
		predicados.add(ativo);

		String textoCidade = filtro.getLogradouro().getCidade();
		if (textoCidade != null && !textoCidade.equals("")) {
			BooleanExpression cidade = qCliente.logradouro.cidade.contains(textoCidade);
			predicados.add(cidade);
		}
		JPAQuery jpaQuery = new JPAQuery(entityManager);
		Predicate[] predicadosArray = predicados.toArray(new Predicate[predicados.size()]);

//		return jpaQuery.from(QCliente.cliente).where(predicadosArray).list(qCliente);
		
		Iterable<Cliente> resultado = clienteRepository.findAll(getPredicados(predicados));
		return ColecaoUtil.converterParaLista(resultado);
	}

	private Predicate getPredicados(List<BooleanExpression> predicados) {
		BooleanExpression predicado = predicados.get(0);
		for (int i = 1; i < predicados.size(); i++) {
			predicado = predicado.and(predicados.get(i));
		}
		return predicado;
	}

	@Override
	protected EntidadeRepository<Cliente> getRepository() {
		return clienteRepository;
	}

}
