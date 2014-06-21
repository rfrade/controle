package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableView;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseEntityController;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.RecebimentoService;

public class RelatorioRecebimentoListaController extends BaseEntityController<Recebimento> {

	@FXML
	private Label labelValorTotal;
	
	@FXML
	private Label labelValorComissao;
	
	@FXML
	private Label labelQuantidadeItens;

	@FXML
	private RecebimentoService recebimentoService;

	@FXML
	private TreeTableView<Recebimento> treeTableView;

	@FXML
	@Coluna(bean = "pedido.id")
	private TableColumn<Recebimento, String> colunaPedido;
	
	@FXML
	@Coluna(bean = "pedido.data")
	private TableColumn<Recebimento, Date> colunaDataRecebimento;

	@FXML
	@Coluna(bean = "cliente.firma")
	private TableColumn<Recebimento, Date> colunaCliente;
	
	@FXML
	@Coluna(bean = "pedido.cliente.logradouro.cidade")
	private TableColumn<Recebimento, Date> colunaMunicipio;
	
	@FXML
	@Coluna(bean = "pedido.cobranca")
	private TableColumn<Recebimento, Date> colunaCobranca;
	
	@FXML
	@Coluna(bean = "pedido.valorTotal")
	private TableColumn<Recebimento, String> colunaValorPedido;
	
	@FXML
	@Coluna(bean = "recebido")
	private TableColumn<Recebimento, String> colunaRecebido;
	
	@FXML
	@Coluna(bean = "pedido.colecao")
	private TableColumn<Recebimento, String> colunaColecao;
	
	@FXML
	@Coluna(bean = "valorRecebimento")
	private TableColumn<Recebimento, String> colunaQuantidadeItens;
	
	public void exibirTelaPesquisa() {
		telaPrincipalController.exibirTelaRelatorioRecebimentos();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

	@Override
	public void remover() {
		
	}

}
