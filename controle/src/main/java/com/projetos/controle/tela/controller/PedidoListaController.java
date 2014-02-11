package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.PedidoService;

@Controller
@Lazy
public class PedidoListaController extends BaseListController<Pedido> {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private PedidoCadastroController pedidoCadastroController;

	@FXML
	@FiltroTela(campo = "fornecedor", tipo = TipoFiltro.STRING, comparador = Comparador.EQUALS)
	private ChoiceBox<ItemCombo<Fornecedor>> filtroFornecedor;
	
	@FXML
	@FiltroTela(campo = "cliente.logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;

	@FXML
	@FiltroTela(campo = "cliente.firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCliente;

	@FXML
	@Coluna(bean = "id")
	private TableColumn<Produto, String> colunaNumeroPedido;
	
	@FXML
	@Coluna(bean = "dataPedido")
	private TableColumn<Produto, String> colunaData;
	
	@FXML
	@Coluna(bean = "cliente.firma")
	private TableColumn<Pedido, String> colunaCliente;
	
	@FXML
	@Coluna(bean = "fornecedor.firma")
	private TableColumn<Produto, String> colunaFornecedor;
	
	@FXML
	@Coluna(bean = "cliente.logradouro.cidade")
	private TableColumn<Produto, String> colunaCidade;
	
	@FXML
	@Coluna(bean = "transportador")
	private TableColumn<Produto, String> colunaTransportador;
	
	@FXML
	@Coluna(bean = "condicoes")
	private TableColumn<Produto, String> colunaCondicoes;
	
	@FXML
	@Coluna(bean = "cobranca")
	private TableColumn<Produto, String> colunaCobranca;

	@FXML
	private TableView<Pedido> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		ObservableList<ItemCombo<Fornecedor>> itens = ItemCombo.novaListaCombo(fornecedorService.listar(), "firma");
		filtroFornecedor.setItems(itens);
	}

	@Override
	public void exibirTelaCadastro() {
		entidadeForm = new Pedido();
		telaPrincipalController.exibirTelaPedidoCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaPedidoLista();
	}

	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
	}

	@Override
	public TableView<Pedido> getTabela() {
		return tabela;
	}

	public TableColumn<Produto, String> getColunaNumeroPedido() {
		return colunaNumeroPedido;
	}

	public void setColunaNumeroPedido(
			TableColumn<Produto, String> colunaNumeroPedido) {
		this.colunaNumeroPedido = colunaNumeroPedido;
	}

	public ChoiceBox<ItemCombo<Fornecedor>> getFiltroFornecedor() {
		return filtroFornecedor;
	}

	public void setFiltroFornecedor(
			ChoiceBox<ItemCombo<Fornecedor>> filtroFornecedor) {
		this.filtroFornecedor = filtroFornecedor;
	}

	public TextField getFiltroCidade() {
		return filtroCidade;
	}

	public void setFiltroCidade(TextField filtroCidade) {
		this.filtroCidade = filtroCidade;
	}

	public TextField getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(TextField filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public TableColumn<Produto, String> getColunaData() {
		return colunaData;
	}

	public void setColunaData(TableColumn<Produto, String> colunaData) {
		this.colunaData = colunaData;
	}

	public TableColumn<Pedido, String> getColunaCliente() {
		return colunaCliente;
	}

	public void setColunaCliente(TableColumn<Pedido, String> colunaCliente) {
		this.colunaCliente = colunaCliente;
	}

	public TableColumn<Produto, String> getColunaFornecedor() {
		return colunaFornecedor;
	}

	public void setColunaFornecedor(TableColumn<Produto, String> colunaFornecedor) {
		this.colunaFornecedor = colunaFornecedor;
	}

	public TableColumn<Produto, String> getColunaCidade() {
		return colunaCidade;
	}

	public void setColunaCidade(TableColumn<Produto, String> colunaCidade) {
		this.colunaCidade = colunaCidade;
	}

	public TableColumn<Produto, String> getColunaTransportador() {
		return colunaTransportador;
	}

	public void setColunaTransportador(
			TableColumn<Produto, String> colunaTransportador) {
		this.colunaTransportador = colunaTransportador;
	}

	public TableColumn<Produto, String> getColunaCondicoes() {
		return colunaCondicoes;
	}

	public void setColunaCondicoes(TableColumn<Produto, String> colunaCondicoes) {
		this.colunaCondicoes = colunaCondicoes;
	}

	public TableColumn<Produto, String> getColunaCobranca() {
		return colunaCobranca;
	}

	public void setColunaCobranca(TableColumn<Produto, String> colunaCobranca) {
		this.colunaCobranca = colunaCobranca;
	}

	public void setTabela(TableView<Pedido> tabela) {
		this.tabela = tabela;
	}

	@Override
	protected BaseCadastroController<Pedido> getBaseCadastroController() {
		return pedidoCadastroController;
	}

}
