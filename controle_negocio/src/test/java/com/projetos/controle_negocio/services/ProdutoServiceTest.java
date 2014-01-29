package com.projetos.controle_negocio.services;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_entities.Produto;

public class ProdutoServiceTest extends BaseServiceTest {

	@Test
	public void getProdutosImportacao() {
		URL file = Thread.currentThread().getContextClassLoader().getResource("arquivo_importacao.xls");
		List<Produto> produtosImportacao = produtoService.getProdutosImportacao(new File(file.getPath()));
		for (Produto produto : produtosImportacao) {
			Assert.assertNotNull(produto.getReferencia());
			Assert.assertNotNull(produto.getTamanho());
			Assert.assertNotNull(produto.getValorUnitario());
			Assert.assertNotNull(produto.getDescricao());
		}
	}

}
