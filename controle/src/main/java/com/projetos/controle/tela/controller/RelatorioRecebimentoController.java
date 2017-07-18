package com.projetos.controle.tela.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseEntityController;
import com.projetos.controle.tela.report.RelatorioRecebimentoDataSource;
import com.projetos.controle.tela.report.RelatorioUtil;
import com.projetos.controle.tela.util.FiltroUtil;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@SuppressWarnings("restriction")
@Controller
@Lazy
public class RelatorioRecebimentoController extends BaseEntityController<Recebimento> {

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private PedidoService pedidoService;
	
	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Fornecedor, String> colunaFirma;

	@FXML
	@FiltroTela(campo = "dataRecebimento", tipo = TipoFiltro.DATE, comparador = Comparador.GREATER_OR_EQUALS)
	private DatePicker dataRecebimentoApartir;

	@FXML
	@FiltroTela(campo = "dataRecebimento", tipo = TipoFiltro.DATE, comparador = Comparador.LOWER_OR_EQUALS)
	private DatePicker dataRecebimentoAte;
	
	@FXML
	@FiltroTela(campo = "pedido.dataPedido", tipo = TipoFiltro.DATE, comparador = Comparador.GREATER_OR_EQUALS)
	private DatePicker dataPedidoDe;
	
	@FXML
	@FiltroTela(campo = "pedido.dataPedido", tipo = TipoFiltro.DATE, comparador = Comparador.LOWER_OR_EQUALS)
	private DatePicker dataPedidoAte;

