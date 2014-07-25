package com.projetos.controle.tela.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_negocio.service.base.ProdutoService;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ProdutoListaController extends BaseListController<Produto> {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private ProdutoCadastroController produtoCadastroController;

	@Autowired
	private PopupTextoController popupTextoController;
	
	@Autowired
	private ParametroService parametroService;

	@FXML
	@FiltroTela(campo = "referencia", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroReferencia;
	
	@FXML
	@FiltroTela(campo = "descricao", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroDescricao;
	
	@FXML
	@FiltroTela(campo = "fornecedor", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ComboBox<ItemCombo<Fornecedor>> filtroFornecedor;

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
		
		ObservableList<ItemCombo<Fornecedor>> itens = FXCollections.observableArrayList(lista);
		filtroFornecedor.setItems(itens);
	}

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaProdutoCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}

	public void importar() {
		try {
			Parametro parametroCaminho = parametroService.getCaminhoImportacaoProdutos();
			validarImportacao(parametroCaminho);

			File file = new File(parametroCaminho.getValor());
			Fornecedor fornecedor = filtroFornecedor.getSelectionModel().getSelectedItem().getValor();
			String resultadoImportacao = produtoService.importarProdutosPlanilha(file, fornecedor);
			popupTextoController.setTitulo("Importação finalizada.");
			popupTextoController.setMensagem(resultadoImportacao);
			telaPrincipalController.exibirPopupTexto();
		} catch (NegocioException e) {
			tratarErro(e);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	public void exibirPopupConfirmacaoRemoverTodos() {
		exibirPopupConfirmacao(new RemoverTodosConfirmmHandler());
	}

	/**
	 * Handler chamado pelo botão de confirmação da exclusão
	 * @author Rafael
	 */
	public class RemoverTodosConfirmmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			removerTodos();
			fecharPopupConfirmacao();
		}
	}

	@Override
	protected List<Filtro> getFiltrosFixos() {
		List<Filtro> filtros = new ArrayList<>();

		Filtro filtroAtivo = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		filtros.add(filtroAtivo);

		return filtros;
	}

	private void removerTodos() {
		try {
			validarExclusao();
			Fornecedor fornecedor = filtroFornecedor.getSelectionModel().getSelectedItem().getValor();
			produtoService.removerTodos(fornecedor);
			exibirMensagem("cadastro.removido_com_sucesso");

			pesquisar();

		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	private void validarExclusao() throws ValidacaoException {
		if (filtroFornecedor.getSelectionModel().getSelectedItem() == null) {
			throw new ValidacaoException("produto.caminho_nao_cadastrado");
		}
	}

	private void validarImportacao(Parametro parametroCaminho) throws ValidacaoException {
		if (parametroCaminho == null) {
			throw new ValidacaoException("produto.selecione_o_fornecedor");
			
		}

		File file = new File(parametroCaminho.getValor());

		if (filtroFornecedor.getSelectionModel().getSelectedItem() == null) {
			throw new ValidacaoException("produto.selecione_o_fornecedor");
		
		} else if (!file.exists()) {
			throw new ValidacaoException("produto.arquivo_nao_econtrado");
		}
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
		return produtoCadastroController;
	}

}