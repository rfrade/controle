package com.projetos.controle.tela.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class RelatorioRecebimentoController extends BaseEntityController<Recebimento> {

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private ParametroService parametroService;

	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Fornecedor, String> colunaFirma;

	@FXML
	@FiltroTela(campo = "dataRecebimento", tipo = TipoFiltro.DATE, comparador = Comparador.GREATHER_OR_EQUALS)
	private DatePicker dataApartir;

	@FXML
	@FiltroTela(campo = "dataRecebimento", tipo = TipoFiltro.DATE, comparador = Comparador.LOWER_OR_EQUALS)
	private DatePicker dataAte;

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
				if (!pedidos.contains(recebimento.getPedido())) {
					pedidos.add(recebimento.getPedido());
				}
			}

			if (dataApartir.getValue() != null) {
				Date dateApartir = fromLocalDateToDate(dataApartir.getValue());
				RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.DATA_APARTIR, DateUtil.convertDateToString(dateApartir));

			}

			if (dataAte.getValue() != null) {
				Date dateAte = fromLocalDateToDate(dataAte.getValue());
				RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.DATA_ATE, DateUtil.convertDateToString(dateAte));

			}

			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.VALOR_COMISSAO, this.getValorComissaoTotal(pedidos));
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.VALOR_PEDIDO, this.getValorTotalPedidos(pedidos));
			RelatorioUtil.preencherParametro(param, RelatorioRecebimentoParam.OBSERVACAO, observacao.getText());

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

	private Double getValorComissaoTotal(List<Pedido> pedidos) {
		BigDecimal valor = BigDecimal.ZERO;

		for (Pedido pedido : pedidos) {
			
			for (Recebimento recebimento : pedido.getRecebimentos()) {
				String valueOf = String.valueOf(recebimento.getValorRecebimento());
				valor = valor.add(new BigDecimal(valueOf));
				
			}
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