package com.projetos.controle.tela.controller;

import java.net.URL;
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
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.FormBinding;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;
import com.projetos.controle_negocio.service.base.ProdutoService;

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
	/*@CampoTela(bean = "produto.referencia")*/
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
		this.referencia.focusedProperty().addListener(new TextFieldChangeListener());
	}

	/*@FormBinding*/
	public void carregarProduto() {
		String referencia = this.referencia.getText();
		Produto produto = produtoService.getProdutoByReferencia(referencia);
		entidadeForm.setProduto(produto);
		bindBeanToForm();
	}
	
	public class TextFieldChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
		// Handling only when focus is out.
          if(!focusIn){
        	  carregarProduto();
          }
		}
		
	}

	@Override
	protected EntidadeService<ItemPedido> getEntidadeService() {
		return itemPedidoService;
	}

}
