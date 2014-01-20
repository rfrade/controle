package com.projetos.controle.tela.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle_negocio.service.base.EntidadeService;

public class PedidoController<T> extends BaseController {

	@FXML
	@CampoTela(bean = "")
	private Label numeroPedido;
	
	@FXML
	@CampoTela(bean = "fornecedor")
	private ChoiceBox fornecedor;
	
	@FXML
	@CampoTela(bean = "cliente")
	private Label cliente;

	@FXML
	@CampoTela(bean = "transportador")
	private TextField transportador;
	
	@FXML
	@CampoTela(bean = "condicoes")
	private TextField condicoes;
	
	@FXML
	@CampoTela(bean = "cobranca")
	private TextField cobrança;
	
	@FXML
	@CampoTela(bean = "comissao")
	private TextField comissao;
	
	@FXML
	@CampoTela(bean = "entrega")
	private TextField entrega;
	
	@FXML
	@CampoTela(bean = "colecao")
	private TextField colecao;
	
	@FXML
	@CampoTela(bean = "colecao")
	private TextArea observacao;

	@FXML
	@Coluna(bean = "referencia")
	private TableColumn referencia;
	
	@Override
	public List filtrar() {
		return null;
	}

	@Override
	public void exibirTelaCadastro() {
		
	}

	@Override
	public void exibirTelaLista() {
		
	}

	@Override
	protected EntidadeService getEntidadeService() {
		return null;
	}

	
}
