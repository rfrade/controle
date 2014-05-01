package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseEntityController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.PedidoService;

@Controller
@Lazy
public class PedidoController /*extends BaseController<Pedido>*/ {/*

	@Autowired
	private PedidoService pedidoService;

	// Filtros
	@FXML
	@FiltroTela(campo = "fornecedor", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private ChoiceBox<ItemCombo<Fornecedor>> filtroFornecedor;
	
	@FXML
	@FiltroTela(campo = "logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;

	@FXML
	@FiltroTela(campo = "cliente.firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCliente;

	@FXML
	@Coluna(bean = "id")
	private TableColumn<Produto, String> colunaNumeroPedido;
	
	// Colunas Tabela
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
	@Coluna(bean = "logradouro.cidade")
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
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor.firma")
	private ChoiceBox<ItemCombo<Fornecedor>> fornecedor;

	@FXML
	@CampoTela(bean = "cliente.firma")
	private Label cliente;

	@FXML
	@CampoTela(bean = "transportador")
	private TextField transportador;

	@FXML
	@CampoTela(bean = "condicoes")
	private TextField condicoes;

	@FXML
	@CampoTela(bean = "cobranca")
	private TextField cobranca;

	@FXML
	@CampoTela(bean = "comissao")
	private TextField comissao;

	@FXML
	@CampoTela(bean = "entrega")
	private TextField entrega;

	@FXML
	@CampoTela(bean = "vendedor.nome")
	private ChoiceBox<ItemCombo<Vendedor>> vendedor;

	@FXML
	@CampoTela(bean = "colecao")
	private TextField colecao;

	@FXML
	@CampoTela(bean = "observacao")
	private TextArea observacao;
	
	@FXML
	@CampoTela(bean = "desconto1")
	private TextField desconto1;
	
	@FXML
	@CampoTela(bean = "desconto2")
	private TextField desconto2;
	
	@FXML
	@CampoTela(bean = "desconto3")
	private TextField desconto3;
	
	@FXML
	@CampoTela(bean = "desconto4")
	private TextField desconto4;
	
	@FXML
	@CampoTela(bean = "descontoTotal")
	private TextField descontoTotal;

	@FXML
	private TableView<Pedido> tabela;

	@Override
	public void exibirTelaCadastro() {
		entidadeForm = new Pedido();
		telaPrincipalController.exibirTelaPedidoCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaPedidoLista();
	}

	public void calcularDesconto() {

	}

	public void procurarCliente() {

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

	public Label getLabelNumeroPedido() {
		return labelNumeroPedido;
	}

	public void setLabelNumeroPedido(Label labelNumeroPedido) {
		this.labelNumeroPedido = labelNumeroPedido;
	}

	public ChoiceBox<ItemCombo<Fornecedor>> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ChoiceBox<ItemCombo<Fornecedor>> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Label getCliente() {
		return cliente;
	}

	public void setCliente(Label cliente) {
		this.cliente = cliente;
	}

	public TextField getTransportador() {
		return transportador;
	}

	public void setTransportador(TextField transportador) {
		this.transportador = transportador;
	}

	public TextField getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(TextField condicoes) {
		this.condicoes = condicoes;
	}

	public TextField getCobranca() {
		return cobranca;
	}

	public void setCobranca(TextField cobranca) {
		this.cobranca = cobranca;
	}

	public TextField getComissao() {
		return comissao;
	}

	public void setComissao(TextField comissao) {
		this.comissao = comissao;
	}

	public TextField getEntrega() {
		return entrega;
	}

	public void setEntrega(TextField entrega) {
		this.entrega = entrega;
	}

	public ChoiceBox<ItemCombo<Vendedor>> getVendedor() {
		return vendedor;
	}

	public void setVendedor(ChoiceBox<ItemCombo<Vendedor>> vendedor) {
		this.vendedor = vendedor;
	}

	public TextField getColecao() {
		return colecao;
	}

	public void setColecao(TextField colecao) {
		this.colecao = colecao;
	}

	public TextArea getObservacao() {
		return observacao;
	}

	public void setObservacao(TextArea observacao) {
		this.observacao = observacao;
	}

	public TextField getDesconto1() {
		return desconto1;
	}

	public void setDesconto1(TextField desconto1) {
		this.desconto1 = desconto1;
	}

	public TextField getDesconto2() {
		return desconto2;
	}

	public void setDesconto2(TextField desconto2) {
		this.desconto2 = desconto2;
	}

	public TextField getDesconto3() {
		return desconto3;
	}

	public void setDesconto3(TextField desconto3) {
		this.desconto3 = desconto3;
	}

	public TextField getDesconto4() {
		return desconto4;
	}

	public void setDesconto4(TextField desconto4) {
		this.desconto4 = desconto4;
	}

	public TextField getDescontoTotal() {
		return descontoTotal;
	}

	public void setDescontoTotal(TextField descontoTotal) {
		this.descontoTotal = descontoTotal;
	}

	public void setTabela(TableView<Pedido> tabela) {
		this.tabela = tabela;
	}

*/}
