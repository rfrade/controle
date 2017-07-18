package com.projetos.controle_negocio.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

/**
 * Responsável pela importação dos dados do arquivo XLS/XLSX e persistência na base
 * 
 * @author Rafael
 */
@Service
@Lazy
public class ProdutoServiceImpl extends EntidadeServiceImpl<Produto> implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntidadeRepository<Produto> getRepository() {
		return produtoRepository;
	}

	@Override
	public EntityPathBase<Produto> getEntityPathBase() {
		return QProduto.produto;
	}

	@Override
	public void remover(Produto produto) {
		produto.setAtivo(false);
		produto = produtoRepository.save(produto);
	}

	@Override
	public Integer importarProdutosPlanilha(File file, Fornecedor fornecedor) throws NegocioException {
		List<Produto> produtos = this.getProdutosImportacao(file, fornecedor);
		produtoRepository.save(produtos);
		return produtos.size();
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
			this.remover(produto);
		}
	}

	@Override
	public List<Produto> getProdutosImportacao(File file, Fornecedor fornecedor) throws NegocioException {
		try {
			List<Produto> produtos = new ArrayList<>();

			/*
			 * Arquivos XLS devem serimportados utilizando as classes HSSF;
			 * Arquivos XLSX devem serimportados utilizando as classes XSSF
			 */
			if (file.getName().endsWith(".xls")) {
				produtos = importarXLS(file, fornecedor, produtos);

			} else if (file.getName().endsWith(".xlsx")) {
				produtos = importarXLSX(file, fornecedor, produtos);
				
			}

			return produtos;
		
		} catch (IOException e) {
			throw new NegocioException(e);
		
		} catch (IllegalStateException e) {
			String message = "produto.colunas_erradas";
			NegocioException negocioException = new NegocioException(message, e);
			throw negocioException;
	
		}
	}

	private List<Produto> importarXLS(File file, Fornecedor fornecedor,
			List<Produto> produtos) throws FileNotFoundException, IOException {
		InputStream inp = new FileInputStream(file);
		
		HSSFWorkbook wb = new HSSFWorkbook(inp);
		HSSFSheet sheet = wb.getSheetAt(0);

		for (int numeroLinha = 0; numeroLinha < sheet.getPhysicalNumberOfRows(); numeroLinha++) {
			HSSFRow linha = sheet.getRow(numeroLinha);
			if ((linha.getCell(0) != null && linha.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK)) {

				Produto produto = getProdutoLinhaXLS(linha);

				desativarProdutos(produto.getReferencia(), fornecedor);

				produto.setFornecedor(fornecedor);
				produtos.add(produto);
			}
		}
		return produtos;
	}

	private List<Produto> importarXLSX(File file, Fornecedor fornecedor,
			List<Produto> produtos) throws FileNotFoundException, IOException {
		InputStream inp = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(inp);
		XSSFSheet sheet = wb.getSheetAt(0);

		for (int numeroLinha = 1; numeroLinha < sheet.getPhysicalNumberOfRows(); numeroLinha++) {
			XSSFRow linha = sheet.getRow(numeroLinha);
			if (linha.getCell(0).getCellType() != XSSFCell.CELL_TYPE_BLANK) {
				Produto produto = getProdutoLinhaXLSX(linha);

				desativarProdutos(produto.getReferencia(), fornecedor);

				produto.setFornecedor(fornecedor);
				produtos.add(produto);
			}
		}
		return produtos;
	}


	private Produto getProdutoLinhaXLS(HSSFRow linha) {

		HSSFCell celulaReferencia = linha.getCell(0);
		celulaReferencia.setCellType(HSSFCell.CELL_TYPE_STRING);

		HSSFCell celulaTamanho = linha.getCell(3);
		String referencia = celulaReferencia.getStringCellValue();
		String descricao = linha.getCell(1).getStringCellValue();
		String tamanho = getValorCelulaTamanhoXLS(celulaTamanho);
		double valorUnitario = linha.getCell(2).getNumericCellValue();

		Produto produto = novoObjetoProduto(referencia, descricao, tamanho, valorUnitario);

		return produto;
	}

	private Produto getProdutoLinhaXLSX(XSSFRow linha) {

		XSSFCell celulaReferencia = linha.getCell(0);
		celulaReferencia.setCellType(HSSFCell.CELL_TYPE_STRING);

		XSSFCell celulaTamanho = linha.getCell(3);
		String referencia = celulaReferencia.getStringCellValue();
		String descricao = linha.getCell(1).getStringCellValue();
		String tamanho = getValorCelulaTamanhoXLSX(celulaTamanho);
		double valorUnitario = linha.getCell(2).getNumericCellValue();

		Produto produto = novoObjetoProduto(referencia, descricao, tamanho, valorUnitario);

		return produto;
	}

	/**
	 * Cria um objeto produto, não faz nenhuma persistência
	 * 
	 * @param produto
	 * @param referencia
	 * @param descricao
	 * @param tamanho
	 * @param valorUnitario
	 */
	private Produto novoObjetoProduto(String referencia,
			String descricao, String tamanho, double valorUnitario) {
		Produto produto = new Produto();
		
		produto.setReferencia(referencia);
		produto.setDescricao(descricao);
		produto.setValorUnitario(valorUnitario);
		produto.setAtivo(true);
		produto.setTamanho(tamanho);
		
		return produto;
	}

	private String getValorCelulaTamanhoXLS(HSSFCell celulaTamanho) {
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


	private String getValorCelulaTamanhoXLSX(XSSFCell celulaTamanho) {
		String tamanho = null;
		switch (celulaTamanho.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			tamanho = celulaTamanho.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
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

	
	public void desativarProdutos(String referencia, Fornecedor fornecedor) {
		String queryString = "update Produto p set p.ativo = false where p.referencia = :referencia ";
		queryString += " and p.ativo = true and p.fornecedor = :fornecedor";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("referencia", referencia);
		query.setParameter("fornecedor", fornecedor);
		query.executeUpdate();
	}

	/**
	 * Retorna somente produtos ativos
	 */
	@Override
	public Produto getProdutoByReferencia(String referencia, Fornecedor fornecedor) {
		return produtoRepository.getProdutoByReferenciaAndAtivoAndFornecedor(referencia, true, fornecedor);
	}

}
