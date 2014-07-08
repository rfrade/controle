package com.projetos.controle_negocio.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.QProduto;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.ProdutoRepository;
import com.projetos.controle_negocio.service.base.ProdutoService;

@Service
public class ProdutoServiceImpl extends EntidadeServiceImpl<Produto> implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	protected EntidadeRepository<Produto> getRepository() {
		return produtoRepository;
	}

	@Override
	public EntityPathBase<Produto> getEntityPathBase() {
		return QProduto.produto;
	}

	@Override
	public String importarProdutosPlanilha(File file, Fornecedor fornecedor) throws NegocioException {
		List<Produto> produtos = getProdutosImportacao(file, fornecedor);
		String retorno = produtos.size() + " produtos foram importados. \nVerifique se esta é a quantidade de produtos no arquivo.";
		produtoRepository.save(produtos);
		return retorno;
	}

	/**
	 * Não exclui na base os produtos. Altera o campo ativo para false
	 * @param fornecedor
	 */
	@Override
	@Transactional
	public void removerTodos(Fornecedor fornecedor) {
		Filtro filtroFornecedor = new Filtro("fornecedor", TipoFiltro.OBJECT, Comparador.EQUALS, fornecedor);
		Filtro filtroAtivo = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		
		List<Produto> produtos = filtrar(filtroFornecedor, filtroAtivo);
		for (Produto produto : produtos) {
			produto.setAtivo(false);
			produto = produtoRepository.save(produto);
		}
	}

	@Override
	public List<Produto> getProdutosImportacao(File file, Fornecedor fornecedor) throws NegocioException {
		try {
			List<Produto> produtos = new ArrayList<>();

			InputStream inp = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(inp);
			HSSFSheet sheet = wb.getSheetAt(0);

			for (int numeroLinha = 1; numeroLinha < sheet.getPhysicalNumberOfRows(); numeroLinha++) {
				HSSFRow linha = sheet.getRow(numeroLinha);
				if (linha.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
					Produto produto = getProdutoLinha(linha);
					produto.setFornecedor(fornecedor);
					produtos.add(produto);
				}
			}

			return produtos;
		} catch (FileNotFoundException e) {
			throw new NegocioException(e);
		} catch (IOException e) {
			throw new NegocioException(e);
		}
	}


	private Produto getProdutoLinha(HSSFRow linha) {
		Produto produto = new Produto();

		HSSFCell celulaReferencia = linha.getCell(0);
		celulaReferencia.setCellType(HSSFCell.CELL_TYPE_STRING);
		produto.setReferencia(celulaReferencia.getStringCellValue());
		produto.setDescricao(linha.getCell(1).getStringCellValue());
		produto.setValorUnitario(linha.getCell(2).getNumericCellValue());

		HSSFCell celulaTamanho = linha.getCell(3);
		produto.setTamanho(getValorCelulaTamnho(celulaTamanho));
		return produto;
	}

	private String getValorCelulaTamnho(HSSFCell celulaTamanho) {
		String tamanho = null;
		switch (celulaTamanho.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			tamanho = celulaTamanho.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(celulaTamanho)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(celulaTamanho.getDateCellValue());
				tamanho = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
			}
			break;
		default:
			break;
		}
		return tamanho;
	}

	/**
	 * Retorna somente produtos ativos
	 */
	@Override
	public Produto getProdutoByReferencia(String referencia) {
		return produtoRepository.getProdutoByReferenciaAndAtivo(referencia, true);
	}

}
