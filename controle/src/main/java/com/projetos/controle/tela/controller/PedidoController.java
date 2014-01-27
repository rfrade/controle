package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.PedidoService;

public class PedidoController extends BaseController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@FXML
	@CampoTela(bean = "id")
	private Label numeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor")
	private ChoiceBox<Fornecedor> fornecedor;

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
	@CampoTela(bean = "observacao")
	private TextArea observacao;

	@FXML
	@Coluna(bean = "referencia")
	private TableColumn<Produto, String> referencia;

	@Override
	public void exibirTelaCadastro() {

	}

	@Override
	public void exibirTelaLista() {

	}

	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
	}

}
