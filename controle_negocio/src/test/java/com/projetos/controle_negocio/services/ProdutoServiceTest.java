package com.projetos.controle_negocio.services;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;

public class ProdutoServiceTest extends BaseServiceTest {

	@Test
	public void getProdutosImportacao() throws NegocioException {
		URL file = Thread.currentThread().getContextClassLoader().getResource("arquivo_importacao.xls");
		
		Filtro filtro = new Filtro("firma", TipoFiltro.STRING, Comparador.CONTAINS_IGNORE_CASE, "L");
		Fornecedor fornecedor = fornecedorService.filtrar(filtro).get(0);
		List<Produto> produtosImportacao = produtoService.getProdutosImportacao(new File(file.getPath()), fornecedor);
		for (Produto produto : produtosImportacao) {
			Assert.assertNotNull(produto.getReferencia());
			Assert.assertNotNull(produto.getTamanho());
			Assert.assertNotNull(produto.getValorUnitario());
			Assert.assertNotNull(produto.getDescricao());
		}
		produtoService.importarProdutosPlanilha(new File(file.getFile()), fornecedor);
	}

}
