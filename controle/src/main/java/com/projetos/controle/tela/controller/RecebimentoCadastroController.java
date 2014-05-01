package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.field.DecimalNumberField;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.RecebimentoService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class RecebimentoCadastroController extends BaseCadastroController<Recebimento> {

	@Autowired
	private RecebimentoService recebimentoService;

	@FXML
	@CampoTela(bean = "dataRecebimento")
	private TextField dataRecebimento;
	
	@FXML
	@CampoTela(bean = "pedido.valorTotal")
	private TextField valorFaturado;
	
	@FXML
	@CampoTela(bean = "pedido.comissao")
	private TextField porcentagem;
	
	@FXML
	@CampoTela(bean = "pedido.valorComissionado")
	private TextField valorComissao;
	
	@FXML
	@CampoTela(bean = "recebido")
	private ChoiceBox<ItemCombo<Boolean>> recebido;

	@FXML
	private TableView<Recebimento> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		List<ItemCombo<Boolean>> lista = new ArrayList<>();
		ItemCombo<Boolean> ativo = new ItemCombo<>("SIM", true);
		ItemCombo<Boolean> naoAtivo = new ItemCombo<>("NÃO", false);
		lista.add(ativo);
		lista.add(naoAtivo);
		
		ObservableList<ItemCombo<Boolean>> itens = FXCollections.observableArrayList(lista);
		recebido.setItems(itens);

	}

	@Override
	public void salvarComMensagem() {
		PedidoCadastroController pedidoCadastroController = ApplicationConfig.getBean(PedidoCadastroController.class);
		Pedido pedido = pedidoCadastroController.getEntidadeForm();
		entidadeForm.setPedido(pedido);
		super.salvarComMensagem();
	}
	

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

}