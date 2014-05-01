package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ProdutoService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ProdutoCadastroController extends BaseCadastroController<Produto> {

	@Autowired
	private ProdutoService produtoService;

	@FXML
	@CampoTela(bean = "referencia")
	private TextField referencia;
	
	@FXML
	@CampoTela(bean = "valorUnitario")
	private TextField valorVenda;
	
	@FXML
	@CampoTela(bean = "descricao")
	private TextField descricao;
	
	@FXML
	@CampoTela(bean = "tamanho")
	private TextField tamanho;
	
	@Override
	protected EntidadeService<Produto> getEntidadeService() {
		return produtoService;
	}

}