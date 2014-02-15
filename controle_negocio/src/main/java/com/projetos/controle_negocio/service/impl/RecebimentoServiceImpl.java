package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.QRecebimento;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.RecebimentoRepository;
import com.projetos.controle_negocio.service.base.RecebimentoService;

@Service
public class RecebimentoServiceImpl extends EntidadeServiceImpl<Recebimento> implements RecebimentoService {

	@Autowired
	private RecebimentoRepository recebimentoRepository;

	@Override
	protected EntidadeRepository<Recebimento> getRepository() {
		return recebimentoRepository;
	}

	@Override
	public EntityPathBase<Recebimento> getEntityPathBase() {
		return QRecebimento.recebimento;
	}

}
