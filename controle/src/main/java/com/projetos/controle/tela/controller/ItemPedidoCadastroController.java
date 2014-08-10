package com.projetos.controle.tela.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.TipoCampo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;
import com.projetos.controle_negocio.service.base.ProdutoService;
import com.projetos.controle_util.validacao.ValidacaoException;

@Controller
@Lazy
@Scope
public class ItemPedidoCadastroController extends BaseCadastroController<ItemPedido> {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private ProdutoService produtoService;

	@FXML
	@CampoTela(bean = "produto.referencia")
	private TextField referencia;

	@FXML
	@CampoTela(bean = "cor")
	private TextField cor;

	@FXML
	@CampoTela(bean = "produto.valorUnitario")
	private TextField valorUnitario;

	@FXML
	@CampoTela(bean = "valorTotal")
	private Label valorTotal;

	@FXML
	@CampoTela(bean = "produto.tamanho")
	private Label labelTamanho;

	@FXML
	@CampoTela(bean = "quantidadeTamanho1", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 1")
	private TextField quantidadeTamanho1;

	@FXML
	@CampoTela(bean = "quantidadeTamanho2", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 2")
	private TextField quantidadeTamanho2;

	@FXML
	@CampoTela(bean = "quantidadeTamanho3", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 3")
	private TextField quantidadeTamanho3;

	@FXML
	@CampoTela(bean = "quantidadeTamanho4", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 4")
	private TextField quantidadeTamanho4;

	@FXML
	@CampoTela(bean = "quantidadeTamanho5", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 5")
	private TextField quantidadeTamanho5;

	@FXML
	@CampoTela(bean = "quantidadeTamanho6", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 6")
	private TextField quantidadeTamanho6;

	@FXML
	@CampoTela(bean = "quantidadeTamanho7", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 7")
	private TextField quantidadeTamanho7;

	@FXML
	@CampoTela(bean = "quantidadeTamanho8", tipoCampo = TipoCampo.INTEIRO, nome = "Tamanho 8")
	private TextField quantidadeTamanho8;

	@FXML
	@CampoTela(bean = "produto.descricao")
	private TextArea descricao;

	@FXML
	@CampoTela(bean = "observacao")
	private TextArea observacao;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		this.referencia.focusedProperty().addListener(new ReferenciaChangeListener());

		List<TextField> camposQuantidade = Arrays.asList(quantidadeTamanho1, quantidadeTamanho2, quantidadeTamanho3, quantidadeTamanho4, quantidadeTamanho5, quantidadeTamanho6,
				quantidadeTamanho7, quantidadeTamanho8);

		for (TextField textFieldQuantidade : camposQuantidade) {
			textFieldQuantidade.focusedProperty().addListener(new FormBindingListener());
		}
	}

	@Override
	protected ItemPedido novaEntidadeForm() {
		ItemPedido novaEntidadeForm = super.novaEntidadeForm();
		novaEntidadeForm.setQuantidadeTamanho1(0);
		novaEntidadeForm.setQuantidadeTamanho2(0);
		novaEntidadeForm.setQuantidadeTamanho3(0);
		novaEntidadeForm.setQuantidadeTamanho4(0);
		novaEntidadeForm.setQuantidadeTamanho5(0);
		novaEntidadeForm.setQuantidadeTamanho6(0);
		novaEntidadeForm.setQuantidadeTamanho7(0);
		novaEntidadeForm.setQuantidadeTamanho8(0);
		return novaEntidadeForm;
	}
	
	public Produto carregarProduto(String referencia) {
		Produto produto = produtoService.getProdutoByReferencia(referencia);
		return produto;
	}

	public void exibirTelaProcurarProduto() {
		Parent tela = configuracaoBeanTela.carregarProdutoListaTelaPedido();
		Stage popup = telaPrincipalController.exibirPopup(tela);

		ProdutoListaTelaPedidoController controller = ApplicationConfig.getBean(ProdutoListaTelaPedidoController.class);
		MouseClickedSelect mouseClickedSelecPedido = new MouseClickedSelect(controller.getTabela(), popup);
		controller.getTabela().setOnMouseClicked(mouseClickedSelecPedido);
	}
	
	public class ReferenciaChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
			// Handling only when focus is out.
			String textoReferencia = referencia.getText();
			if (!focusIn && textoReferencia != null && !textoReferencia.equals("")) {
				Produto produto = carregarProduto(textoReferencia);
				
				if (produto == null) {
					// Se não encontrar o produto, exibe mensagem
					exibirMensagem("item_pedido.produto_nao_encontrado");
					limparCampos();
				} else if (!produto.getFornecedor().equals(entidadeForm.getPedido().getFornecedor())) {
					// Se o produto for de outro fornecedor, exibe a mensagem
					exibirMensagem("item_pedido.produto_de_outro_fornecedor");
					limparCampos();
					
				} else {
					// Se encontrou o produto, preenche os dados da tela
					entidadeForm.setProduto(produto);
				}
				calcularQuantidadeTotal();
				calcularValorTotal();
				bindBeanToForm();
			}
		}

	}

	private void limparCampos() {
		entidadeForm.setProduto(null);
		referencia.requestFocus();
		referencia.setText("");
	}

	public class MouseClickedSelect implements EventHandler<MouseEvent> {

		private TableView<Produto> tabela;
		private Stage popup;

		public MouseClickedSelect(TableView<Produto> tabela, Stage popup) {
			this.tabela = tabela;
			this.popup = popup;
		}

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() > 1) {
				Produto selectedItem = tabela.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					entidadeForm.setProduto(selectedItem);
					bindBeanToForm();
					popup.close();
				}
			}
		}
	}

	public class FormBindingListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
			// Handling only when focus is out.
			if (!focusIn) {

				try {
					bindFormToBean();
					calcularQuantidadeTotal();
					calcularValorTotal();
					bindBeanToForm();
				} catch (ValidacaoException e) {
					tratarErroValidacao(e);
				}
			}
		}

	}

	private void calcularValorTotal() {
		if (entidadeForm.getProduto() != null && entidadeForm.getQuantidadeTotal() != null) {
			BigDecimal valorUnitario = new BigDecimal(entidadeForm.getProduto().getValorUnitario());
			BigDecimal quantidade = new BigDecimal(entidadeForm.getQuantidadeTotal());
			BigDecimal valorTotal = valorUnitario.multiply(quantidade);
			valorTotal.setScale(2, RoundingMode.DOWN);
			entidadeForm.setValorTotal(valorTotal.doubleValue());

		}
	}

	@Override
	public void salvarComMensagem() {
		try {
			PedidoCadastroController pedidoCadastroController = ApplicationConfig.getBean(PedidoCadastroController.class);

			Pedido pedido = entidadeForm.getPedido();
			super.salvarSemMensagem();
			super.exibirMensagem("cadastro.salvo_com_sucesso");

			if (!pedido.getItensPedido().contains(entidadeForm)) {
				pedido.addItemPedido(entidadeForm);
			}
			pedidoCadastroController.getEntidadeService().salvar(entidadeForm.getPedido());
			pedidoCadastroController.atualizarValorPedido();

			entidadeForm = novaEntidadeForm();
			super.bindBeanToForm();
			entidadeForm.setPedido(pedido);

			pedidoCadastroController.atualizaValorRecebimento();
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	@Override
	protected void validaPersistencia() throws ValidacaoException {
		super.validaPersistencia();
		
		int quantidades = 0;
		quantidades += entidadeForm.getQuantidadeTamanho1();
		quantidades += entidadeForm.getQuantidadeTamanho2();
		quantidades += entidadeForm.getQuantidadeTamanho3();
		quantidades += entidadeForm.getQuantidadeTamanho4();
		quantidades += entidadeForm.getQuantidadeTamanho5();
		quantidades += entidadeForm.getQuantidadeTamanho6();
		quantidades += entidadeForm.getQuantidadeTamanho7();
		quantidades += entidadeForm.getQuantidadeTamanho8();
		
		if (quantidades == 0) {
			throw new ValidacaoException("item_pedido.preencha_uma_quantidade");
		}
	}

	private void calcularQuantidadeTotal() {
		int qTotal = 0;
		if (entidadeForm.getQuantidadeTamanho1() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho1();
		}
		if (entidadeForm.getQuantidadeTamanho2() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho2();
		}
		if (entidadeForm.getQuantidadeTamanho3() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho3();
		}
		if (entidadeForm.getQuantidadeTamanho4() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho4();
		}
		if (entidadeForm.getQuantidadeTamanho5() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho5();
		}
		if (entidadeForm.getQuantidadeTamanho6() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho6();
		}
		if (entidadeForm.getQuantidadeTamanho7() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho7();
		}
		if (entidadeForm.getQuantidadeTamanho8() != null) {
			qTotal += entidadeForm.getQuantidadeTamanho8();
		}
		entidadeForm.setQuantidadeTotal(qTotal);
	}

	@Override
	protected EntidadeService<ItemPedido> getEntidadeService() {
		return itemPedidoService;
	}

}
