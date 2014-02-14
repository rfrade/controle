package com.projetos.controle.tela.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.VendedorService;

@Controller
@Lazy
public class PedidoCadastroController extends BaseCadastroController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private ItemPedidoCadastroController itemPedidoCadastroController;

	@FXML
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor")
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
	@CampoTela(bean = "vendedor")
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
	@Coluna(bean = "itensPedido.produto.referencia")
	private TableColumn<Produto, String> referencia;
	
	@FXML
	@Coluna(bean = "itensPedido.cor")
	private TableColumn<Produto, String> cor;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTotal")
	private TableColumn<Produto, String> quantidadeTotal;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho1")
	private TableColumn<Produto, String> quantidadeTamanho1;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho2")
	private TableColumn<Produto, String> quantidadeTamanho2;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho3")
	private TableColumn<Produto, String> quantidadeTamanho3;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho4")
	private TableColumn<Produto, String> quantidadeTamanho4;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho5")
	private TableColumn<Produto, String> quantidadeTamanho5;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho6")
	private TableColumn<Produto, String> quantidadeTamanho6;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho7")
	private TableColumn<Produto, String> quantidadeTamanho7;
	
	@FXML
	@Coluna(bean = "itensPedido.quantidadeTamanho8")
	private TableColumn<Produto, String> quantidadeTamanho8;

	@FXML
	@Coluna(bean = "itensPedido.produto.valorUnitario")
	private TableColumn<Produto, String> valorUnitario;

	@FXML
	@Coluna(bean = "itensPedido.descricao")
	private TableColumn<Produto, String> descricao;
	
	@FXML
	@Coluna(bean = "itensPedido.observacao")
	private TableColumn<Produto, String> observacaoItemPedido;

	@FXML
	private TableView<ItemPedido> tabelaItensPedido;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		ObservableList<ItemCombo<Fornecedor>> itensFornecedor = ItemCombo.novaListaCombo(fornecedorService.listar(), "firma");
		fornecedor.setItems(itensFornecedor);

		ObservableList<ItemCombo<Vendedor>> itensVendedor = ItemCombo.novaListaCombo(vendedorService.listar(), "nome");
		vendedor.setItems(itensVendedor);

		if (entidadeForm != null) {
			ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList(entidadeForm.getItensPedido());
			tabelaItensPedido.setItems(itensPedido);
		}
		
		InnerMouseClicked<ItemPedido> mouseClicked = new InnerMouseClicked<>(tabelaItensPedido, itemPedidoCadastroController);
		tabelaItensPedido.setOnMouseClicked(mouseClicked);
	}

	public void exibirTelaCliente() {
		Parent telaClienteLista = configuracaoBeanTela.carregarTelaClienteLista();
		Stage popup = telaPrincipalController.exibirPopup(telaClienteLista);
		ClienteListaController controller = ApplicationConfig.getBean(ClienteListaController.class);
		MouseClickedSelect mouseClickedSelecPedido = new MouseClickedSelect(controller.getTabela(), popup);
		controller.getTabela().setOnMouseClicked(mouseClickedSelecPedido);
	}

	public void calcularDesconto() {
		BigDecimal valor1 = getValorDesconto(desconto1);
		BigDecimal valor2 = getValorDesconto(desconto2);
		BigDecimal valor3 = getValorDesconto(desconto3);
		BigDecimal valor4 = getValorDesconto(desconto4);

		List<BigDecimal> descontos = Arrays.asList(valor1, valor2, valor3, valor4);
		
		BigDecimal descontoFinal = BigDecimal.ZERO;
		BigDecimal cem = new BigDecimal(100);
		for (BigDecimal valor : descontos) {
			if (valor != null && !valor.equals(BigDecimal.ZERO)) {
				BigDecimal valorAbatido = valor.divide(cem).multiply(descontoFinal);
				descontoFinal = descontoFinal.add(valor.subtract(valorAbatido));
			}
		}

		descontoFinal = descontoFinal.setScale(2, RoundingMode.DOWN);
		descontoTotal.setText(descontoFinal.toString().replace(".",  ","));
	}

	private BigDecimal getValorDesconto(TextField desconto) {
		String text = desconto.getText();
		String valor = text.replace(",", ".");
		BigDecimal bigDecimal = new BigDecimal(valor);
		return bigDecimal;
	}

	public void imprimir() {

	}

	public void exibirTelaItemPedidoCadastroInclusao() {
		telaPrincipalController.exibirTelaItemPedidoCadastro();
		itemPedidoCadastroController.getEntidadeForm().setPedido(entidadeForm);
	}

	public void exibirTelaCadastroItemPedidoCadastroAlteracao() {
		telaPrincipalController.exibirTelaItemPedidoCadastro();
	}

	public class InnerMouseClicked<T extends Entidade> implements EventHandler<MouseEvent> {
		
		private TableView<T> tabela;
		private BaseCadastroController<T> baseCadastroController;

		public InnerMouseClicked(TableView<T> tabela, BaseCadastroController<T> baseCadastroController) {
			this.tabela = tabela;
			this.baseCadastroController = baseCadastroController;
		}

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() > 1) {
				T selectedItem = this.tabela.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					baseCadastroController.setEntidadeForm(selectedItem);
					exibirTelaCadastroItemPedidoCadastroAlteracao();
				}
			}
		}
	}

	public void removerItemPedido() {
		
	}

	// TODO: Extrair essa classe
	private class MouseClickedSelect implements EventHandler<MouseEvent> {

		private TableView<Cliente> tabela;
		private Stage popup;

		public MouseClickedSelect(TableView<Cliente> tabela, Stage popup) {
			this.tabela = tabela;
			this.popup = popup;
		}

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() > 1) {
				Cliente selectedItem = tabela.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					entidadeForm.setCliente(selectedItem);
					cliente.setText(selectedItem.getFirma());
					popup.close();
				}
			}
		}
	}

	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
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

}
