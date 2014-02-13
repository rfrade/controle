package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;

@Deprecated
@Controller
@Lazy
public class ItemPedidoListaController extends BaseListController<ItemPedido> {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private ItemPedidoCadastroController itemPedidoCadastroController;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
	}

	@Override
	public void exibirTelaCadastro() {
		entidadeForm = new ItemPedido();
		telaPrincipalController.exibirTelaItemPedidoCadastro();
	}

	@Override
	public void exibirTelaLista() {
	}

	@Override
	protected EntidadeService<ItemPedido> getEntidadeService() {
		return itemPedidoService;
	}

	/*@Override
	public TableView<ItemPedido> getTabela() {
		return null;
	}

	public void setTabela(TableView<ItemPedido> tabela) {
		this.tabela = tabela;
	}*/

	@Override
	protected BaseCadastroController<ItemPedido> getBaseCadastroController() {
		return itemPedidoCadastroController;
	}

	@Override
	public TableView<ItemPedido> getTabela() {
		// TODO Auto-generated method stub
		return null;
	}

}
