package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.RecebimentoService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class RecebimentoListaController extends BaseListController<Recebimento> {

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private RecebimentoCadastroController recebimentoCadastroController;
	
	@FXML
	@Coluna(bean = "pedido.id")
	private TableColumn<Recebimento, String> colunaPedido;
	
	@FXML
	@Coluna(bean = "pedido.vendedor.nome")
	private TableColumn<Recebimento, String> colunaVendedor;
	
	@FXML
	@Coluna(bean = "pedido.fornecedor.firma")
	private TableColumn<Recebimento, String> colunaFornecedor;
	
	@FXML
	@Coluna(bean = "valorRecebimento")
	private TableColumn<Recebimento, String> colunaValorRecebimento;
	
	@FXML
	@Coluna(bean = "recebido")
	private TableColumn<Recebimento, String> colunaRecebido;
	
	@FXML
	@Coluna(bean = "dataRecebimento")
	private TableColumn<Recebimento, Date> colunaDataRecebimento;
	
	@FXML
	private TableView<Recebimento> tabela;

	@Override
	protected List<Filtro> getCamposFiltro() {
		Filtro filtro = new Filtro("pedido.id", TipoFiltro.INTEGER, Comparador.EQUALS);
		return Arrays.asList(filtro);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Filtro filtroPedido = new Filtro("pedido.id", TipoFiltro.INTEGER, Comparador.EQUALS, entidadeForm.getPedido().getId());
		super.addFiltro(filtroPedido);
		super.initialize(url, resource);
		entidadeForm = new Recebimento();
	}

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaRecebimentoCadastro();
		super.exibirTelaCadastro();
		getBaseCadastroController().getEntidadeForm().setPedido(entidadeForm.getPedido());
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}

	public void imprimir() {

	}

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

	@Override
	public TableView<Recebimento> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Recebimento> getBaseCadastroController() {
		return recebimentoCadastroController;
	}

}