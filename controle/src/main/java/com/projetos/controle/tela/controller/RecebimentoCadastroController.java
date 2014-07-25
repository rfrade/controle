package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class RecebimentoCadastroController extends BaseCadastroController<Recebimento> {

	@Autowired
	private RecebimentoService recebimentoService;
	
	@Autowired
	private PedidoService pedidoService;

	@FXML
	@CampoTela(bean = "dataRecebimento")
	private DatePicker dataRecebimento;
	
	@FXML
	@CampoTela(bean = "pedido.valorTotal")
	private TextField valorFaturado;
	
	@FXML
	@CampoTela(bean = "pedido.comissao")
	private TextField porcentagem;

	@FXML
	@CampoTela(bean = "valorRecebimento")
	private TextField valorComissao;
	
	@FXML
	@CampoTela(bean = "recebido")
	private ComboBox<ItemCombo<Boolean>> recebido;

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

		if (entidadeForm.getDataRecebimento() == null) {
			entidadeForm.setDataRecebimento(new Date());
			
		}

		ObservableList<ItemCombo<Boolean>> itens = FXCollections.observableArrayList(lista);
		recebido.setItems(itens);
		bindBeanToForm();
	}

	@Override
	public void salvar() throws ValidacaoException {
		bindFormToBean();
		super.validaPersistencia();
		entidadeForm = getEntidadeService().salvar(entidadeForm);
	}
	

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

}