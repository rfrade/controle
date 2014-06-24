package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_entities.QParametro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.ParametroRepository;
import com.projetos.controle_negocio.service.base.ParametroService;

@Service
public class ParametroServiceImpl extends EntidadeServiceImpl<Parametro> implements ParametroService {

	@Autowired
	private ParametroRepository parametroRepository;

	@Override
	protected EntidadeRepository<Parametro> getRepository() {
		return parametroRepository;
	}

	/**
	 * @param chave
	 * @return Parametro a partir da chave passada
	 */
	public Parametro getParametroByChave(String chave) {
		return parametroRepository.getParametroByChave(chave);
	}

	public Parametro getCaminhoImportacaoProdutos() {
		String chave = "caminho_diretorio_importacao";
		return this.getParametroByChave(chave);
	}

	@Override
	public EntityPathBase<Parametro> getEntityPathBase() {
		return QParametro.parametro;
	}

	@Override
	public Parametro getCaminhoRelatorioPedidos() {
		return this.getParametroByChave("caminho_relatorio_pedido");
	}

	@Override
	public Parametro getCaminhoRelatorioRecebimentos() {
		return this.getParametroByChave("caminho_relatorio_recebimentos");
	}

}
