package com.projetos.controle.tela.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
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
	@CampoTela(bean = "quantidadeTamanho1")
	private TextField quantidadeTamanho1;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho2")
	private TextField quantidadeTamanho2;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho3")
	private TextField quantidadeTamanho3;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho4")
	private TextField quantidadeTamanho4;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho5")
	private TextField quantidadeTamanho5;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho6")
	private TextField quantidadeTamanho6;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho7")
	private TextField quantidadeTamanho7;
	
	@FXML
	@CampoTela(bean = "quantidadeTamanho8")
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
		
		List<TextField> camposQuantidade = Arrays.asList(quantidadeTamanho1, quantidadeTamanho2, quantidadeTamanho3, quantidadeTamanho4,
				quantidadeTamanho5, quantidadeTamanho6, quantidadeTamanho7, quantidadeTamanho8);
		
		for (TextField textFieldQuantidade : camposQuantidade) {
			textFieldQuantidade.focusedProperty().addListener(new FormBindingListener());
		}
		
	}

	/*@FormBinding*/
	public void carregarProduto() {
		String referencia = this.referencia.getText();
		Produto produto = produtoService.getProdutoByReferencia(referencia);
		entidadeForm.setProduto(produto);
		bindBeanToForm();
	}

	public class ReferenciaChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
		// Handling only when focus is out.
          if(!focusIn){
        	  carregarProduto();
          }
		}
		
	}
	
	public class FormBindingListener implements ChangeListener<Boolean> {
		
		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
			// Handling only when focus is out.
			if(!focusIn){
				bindFormToBean();
				calcularQuantidadeTotal();
				calcularValorTotal();
				bindBeanToForm();
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

		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
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
