package com.projetos.controle_negocio.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.QProduto;
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
	public void importarProdutosPlanilha(File file) {
		List<Produto> produtos = getProdutosImportacao(file);
		produtoRepository.save(produtos);
	}

	@Override
	public List<Produto> getProdutosImportacao(File file) {
		try {
			List<Produto> produtos = new ArrayList<>();

			InputStream inp = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(inp);
			HSSFSheet sheet = wb.getSheetAt(0);

			for (int numeroLinha = 1; numeroLinha < sheet.getPhysicalNumberOfRows(); numeroLinha++) {
				HSSFRow linha = sheet.getRow(numeroLinha);
				if (linha.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
					Produto produto = getProdutoLinha(linha);
					produtos.add(produto);
				}
			}

			return produtos;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
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

}
