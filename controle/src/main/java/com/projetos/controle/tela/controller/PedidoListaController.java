package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
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
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_negocio.service.base.PedidoService;

@SuppressWarnings("restriction")
@Controller
@Lazy
public class PedidoListaController extends BaseListController<Pedido> {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private PedidoCadastroController pedidoCadastroController;
	
	@Autowired
	private RecebimentoListaController recebimentoListaController;
	
	@FXML
	@FiltroTela(campo = "cliente.firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCliente;

	@FXML
	@FiltroTela(campo = "cliente.logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;

	@FXML
	@FiltroTela(campo = "id", tipo = TipoFiltro.INTEGER, comparador = Comparador.EQUALS)
	private TextField numeroPedido;

	@FXML
	@Coluna(bean = "id")
	private TableColumn<Produto, String> colunaNumeroPedido;
	
	@FXML
	@Coluna(bean = "dataPedido")
	private TableColumn<Produto, String> colunaData;
	
	@FXML
	@Coluna(bean = "cliente.firma")
	private TableColumn<Pedido, String> colunaCliente;
	
	@FXML
	@Coluna(bean = "fornecedor.firma")
	private TableColumn<Produto, String> colunaFornecedor;
	
	@FXML
	@Coluna(bean = "cliente.logradouro.cidade")
	private TableColumn<Produto, String> colunaCidade;
	
	@FXML
	@Coluna(bean = "transportador")
	private TableColumn<Produto, String> colunaTransportador;
	
	@FXML
	@Coluna(bean = "condicoes")
	private TableColumn<Produto, String> colunaCondicoes;
	
	@FXML
	@Coluna(bean = "cobranca")
	private TableColumn<Produto, String> colunaCobranca;

	@FXML
	private TableView<Pedido> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
	}

	@Override
	public void exibirTelaCadastro() {
		entidadeForm = new Pedido();
		telaPrincipalController.exibirTelaPedidoCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaPedidoLista();
	}
	
	/**
	 * abre a tela de movimentação direto da lista
	 */
	public void exibirTelaMovimentacao() {
		Pedido pedido = getTabela().getSelectionModel().getSelectedItem();
		if (pedido == null || pedido.getId() == null) {
			exibirMensagem("cadastro.selecione_um_item");
			return;
		}

		// essa consulta é necessária para carregar os itensPedido e recebimentos
		pedido = pedidoService.consultarPedido(pedido.getId());
		recebimentoListaController.setPedido(pedido);
		telaPrincipalController.exibirTelaRecebimentoLista();
	}

	@Override
	public void pesquisar() {
		try {
			atualizarFiltros();

			// Se algum filtro foi selecionado, usa o filtro
			if (filtros.size() > 0) {
				super.pesquisar();
				
			} else {
				// Senão faz a pesquisa normal
				pesquisarUltimosMeses();
				
			}

		} catch (NegocioException e) {
			tratarErro(e);
		}
	}

	private void pesquisarUltimosMeses() throws NegocioException {
		Calendar calendar = Calendar.getInstance();

		Parametro parametro;
		parametro = parametroService.getNumeroDeMesesConsultaPedido();
		Integer numeroDeMesesParaConsultar = new Integer(parametro.getValor());
		// altera o mês para 3 meses antes
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)
				- numeroDeMesesParaConsultar);

		Date data = calendar.getTime();

		Filtro filtro = new Filtro("dataPedido", TipoFiltro.DATE,
				Comparador.GREATER_OR_EQUALS, data);

		List<Pedido> pedidos = getEntidadeService().filtrar(filtro);
		preencherTabela(pedidos);
	}

	public void pesquisarTodos() {
		super.pesquisar();
	}

	@Override
	protected void preencherTabela(List<Pedido> listaTabela) {
		listaTabela.sort(new ComparadorPedido());
		super.preencherTabela(listaTabela);
	}
	
	/**
	 * Compara de modo a retornar a lista em ordem decrescente
	 */
	private class ComparadorPedido implements Comparator<Pedido> {

		@Override
		public int compare(Pedido o1, Pedido o2) {
			return o2.getId().compareTo(o1.getId());
		}

	}
	
	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
	}

	@Override
	public TableView<Pedido> getTabela() {
		return tabela;
	}

	public TableColumn<Produto, String> getColunaNumeroPedido() {
		return colunaNumeroPedido;
	}

	public void setColunaNumeroPedido(
			TableColumn<Produto, String> colunaNumeroPedido) {
		this.colunaNumeroPedido = colunaNumeroPedido;
	}

	public TextField getFiltroCidade() {
		return filtroCidade;
	}

	public void setFiltroCidade(TextField filtroCidade) {
		this.filtroCidade = filtroCidade;
	}

	public TextField getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(TextField filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public TableColumn<Produto, String> getColunaData() {
		return colunaData;
	}

	public void setColunaData(TableColumn<Produto, String> colunaData) {
		this.colunaData = colunaData;
	}

	public TableColumn<Pedido, String> getColunaCliente() {
		return colunaCliente;
	}

	public void setColunaCliente(TableColumn<Pedido, String> colunaCliente) {
		this.colunaCliente = colunaCliente;
	}

	public TableColumn<Produto, String> getColunaFornecedor() {
		return colunaFornecedor;
	}

	public void setColunaFornecedor(TableColumn<Produto, String> colunaFornecedor) {
		this.colunaFornecedor = colunaFornecedor;
	}

	public TableColumn<Produto, String> getColunaCidade() {
		return colunaCidade;
	}

	public void setColunaCidade(TableColumn<Produto, String> colunaCidade) {
		this.colunaCidade = colunaCidade;
	}

	public TableColumn<Produto, String> getColunaTransportador() {
		return colunaTransportador;
	}

	public void setColunaTransportador(
			TableColumn<Produto, String> colunaTransportador) {
		this.colunaTransportador = colunaTransportador;
	}

	public TableColumn<Produto, String> getColunaCondicoes() {
		return colunaCondicoes;
	}

	public void setColunaCondicoes(TableColumn<Produto, String> colunaCondicoes) {
		this.colunaCondicoes = colunaCondicoes;
	}

	public TableColumn<Produto, String> getColunaCobranca() {
		return colunaCobranca;
	}

	public void setColunaCobranca(TableColumn<Produto, String> colunaCobranca) {
		this.colunaCobranca = colunaCobranca;
	}

	public void setTabela(TableView<Pedido> tabela) {
		this.tabela = tabela;
	}

	@Override
	protected BaseCadastroController<Pedido> getBaseCadastroController() {
		return pedidoCadastroController;
	}

}
