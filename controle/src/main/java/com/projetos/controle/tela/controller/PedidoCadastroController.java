package com.projetos.controle.tela.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.TipoCampo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.report.JRDataSourceGenerico;
import com.projetos.controle.tela.report.RelatorioUtil;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ItemPedidoService;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_negocio.service.base.VendedorService;
import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.validacao.ValidacaoException;

@Controller
@Lazy
public class PedidoCadastroController extends BaseCadastroController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	protected RecebimentoListaController recebimentoListaController;

	@Autowired
	protected RecebimentoCadastroController recebimentoCadastroController;

	@Autowired
	private ItemPedidoCadastroController itemPedidoCadastroController;

	@FXML
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor")
	private ComboBox<ItemCombo<Fornecedor>> fornecedor;

	@FXML
	@CampoTela(bean = "cliente.firma")
	private Label cliente;

	@FXML
	@CampoTela(bean = "transportador")
	private TextField transportador;

	@FXML
	@CampoTela(bean = "condicoes")
	private TextField condicoes;

	@FXML
	@CampoTela(bean = "cobranca")
	private TextField cobranca;

	@FXML
	@CampoTela(bean = "comissao", tipoCampo = TipoCampo.MOEDA, nome = "Comissão")
	private PasswordField comissao;

	@FXML
	@CampoTela(bean = "entrega")
	private TextField entrega;

	@FXML
	@CampoTela(bean = "vendedor")
	private ComboBox<ItemCombo<Vendedor>> vendedor;

	@FXML
	@CampoTela(bean = "colecao")
	private TextField colecao;

	@FXML
	@CampoTela(bean = "observacao")
	private TextArea observacao;

	@FXML
	@CampoTela(bean = "desconto1", tipoCampo = TipoCampo.MOEDA, nome = "Desconto 1")
	private TextField desconto1;

	@FXML
	@CampoTela(bean = "desconto2", tipoCampo = TipoCampo.MOEDA, nome = "Desconto 2")
	private TextField desconto2;

	@FXML
	@CampoTela(bean = "desconto3", tipoCampo = TipoCampo.MOEDA, nome = "Desconto 3")
	private TextField desconto3;

	@FXML
	@CampoTela(bean = "desconto4", tipoCampo = TipoCampo.MOEDA, nome = "Desconto 4")
	private TextField desconto4;

	@FXML
	@CampoTela(bean = "descontoTotal", tipoCampo = TipoCampo.MOEDA, nome = "Desconto Total")
	private TextField descontoTotal;

	@FXML
	@CampoTela(bean = "valorTotal", tipoCampo = TipoCampo.MOEDA, nome = "Valor Total")
	private Label valorTotal;

	@FXML
	@CampoTela(bean = "valorSubTotal", tipoCampo = TipoCampo.MOEDA, nome = "Valor SubTotal")
	private Label valorSubTotal;

	@FXML
	@Coluna(bean = "produto.referencia")
	private TableColumn<Produto, String> referencia;

	@FXML
	@Coluna(bean = "cor")
	private TableColumn<Produto, String> cor;

	@FXML
	@Coluna(bean = "quantidadeTotal")
	private TableColumn<Produto, String> quantidadeTotal;

	@FXML
	@Coluna(bean = "quantidadeTamanho1")
	private TableColumn<Produto, String> quantidadeTamanho1;

	@FXML
	@Coluna(bean = "quantidadeTamanho2")
	private TableColumn<Produto, String> quantidadeTamanho2;

	@FXML
	@Coluna(bean = "quantidadeTamanho3")
	private TableColumn<Produto, String> quantidadeTamanho3;

	@FXML
	@Coluna(bean = "quantidadeTamanho4")
	private TableColumn<Produto, String> quantidadeTamanho4;

	@FXML
	@Coluna(bean = "quantidadeTamanho5")
	private TableColumn<Produto, String> quantidadeTamanho5;

	@FXML
	@Coluna(bean = "quantidadeTamanho6")
	private TableColumn<Produto, String> quantidadeTamanho6;

	@FXML
	@Coluna(bean = "quantidadeTamanho7")
	private TableColumn<Produto, String> quantidadeTamanho7;

	@FXML
	@Coluna(bean = "quantidadeTamanho8")
	private TableColumn<Produto, String> quantidadeTamanho8;

	@FXML
	@Coluna(bean = "produto.valorUnitario")
	private TableColumn<Produto, String> valorUnitario;

	@FXML
	@Coluna(bean = "valorTotal")
	private TableColumn<Produto, String> valorTotalItemPedido;

	@FXML
	@Coluna(bean = "descricao")
	private TableColumn<Produto, String> descricao;

	@FXML
	@Coluna(bean = "observacao")
	private TableColumn<Produto, String> observacaoItemPedido;

	@FXML
	private TableView<ItemPedido> tabelaItensPedido;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		if (entidadeForm.getItensPedido() == null) {
			// Inicialização para evitar: collection cascade="all-delete-orphan"
			// no longer referenced
			entidadeForm.setItensPedido(new ArrayList<ItemPedido>());
		}

		Filtro filtro = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		List<Fornecedor> fornecedores = fornecedorService.filtrar(filtro);
		ObservableList<ItemCombo<Fornecedor>> itensFornecedor = ItemCombo.novaListaCombo(fornecedores, "firma");
		fornecedor.setItems(itensFornecedor);

		ObservableList<ItemCombo<Vendedor>> itensVendedor = ItemCombo.novaListaCombo(vendedorService.listar(), "nome");
		vendedor.setItems(itensVendedor);

		carregarTabelaItensPedido();

		InnerMouseClicked<ItemPedido> mouseClicked = new InnerMouseClicked<>(tabelaItensPedido, itemPedidoCadastroController);
		tabelaItensPedido.setOnMouseClicked(mouseClicked);
	}

	@Override
	protected Pedido novaEntidadeForm() {
		Pedido novaEntidadeForm = super.novaEntidadeForm();
		novaEntidadeForm.setDataPedido(new Date());
		novaEntidadeForm.setComissao(10);
		return novaEntidadeForm;
	}

	private void carregarTabelaItensPedido() {
		if (entidadeForm != null && entidadeForm.getItensPedido() != null) {
			ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList(entidadeForm.getItensPedido());
			tabelaItensPedido.setItems(itensPedido);
			loadColumns();
		}
	}

	public void exibirTelaRecebimentoLista() {
		try {
			salvarSemMensagem();
			recebimentoListaController.setPedido(entidadeForm);
			telaPrincipalController.exibirTelaRecebimentoLista();

		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	@Override
	protected void salvar() throws ValidacaoException {
		// A inclusão do recebimento não pode ser feita antes de salvar o pedido
		// mas a comparação do id tem que ser feita antes
		boolean inclusao = entidadeForm.getId() == null;
		super.salvar();
		if (inclusao) {
			incluirRecebimento();
		}
	}

	/**
	 * Chamado pelo botão novo
	 */
	public void novo() {
		Pedido pedido = entidadeForm.copy();
		entidadeForm = pedido;

		try {
			salvarSemMensagem();
			/*for (ItemPedido item : entidadeForm.getItensPedido()) {
				itemPedidoService.salvar(item);
			}*/

			exibirMensagem("pedido.copiado_com_sucesso");
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	public void exibirTelaCliente() {
		Parent telaClienteLista = configuracaoBeanTela.carregarTelaClienteLista();
		Stage popup = telaPrincipalController.exibirPopup(telaClienteLista);
		ClienteListaController controller = ApplicationConfig.getBean(ClienteListaController.class);
		MouseClickedSelect mouseClickedSelecPedido = new MouseClickedSelect(controller.getTabela(), popup);
		controller.getTabela().setOnMouseClicked(mouseClickedSelecPedido);
	}

	public void calcularDesconto() {

		try {
			bindFormToBean();

			BigDecimal valor1 = getValorDesconto(desconto1);
			BigDecimal valor2 = getValorDesconto(desconto2);
			BigDecimal valor3 = getValorDesconto(desconto3);
			BigDecimal valor4 = getValorDesconto(desconto4);

			List<BigDecimal> descontos = Arrays.asList(valor1, valor2, valor3, valor4);

			BigDecimal descontoFinal = BigDecimal.ZERO;
			BigDecimal cem = new BigDecimal(100);
			for (BigDecimal valor : descontos) {
				if (valor != null && !valor.equals(BigDecimal.ZERO)) {
					BigDecimal valorAbatido = valor.divide(cem).multiply(descontoFinal);
					descontoFinal = descontoFinal.add(valor.subtract(valorAbatido));
				}
			}

			descontoFinal = descontoFinal.setScale(2, RoundingMode.DOWN);
			entidadeForm.setDescontoTotal(descontoFinal.doubleValue());
			atualizarValorPedido();
			getEntidadeService().salvar(entidadeForm);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}

	}

	private BigDecimal getValorDesconto(TextField desconto) {
		String text = desconto.getText();
		String valor = text.replace(",", ".");
		BigDecimal bigDecimal = new BigDecimal(valor);
		return bigDecimal;
	}

	class RelatorioPedidoParam {
		private static final String NUM_PEDIDO = "NUM_PEDIDO";
		private static final String DATA_PEDIDO = "DATA_PEDIDO";
		private static final String FIRMA_FORNECEDOR = "FIRMA_FORNECEDOR";
		private static final String LOGRADOURO_FORNECEDOR = "LOGRADOURO_FORNECEDOR";
		private static final String BAIRRO_FORNECEDOR = "BAIRRO_FORNECEDOR";
		private static final String CIDADE_FORNECEDOR = "CIDADE_FORNECEDOR";
		private static final String TELEFONE_FORNECEDOR = "TELEFONE_FORNECEDOR";
		private static final String TRANSPORTADOR = "TRANSPORTADOR";
		private static final String CONDICAO = "CONDICAO";
		private static final String COBRANCA = "COBRANCA";
		private static final String ENTREGA = "ENTREGA";
		private static final String QUANTIDADE_TOTAL = "QUANTIDADE_TOTAL";
		private static final String OBSERVACAO = "OBSERVACAO";
		private static final String SUBTOTAL = "SUBTOTAL";
		private static final String DESCONTO = "DESCONTO";
		private static final String TOTAL = "TOTAL";
		private static final String FIRMA_CLIENTE = "FIRMA_CLIENTE";
		private static final String LOGRADOURO_CLIENTE = "LOGRADOURO_CLIENTE";
		private static final String BAIRRO_CLIENTE = "BAIRRO_CLIENTE";
		private static final String CIDADE_CLIENTE = "CIDADE_CLIENTE";
		private static final String ESTADO_CLIENTE = "ESTADO_CLIENTE";
		private static final String FONE_CLIENTE = "FONE_CLIENTE";
		private static final String CEP_CLIENTE = "CEP_CLIENTE";
		private static final String CNPJ_CLIENTE = "CNPJ_CLIENTE";
		private static final String INS_EST_CLIENTE = "INS_EST_CLIENTE";
	}

	public void imprimir() {
		try {
			bindFormToBean();
			JasperPrint print = gerarRelatorio();
			JasperViewer.viewReport(print, false);

		} catch (JRException e) {
			tratarErro(e);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	public void salvarRelatorio() {

		try {
			bindFormToBean();
			JasperPrint print = gerarRelatorio();

//			String caminho = propertiesPathLoader.getProperty(PropertiesPathLoader.RELATORIO_PEDIDO_CLIENTE);
//			String caminho = propertiesPathLoader.getProperty(PropertiesPathLoader.RELATORIO_PEDIDO_CLIENTE);
			String nomeRelatorio = "/relatorio_pedidos" + getDataAgora() + ".pdf";

			JasperExportManager.exportReportToPdfFile(print, /*caminho +*/ nomeRelatorio);
			
			exibirMensagem("relatorio.salvo_na_pasta = Relatório salvo na pasta");

		} catch (JRException e) {
			tratarErro(e);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	private JasperPrint gerarRelatorio() throws JRException {
		if (entidadeForm.getItensPedido() == null) {
			exibirMensagem("relatorio.nao_ha_produtos_cadastrados");
			return null;
		}

		Map<String, Object> param = new HashMap<>();

		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.NUM_PEDIDO, entidadeForm.getId());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.DATA_PEDIDO, getDataPedido());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.FIRMA_FORNECEDOR, entidadeForm.getFornecedor().getFirma());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.LOGRADOURO_FORNECEDOR, entidadeForm.getFornecedor().getLogradouro().getEndereco());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.BAIRRO_FORNECEDOR, entidadeForm.getFornecedor().getLogradouro().getBairro());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.CIDADE_FORNECEDOR, entidadeForm.getFornecedor().getLogradouro().getCidade());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.TELEFONE_FORNECEDOR, entidadeForm.getFornecedor().getLogradouro().getTelefone());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.TRANSPORTADOR, entidadeForm.getTransportador());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.CONDICAO, entidadeForm.getCondicoes());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.COBRANCA, entidadeForm.getCobranca());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.ENTREGA, entidadeForm.getEntrega());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.QUANTIDADE_TOTAL, entidadeForm.getQuantidadeItens());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.OBSERVACAO, entidadeForm.getObservacao());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.SUBTOTAL, entidadeForm.getValorSubTotal());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.DESCONTO, entidadeForm.getDescontoTotal());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.TOTAL, entidadeForm.getValorTotal());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.FIRMA_CLIENTE, entidadeForm.getCliente().getFirma());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.LOGRADOURO_CLIENTE, entidadeForm.getCliente().getLogradouro().getEndereco());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.BAIRRO_CLIENTE, entidadeForm.getCliente().getLogradouro().getBairro());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.CIDADE_CLIENTE, entidadeForm.getCliente().getLogradouro().getCidade());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.ESTADO_CLIENTE, entidadeForm.getCliente().getLogradouro().getEstado());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.FONE_CLIENTE, entidadeForm.getCliente().getLogradouro().getTelefone());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.CEP_CLIENTE, entidadeForm.getCliente().getLogradouro().getCep());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.CNPJ_CLIENTE, entidadeForm.getCliente().getCnpj());
		RelatorioUtil.preencherParametro(param, RelatorioPedidoParam.INS_EST_CLIENTE, entidadeForm.getCliente().getInscricao());

		JRDataSourceGenerico<ItemPedido> dataSource = new JRDataSourceGenerico<>(entidadeForm.getItensPedido());

		InputStream resource = getClass().getResourceAsStream("/report/relatorioPedido.jasper");

		JasperPrint print = JasperFillManager.fillReport(resource, param, dataSource);
		return print;
	}

	private String getDataPedido() {
		if (entidadeForm.getDataPedido() != null) {
			return DateUtil.convertDateToString(entidadeForm.getDataPedido());
		} else {
			return "";
		}
	}

	public void exibirTelaItemPedidoCadastroInclusao() {
		try {
			salvarSemMensagem();
			itemPedidoCadastroController.setEntidadeForm(null);
			itemPedidoCadastroController.setTabela(tabelaItensPedido);
			telaPrincipalController.exibirTelaItemPedidoCadastro();
			itemPedidoCadastroController.getEntidadeForm().setPedido(entidadeForm);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	public void exibirTelaCadastroItemPedidoCadastroAlteracao() {
		try {
			salvarSemMensagem();
			itemPedidoCadastroController.setTabela(tabelaItensPedido);
			telaPrincipalController.exibirTelaItemPedidoCadastro();
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}

	}

	/**
	 * Chamado pelo botão salvar. Como os dados precisam continuar na tela e
	 * esse é um comportamento diferente do padrão do método salvarComMensagem,
	 * essa sobrescrita é necessária.
	 */
	@Override
	public void salvarComMensagem() {
		try {
			super.salvarSemMensagem();

			exibirMensagem("cadastro.salvo_com_sucesso");
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	private void incluirRecebimento() {
		Recebimento recebimento = new Recebimento();
		recebimento.setDataRecebimento(null);
		recebimento.setPedido(entidadeForm);
		recebimento.setRecebido(false);
		recebimento.setPercentualComissao(entidadeForm.getComissao());
		recebimento.setValorRecebimento(getValorComissao());

		recebimento = recebimentoService.salvar(recebimento);

		entidadeForm.addRecebimento(recebimento);
		entidadeForm = pedidoService.salvar(entidadeForm);
	}

	private double getValorComissao() {
		BigDecimal valorTotal = new BigDecimal(String.valueOf(entidadeForm.getValorTotal()));
		BigDecimal comissao =  new BigDecimal(String.valueOf(entidadeForm.getComissao()));
		BigDecimal cem = new BigDecimal(100);
		
		return valorTotal.multiply(comissao).divide(cem).doubleValue();
	}

	@Override
	public void remover() {
		List<ItemPedido> itensPedido = entidadeForm.getItensPedido();

		for (ItemPedido itemPedido : itensPedido) {
			itemPedidoService.remover(itemPedido);
		}

		/*List<Recebimento> recebimentos = entidadeForm.getRecebimentos();

		for (Recebimento recebimento : recebimentos) {
			recebimentoService.remover(recebimento);
		}*/

		super.remover();
	}

	public class InnerMouseClicked<T extends Entidade> implements EventHandler<MouseEvent> {

		private TableView<T> tabela;
		private BaseCadastroController<T> baseCadastroController;

		public InnerMouseClicked(TableView<T> tabela, BaseCadastroController<T> baseCadastroController) {
			this.tabela = tabela;
			this.baseCadastroController = baseCadastroController;
		}

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() > 1) {
				T selectedItem = this.tabela.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					baseCadastroController.setEntidadeForm(selectedItem);
					exibirTelaCadastroItemPedidoCadastroAlteracao();
				}
			}
		}
	}

	public void exibirPopupConfirmacaoItemPedido() {
		exibirPopupConfirmacao(new RemoverPedidoConfirmmHandler());
	}

	public class RemoverPedidoConfirmmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			removerItemPedido();
			fecharPopupConfirmacao();
		}
	}

	public void removerItemPedido() {
		ItemPedido itemPedido = tabelaItensPedido.getSelectionModel().getSelectedItem();
		entidadeForm.removeItemPedido(itemPedido);
		itemPedidoService.salvar(itemPedido);
		itemPedidoService.remover(itemPedido);
		tabelaItensPedido.getItems().remove(itemPedido);
		entidadeForm = getEntidadeService().salvar(entidadeForm);
		entidadeForm = getEntidadeService().findById(entidadeForm.getId());
		this.atualizarValorPedido();

		this.atualizaValorRecebimento();

		exibirMensagem("cadastro.removido_com_sucesso");
	}

	public void atualizaValorRecebimento() {
		/*// Chamar o método que atualiza o valor do recebimento aqui e na
		// inclusão de Item. o mesmo método
		Filtro filtroPedido = new Filtro("pedido", TipoFiltro.OBJECT, Comparador.EQUALS, entidadeForm);

		// procura o menor id para atualizar apenas o recebimento criado
		// automaticamente
		Ordenacao ordenacao = new Ordenacao("id", TipoOrdenacao.ASC);

		List<Filtro> filtros = new ArrayList<>();
		filtros.add(filtroPedido);

		List<Ordenacao> ordenacoes = new ArrayList<>();
		ordenacoes.add(ordenacao);

		List<Recebimento> recebimentos = recebimentoService.filtrar(filtros, ordenacoes);
		Recebimento recebimento = recebimentos.get(0);*/

		Recebimento recebimento = getPrimeiroRecebimento();
		recebimento.setValorRecebimento(getValorComissao());
		recebimentoService.salvar(recebimento);
	}
	
	private Recebimento getPrimeiroRecebimento() {
		int id = 0;
		Recebimento recebimento = null;
		for (Recebimento item : entidadeForm.getRecebimentos()) {
			if (id < item.getId()) {
				recebimento = item;
			}
		}
		return recebimento;
	}
	
	public void atualizarValorPedido() {
		BigDecimal subTotal = BigDecimal.ZERO;
		// for (ItemPedido itemPedido : tabelaItensPedido.getItems()) {
		for (ItemPedido itemPedido : entidadeForm.getItensPedido()) {
			BigDecimal valorItem = new BigDecimal(itemPedido.getValorTotal());
			subTotal = subTotal.add(valorItem);
		}
		subTotal = subTotal.setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorSubTotal(subTotal.doubleValue());

		BigDecimal descontoTotalBigDecimal = new BigDecimal(entidadeForm.getDescontoTotal());
		BigDecimal cem = new BigDecimal(100);
		
		BigDecimal desconto = descontoTotalBigDecimal.divide(cem).multiply(subTotal);
		BigDecimal valorTotal = subTotal.subtract(desconto);

		valorTotal = valorTotal.setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorTotal(valorTotal.doubleValue());

		/*BigDecimal porcentagemComissao = new BigDecimal(entidadeForm.getComissao() / 100);
		BigDecimal valorComissionado = valorTotal.multiply(porcentagemComissao).setScale(2, RoundingMode.DOWN);
		entidadeForm.setValorComissionado(valorComissionado.doubleValue());*/
		bindBeanToForm();
	}

	public class MouseClickedSelect implements EventHandler<MouseEvent> {

		private TableView<Cliente> tabela;
		private Stage popup;

		public MouseClickedSelect(TableView<Cliente> tabela, Stage popup) {
			this.tabela = tabela;
			this.popup = popup;
		}

		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() > 1) {
				Cliente selectedItem = tabela.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					entidadeForm.setCliente(selectedItem);
					cliente.setText(selectedItem.getFirma());
					popup.close();
				}
			}
		}
	}

	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
	}

	public Label getLabelNumeroPedido() {
		return labelNumeroPedido;
	}

	public void setLabelNumeroPedido(Label labelNumeroPedido) {
		this.labelNumeroPedido = labelNumeroPedido;
	}

	public ComboBox<ItemCombo<Fornecedor>> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ComboBox<ItemCombo<Fornecedor>> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Label getCliente() {
		return cliente;
	}

	public void setCliente(Label cliente) {
		this.cliente = cliente;
	}

	public TextField getTransportador() {
		return transportador;
	}

	public void setTransportador(TextField transportador) {
		this.transportador = transportador;
	}

	public TextField getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(TextField condicoes) {
		this.condicoes = condicoes;
	}

	public TextField getCobranca() {
		return cobranca;
	}

	public void setCobranca(TextField cobranca) {
		this.cobranca = cobranca;
	}

	public TextField getEntrega() {
		return entrega;
	}

	public void setEntrega(TextField entrega) {
		this.entrega = entrega;
	}

	public ComboBox<ItemCombo<Vendedor>> getVendedor() {
		return vendedor;
	}

	public void setVendedor(ComboBox<ItemCombo<Vendedor>> vendedor) {
		this.vendedor = vendedor;
	}

	public TextField getColecao() {
		return colecao;
	}

	public void setColecao(TextField colecao) {
		this.colecao = colecao;
	}

	public TextArea getObservacao() {
		return observacao;
	}

	public void setObservacao(TextArea observacao) {
		this.observacao = observacao;
	}

	public TextField getDesconto1() {
		return desconto1;
	}

	public void setDesconto1(TextField desconto1) {
		this.desconto1 = desconto1;
	}

	public TextField getDesconto2() {
		return desconto2;
	}

	public void setDesconto2(TextField desconto2) {
		this.desconto2 = desconto2;
	}

	public TextField getDesconto3() {
		return desconto3;
	}

	public void setDesconto3(TextField desconto3) {
		this.desconto3 = desconto3;
	}

	public TextField getDesconto4() {
		return desconto4;
	}

	public void setDesconto4(TextField desconto4) {
		this.desconto4 = desconto4;
	}

	public TextField getDescontoTotal() {
		return descontoTotal;
	}

	public void setDescontoTotal(TextField descontoTotal) {
		this.descontoTotal = descontoTotal;
	}

}
