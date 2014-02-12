package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;

@Controller
@Lazy
public class ItemPedidoCadastroController extends BaseCadastroController<ItemPedido> {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@FXML
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
	}

	public void imprimir() {

	}

	@Override
	protected EntidadeService<ItemPedido> getEntidadeService() {
		return itemPedidoService;
	}

}
