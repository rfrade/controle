package com.projetos.controle_negocio.service.base;

import com.projetos.controle_entities.Parametro;
import com.projetos.controle_negocio.exception.NegocioException;

public interface ParametroService extends EntidadeService<Parametro> {

	/**
	 * @param chave
	 * @return Parametro a partir da chave passada
	 */
	Parametro getParametroByChave(String chave) throws NegocioException;

	Parametro getCaminhoImportacaoProdutos() throws NegocioException;

	Parametro getCaminhoRelatorioPedidos() throws NegocioException;

	Parametro getCaminhoRelatorioRecebimentos() throws NegocioException;

	Parametro getCaminhoBackupArquivos() throws NegocioException;

	/**
	 * @return alguma coisa do tipo C:\Program Files\MySQL\MySQL Server 5.5\bin\mysqldump.exe
	 */
	Parametro getCaminhoMysqldump() throws NegocioException;

	/**
	 * @return número de meses anteriores ao atual até o qual os
	 * pedidos serão consultados. Por exemplo, se retornar 3,
	 * serão consultados pedidos dos 3 meses anteriores
	 * @throws NegocioException 
	 */
	Parametro getNumeroDeMesesConsultaPedido() throws NegocioException;

}
