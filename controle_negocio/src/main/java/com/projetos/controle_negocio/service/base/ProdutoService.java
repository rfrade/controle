package com.projetos.controle_negocio.service.base;

import java.io.File;
import java.util.List;

import com.projetos.controle_entities.Produto;

public interface ProdutoService extends EntidadeService<Produto> {

	List<Produto> getProdutosImportacao(File file);

	void importarProdutosPlanilha(File file);

}
