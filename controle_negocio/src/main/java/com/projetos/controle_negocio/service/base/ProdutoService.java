package com.projetos.controle_negocio.service.base;

import java.io.File;
import java.util.List;

import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.exception.NegocioException;

public interface ProdutoService extends EntidadeService<Produto> {

	List<Produto> getProdutosImportacao(File file, Fornecedor fornecedor) throws NegocioException;

	String importarProdutosPlanilha(File file, Fornecedor fornecedor) throws NegocioException;

	Produto getProdutoByReferencia(String referencia, Fornecedor fornecedor);
	
	/**
	 * Não exclui na base os produtos. Altera o campo ativo para false
	 * @param fornecedor
	 */
	void removerTodos(Fornecedor fornecedor);

	void desativarProdutos(String referencia, Fornecedor fornecedor);
	
}
