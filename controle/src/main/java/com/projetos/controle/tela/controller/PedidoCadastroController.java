package com.projetos.controle.tela.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

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
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_negocio.service.base.VendedorService;

@Controller
@Lazy
public class PedidoCadastroController extends BaseCadastroController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private VendedorService vendedorService;
    
    @Autowired
    protected RecebimentoListaController recebimentoListaController;
    
	@Autowired
	private ItemPedidoCadastroController itemPedidoCadastroController;

	@FXML
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor")
	private ComboBox<ItemCombo<Fornecedor>> fornecedor;

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
	private ComboBox<ItemCombo<Vendedor>> vendedor;

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
	@CampoTela(bean = "valorTotal")
	private Label valorTotal;
	
	@FXML
	@CampoTela(bean = "valorSubTotal")
	private Label valorSubTotal;

	@FXML
	@Coluna(bean = "produto.referencia")
	private TableColumn<Produto, String> referencia;
	
	@FXML
	@Coluna(bean = "cor")
	private TableColumn<Produto, String> cor;
	
	@FXML
	@Coluna(bean = "quantidadeTotal")
	private TableColumn<Produto, String> quantidadeTotal;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho1")
	private TableColumn<Produto, String> quantidadeTamanho1;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho2")
	private TableColumn<Produto, String> quantidadeTamanho2;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho3")
	private TableColumn<Produto, String> quantidadeTamanho3;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho4")
	private TableColumn<Produto, String> quantidadeTamanho4;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho5")
	private TableColumn<Produto, String> quantidadeTamanho5;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho6")
	private TableColumn<Produto, String> quantidadeTamanho6;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho7")
	private TableColumn<Produto, String> quantidadeTamanho7;
	
	@FXML
	@Coluna(bean = "quantidadeTamanho8")
	private TableColumn<Produto, String> quantidadeTamanho8;

	@FXML
	@Coluna(bean = "produto.valorUnitario")
	private TableColumn<Produto, String> valorUnitario;
	
	@FXML
	@Coluna(bean = "valorTotal")
	private TableColumn<Produto, String> valorTotalItemPedido;

	@FXML
	@Coluna(bean = "descricao")
	private TableColumn<Produto, String> descricao;
	
	@FXML
	@Coluna(bean = "observacao")
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

		carregarTabelaItensPedido();
		
		InnerMouseClicked<ItemPedido> mouseClicked = new InnerMouseClicked<>(tabelaItensPedido, itemPedidoCadastroController);
		tabelaItensPedido.setOnMouseClicked(mouseClicked);
	}

	private void carregarTabelaItensPedido() {
		if (entidadeForm != null && entidadeForm.getItensPedido() != null) {
			ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList(entidadeForm.getItensPedido());
			tabelaItensPedido.setItems(itensPedido);
			loadColumns();
		}
	}

	public void exibirTelaRecebimentoLista() {
		salvarSemMensagem();
		recebimentoListaController.setEntidadeForm(new Recebimento());
		recebimentoListaController.getEntidadeForm().setPedido(entidadeForm);
		telaPrincipalController.exibirTelaRecebimentoLista();
	}

	public void exibirTelaCliente() {
		Parent telaClienteLista = configuracaoBeanTela.carregarTelaClienteLista();
		Stage popup = telaPrincipalController.exibirPopup(telaClienteLista);
		ClienteListaController controller = ApplicationConfig.getBean(ClienteListaController.class);
		MouseClickedSelect mouseClickedSelecPedido = new MouseClickedSelect(controller.getTabela(), popup);
		controller.getTabela().setOnMouseClicked(mouseClickedSelecPedido);
	}

	public void calcularDesconto() {
		bindFormToBean();
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
		entidadeForm.setDescontoTotal(descontoFinal.doubleValue());
		atualizarValorPedido();
		getEntidadeService().salvar(entidadeForm);
	}

	private BigDecimal getValorDesconto(TextField desconto) {
		String text = desconto.getText();
		String valor = text.replace(",", ".");
		BigDecimal bigDecimal = new BigDecimal(valor);
		return bigDecimal;
	}

	public void imprimir() {
		bindFormToBean();
		try {
			Map<String, Object> param = new HashMap<>();
			List<String> dados = new ArrayList<>();
//			byte[] relatorio = ReportGenerator.gerarRelatorio(dados, "relatorioPedido.jasper", FormatoRelatorio.FORMATO_EXPORT_PDF, param);
			
			InputStream resource = getClass().getResourceAsStream("/report/relatorioPedido.jasper");

//			String file = "D:\\Desenvolvimento\\projetos\\controle\\branches\\controle\\controle\\src\\main\\resources\\report\\relatorioPedido.jasper";
			JasperPrint print = JasperFillManager.fillReport(resource, param);

			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			String path = "C:\\Users\\Rafael\\Desktop\\pedido.pdf";
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path);
			System.out.println("Exporting report...");
			pdfExporter.exportReport();

//			ByteArrayInputStream bis = new ByteArrayInputStream(relatorio);
//			JasperViewer.viewReport(print);
			
		/*} catch (RelatorioException e) {
			tratarErro(e);*/
		} catch (JRException e) {
			tratarErro(e);
		}
	}

	public void exibirTelaItemPedidoCadastroInclusao() {
		if (entidadeForm.getId() == null) {
			exibirMensagem("pedido.salve_antes_de_incluir_produtos");
			return;
		}
		itemPedidoCadastroController.setEntidadeForm(null);
		itemPedidoCadastroController.setTabela(tabelaItensPedido);
		telaPrincipalController.exibirTelaItemPedidoCadastro();
		itemPedidoCadastroController.getEntidadeForm().setPedido(entidadeForm);
	}

	public void exibirTelaCadastroItemPedidoCadastroAlteracao() {
		itemPedidoCadastroController.setTabela(tabelaItensPedido);
		telaPrincipalController.exibirTelaItemPedidoCadastro();
	}

	@Override
	public void remover() {
		List<ItemPedido> itensPedido = entidadeForm.getItensPedido();
		
		for (ItemPedido itemPedido : itensPedido) {
			itemPedidoService.remover(itemPedido);
		}
		
		List<Recebimento> recebimentos = entidadeForm.getRecebimentos();
		
		for (Recebimento recebimento : recebimentos) {
			recebimentoService.remover(recebimento);
		}

		super.remover();
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

	public void exibirPopupConfirmacaoItemPedido() {
		exibirPopupConfirmacao(new RemoverPedidoConfirmmHandler());
	}

	public class RemoverPedidoConfirmmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			removerItemPedido();
			fecharPopupConfirmacao();
		}
	}

	public void removerItemPedido() {
		ItemPedido itemPedido = tabelaItensPedido.getSelectionModel().getSelectedItem();
		entidadeForm.removeItemPedido(itemPedido);
		itemPedidoService.salvar(itemPedido);
		itemPedidoService.remover(itemPedido);
		tabelaItensPedido.getItems().remove(itemPedido);
		entidadeForm = getEntidadeService().findById(entidadeForm.getId());
		getEntidadeService().salvar(entidadeForm);
		exibirMensagem("cadastro.removido_com_sucesso");
	}

	public void atualizarValorPedido() {
		BigDecimal subTotal = BigDecimal.ZERO;
		for (ItemPedido itemPedido : tabelaItensPedido.getItems()) {
			BigDecimal valorItem = new BigDecimal(itemPedido.getValorTotal());
			subTotal = subTotal.add(valorItem);
		}
		subTotal = subTotal.setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorSubTotal(subTotal.doubleValue());
		
		BigDecimal desconto = new BigDecimal(entidadeForm.getDescontoTotal() / 100).multiply(subTotal);
		BigDecimal valorTotal = subTotal.subtract(desconto);
		
		valorTotal = valorTotal.setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorTotal(valorTotal.doubleValue());
		
		BigDecimal porcentagemComissao = new BigDecimal(entidadeForm.getComissao() / 100);
		BigDecimal valorComissionado = valorTotal.multiply(porcentagemComissao).setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorComissionado(valorComissionado.doubleValue());
		bindBeanToForm();
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

	public ComboBox<ItemCombo<Fornecedor>> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ComboBox<ItemCombo<Fornecedor>> fornecedor) {
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

	public ComboBox<ItemCombo<Vendedor>> getVendedor() {
		return vendedor;
	}

	public void setVendedor(ComboBox<ItemCombo<Vendedor>> vendedor) {
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
