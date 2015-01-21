package com.projetos.controle_negocio.service.base;

import com.projetos.controle_entities.Parametro;

public interface ParametroService extends EntidadeService<Parametro> {

	/**
	 * @param chave
	 * @return Parametro a partir da chave passada
	 */
	Parametro getParametroByChave(String chave);

	Parametro getCaminhoImportacaoProdutos();

	Parametro getCaminhoRelatorioPedidos();

	Parametro getCaminhoRelatorioRecebimentos();

	Parametro getCaminhoBackupArquivos();

}
