package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseEntityController;
import com.projetos.controle.tela.util.FiltroUtil;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.RecebimentoService;

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
	
	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Fornecedor, String> colunaFirma;

	@FXML
	@FiltroTela(campo = "colecao", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private DatePicker dataApartir;
	

	@FXML
	@FiltroTela(campo = "colecao", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroColecao;

	@FXML
	@FiltroTela(campo = "filtroRecebido", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ComboBox<ItemCombo<Boolean>> filtroRecebido;

	@FXML
	private TableView<Fornecedor> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		carregarTabelaFornecedores();
		initFiltroAtivo();
	}

	private void carregarTabelaFornecedores() {
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

	public void gerarRelatorio() {
		pesquisar();
	}

	private void pesquisar() {
		List<Filtro> filtros = new ArrayList<>();
		recebimentoService.filtrar(filtros);
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