	@FXML
	@FiltroTela(campo = "pedido.colecao", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroColecao;

	@FXML
	@FiltroTela(campo = "recebido", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ComboBox<ItemCombo<Boolean>> filtroRecebido;

	@FXML
	@FiltroTela(campo = "pedido.fornecedor", tipo = TipoFiltro.LIST, comparador = Comparador.IN)
	private TableView<Fornecedor> tabela;

	@FXML
	private TextArea observacao;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		carregarTabelaFornecedores();
		initFiltroAtivo();
	}

	private void carregarTabelaFornecedores() {
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Filtro fornecedorAtivo = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		List<Fornecedor> fornecedoresAtivos = fornecedorService.filtrar(fornecedorAtivo);
		List<Fornecedor> listaTabela = FXCollections.observableArrayList(fornecedoresAtivos);
		tabela.getItems().setAll(listaTabela);
		loadColumns();
	}

	private void initFiltroAtivo() {
		Map<String, Boolean> mapaRecebidos = new HashMap<>();
		mapaRecebidos.put("SIM", Boolean.TRUE);
		mapaRecebidos.put("NÃO", Boolean.FALSE);
		mapaRecebidos.put("TODOS", null);
		filtroRecebido.setItems(FiltroUtil.criarListaFiltro(mapaRecebidos));
	}

	class RelatorioRecebimentoParam {
		private static final String DATA_APARTIR = "DATA_APARTIR";
		private static final String DATA_ATE = "DATA_ATE";
		private static final String VALOR_COMISSAO = "VALOR_COMISSAO";
		private static final String VALOR_PEDIDO = "VALOR_PEDIDO";
		private static final String OBSERVACAO = "OBSERVACAO";
		private static final String QTDD_ITENS = "QTDD_ITENS";
	}

	public void gerarRelatorio() {

		try {
			bindFormToBean();
			List<Filtro> camposFiltro = super.getCamposFiltro();
			List<Recebimento> recebimentos = recebimentoService.filtrar(camposFiltro);

			if (recebimentos == null) {
				exibirMensagem("relatorio.nao_ha_recebimentos_no_periodo");
				return;
			}

			Map<String, Object> param = new HashMap<>();

			List<Pedido> pedidos = new ArrayList<>();
			for (Recebimento recebimento : recebimentos) {
				Pedido pedido = recebimento.getPedido();
				if (!pedidos.contains(pedido) && pedido != null) {
					Pedido pedidoConsultado = pedidoService.consultarPedido(pedido.getId());
					recebimento.setPedido(pedidoConsultado);
					pedidos.add(pedidoConsultado);
				} else {
					// Se o pedido já está na lista, tem que preencher o recebimento com
					// o pedido do recebimento que já está na lista, porque esse pedido
					// já vai ter tido os itensPedido consultados pela pedidoService.consultarPedido
					Pedido pedidoConsultado = this.getPedidoRecebimento(recebimentos, pedido);
					recebimento.setPedido(pedidoConsultado);
				}
			}

			if (dataRecebimentoApartir.getValue() != null) {
				Date dateApartir = fromLocalDateToDate(dataRecebimentoApartir.getValue());
				RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.DATA_APARTIR, DateUtil.convertDateToString(dateApartir));

			}

			if (dataRecebimentoAte.getValue() != null) {
				Date dateAte = fromLocalDateToDate(dataRecebimentoAte.getValue());
				RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.DATA_ATE, DateUtil.convertDateToString(dateAte));

			}
			
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.VALOR_COMISSAO, this.getValorComissaoTotal(recebimentos));
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.VALOR_PEDIDO, this.getValorTotalPedidos(pedidos));
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.OBSERVACAO, observacao.getText());
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.QTDD_ITENS, this.getQuantidadeItensPedido(pedidos));

			recebimentos.sort(new RelatorioRecebimentoComparator());
			RelatorioRecebimentoDataSource dataSource = new RelatorioRecebimentoDataSource(recebimentos);

			InputStream resource = getClass().getResourceAsStream("/report/relatorioRecebimento.jasper");
			JasperPrint print = JasperFillManager.fillReport(resource, param, dataSource);

			JasperViewer.viewReport(print, false);

			/*String nomeRelatorio = "/relatorio_recebimentos" + getDataAgora() + ".pdf";
			Parametro parametro = parametroService.getCaminhoRelatorioRecebimentos();

			String path = parametro.getValor() + nomeRelatorio;
			JasperExportManager.exportReportToPdfFile(print, path);*/

		} catch (JRException e) {
			tratarErro(e);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}

	}

	/**
	 * Esse método só deverá ser utilizado para pedidos com mais de um recebimento
	 * 
	 * @param recebimentos
	 * @param pedido
	 * @return pedido já adicionado na lista de recebimentos com os itensPEdido já consultados
	 */
	private Pedido getPedidoRecebimento(List<Recebimento> recebimentos, Pedido pedido) {
		for (Recebimento recebimento : recebimentos) {
			if (recebimento.getPedido().equals(pedido)) {
				return recebimento.getPedido();
			}
		}

		return null;
	}

	class RelatorioRecebimentoComparator implements Comparator<Recebimento> {

		public int compare(Recebimento o1, Recebimento o2) {
			String firma1 = o1.getPedido().getFornecedor().getFirma();
			String firma2 = o2.getPedido().getFornecedor().getFirma();

			// Se for do mesmo fornecedor ordena pela data
			if (firma1.compareToIgnoreCase(firma2) == 0) {
				return o1.getPedido().getDataPedido().compareTo(o2.getPedido().getDataPedido());
			}

			return firma1.compareToIgnoreCase(firma2);
		}
		
	}

	private Double getValorComissaoTotal(List<Recebimento> recebimentos) {
		BigDecimal valor = BigDecimal.ZERO;

		for (Recebimento recebimento : recebimentos) {
			String valueOf = String.valueOf(recebimento.getValorRecebimento());
			valor = valor.add(new BigDecimal(valueOf));

		}
		return valor.doubleValue();
	}

	private Double getValorTotalPedidos(List<Pedido> pedidos) {
		BigDecimal valor = BigDecimal.ZERO;

		for (Pedido pedido : pedidos) {
			String valueOf = String.valueOf(pedido.getValorTotal());
			valor = valor.add(new BigDecimal(valueOf));
		}

		return valor.doubleValue();
	}

	private String getQuantidadeItensPedido(List<Pedido> pedidos) {
		int qtdd = 0;
		for (Pedido pedido : pedidos) {
			//pedido = pedidoService.consultarPedido(pedido.getId());
			for (ItemPedido item : pedido.getItensPedido()) {
				qtdd += item.getQuantidadeTotal();
			}
		}
		return String.valueOf(qtdd);
	}
	
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

	@Override
	public void remover() {
		throw new RuntimeException("Esse método não deve ser chamado");
	}

}