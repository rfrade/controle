package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ParametroService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ParametroListaController extends BaseListController<Parametro> {

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private ParametroCadastroController parametroCadastroController;

	@FXML
	@Coluna(bean = "chave")
	private TableColumn<Cliente, String> colunaCodigo;

	@FXML
	@Coluna(bean = "valor")
	private TableColumn<Cliente, String> colunaValor;

	@FXML
	@Coluna(bean = "descricao")
	private TableColumn<Cliente, Integer> colunaDescricao;

	@FXML
	private TableView<Parametro> tabela;

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaParametroCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaParametroLista();
	}

	@Override
	protected EntidadeService<Parametro> getEntidadeService() {
		return parametroService;
	}

	@Override
	public TableView<Parametro> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Parametro> getBaseCadastroController() {
		return parametroCadastroController;
	}

}