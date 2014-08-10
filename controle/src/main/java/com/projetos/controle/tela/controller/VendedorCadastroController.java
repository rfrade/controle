package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.VendedorService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class VendedorCadastroController extends BaseCadastroController<Vendedor> {

	@Autowired
	private VendedorService vendedorService;

	@FXML
	@CampoTela(bean = "nome")
	private TextField nome;

	@FXML
	private TableView<Vendedor> tabela;

	@Override
	protected EntidadeService<Vendedor> getEntidadeService() {
		return vendedorService;
	}

}