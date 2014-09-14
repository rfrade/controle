package com.projetos.controle_negocio.repositoy;

import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;

public interface ProdutoRepository extends EntidadeRepository<Produto> {

	Produto getProdutoByReferenciaAndAtivoAndFornecedor(String referencia, boolean ativo, Fornecedor fornecedor);

}