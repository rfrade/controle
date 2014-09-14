package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ProdutoService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ProdutoListaTelaPedidoController extends BaseListController<Produto> {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private PopupTextoController popupTextoController;
	
	@FXML
	@FiltroTela(campo = "referencia", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroReferencia;
	
	@FXML
	@FiltroTela(campo = "descricao", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroDescricao;

	@FXML
	@Coluna(bean = "referencia")
	private TableColumn<Produto, String> colunaReferencia;
	
	@FXML
	@Coluna(bean = "descricao")
	private TableColumn<Produto, String> colunaDescricao;
	
	@FXML
	@Coluna(bean = "valorUnitario")
	private TableColumn<Produto, Double> colunaValorVenda;
	
	@FXML
	@Coluna(bean = "tamanho")
	private TableColumn<Produto, String> colunaTamanho;
	
	@FXML
	private TableView<Produto> tabela;

	private Fornecedor fornecedor;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		initFiltroAtivo();
	}

	private void initFiltroAtivo() {
		List<Fornecedor> fornecedores = fornecedorService.listar();

		List<ItemCombo<Fornecedor>> lista = new ArrayList<>();
		for (Fornecedor fornecedor : fornecedores) {
			ItemCombo<Fornecedor> item = new ItemCombo<Fornecedor>(fornecedor.getFirma(), fornecedor);
			lista.add(item);
		}

	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirProdutoListaTelaPedido();
	}

	@Override
	protected List<Filtro> getFiltrosFixos() {
		List<Filtro> filtros = new ArrayList<>();

		Filtro filtroAtivo = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		Filtro filtroFornecedor = new Filtro("fornecedor", TipoFiltro.OBJECT, Comparador.EQUALS, fornecedor);

		filtros.add(filtroAtivo);
		filtros.add(filtroFornecedor);

		return filtros;
	}

	@Override
	protected EntidadeService<Produto> getEntidadeService() {
		return produtoService;
	}

	@Override
	public TableView<Produto> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Produto> getBaseCadastroController() {
		return null;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}