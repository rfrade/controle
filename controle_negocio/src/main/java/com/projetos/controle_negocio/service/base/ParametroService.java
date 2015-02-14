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

	/**
	 * @return alguma coisa do tipo C:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump.exe
	 */
	Parametro getCaminhoMysqldump();

}
