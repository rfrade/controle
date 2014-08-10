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
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.VendedorService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class VendedorListaController extends BaseListController<Vendedor> {

	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private VendedorCadastroController vendedorCadastroController;

	@FXML
	@Coluna(bean = "nome")
	private TableColumn<Vendedor, String> colunaVendedor;

	@FXML
	private TableView<Vendedor> tabela;

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaVendedorCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaVendedorLista();
	}

	@Override
	protected EntidadeService<Vendedor> getEntidadeService() {
		return vendedorService;
	}

	@Override
	public TableView<Vendedor> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Vendedor> getBaseCadastroController() {
		return vendedorCadastroController;
	}

}