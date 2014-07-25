package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ParametroService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ParametroCadastroController extends BaseCadastroController<Parametro> {

	@Autowired
	private ParametroService parametroService;

	@FXML
	@CampoTela(bean = "chave")
	private TextField codigo;

	@FXML
	@CampoTela(bean = "valor")
	private TextField valor;

	@FXML
	@CampoTela(bean = "descricao")
	private TextField descricao;

	@FXML
	private TableView<Parametro> tabela;

	@Override
	protected EntidadeService<Parametro> getEntidadeService() {
		return parametroService;
	}

}