package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
import com.projetos.controle.tela.util.FiltroUtil;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class FornecedorListaController extends BaseListController<Fornecedor> {

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private FornecedorCadastroController fornecedorCadastroController;
	
	@FXML
	@FiltroTela(campo = "firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroFirma;
	
	@FXML
	@FiltroTela(campo = "logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;
	
	@FXML
	@FiltroTela(campo = "ativo", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ChoiceBox<ItemCombo<Boolean>> filtroAtivo;

	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Fornecedor, String> colunaFirma;
	
	@FXML
	@Coluna(bean = "logradouro.endereco")
	private TableColumn<Fornecedor, String> colunaEndereco;
	
	@FXML
	@Coluna(bean = "logradouro.numero")
	private TableColumn<Fornecedor, Integer> colunaNumero;
	
	@FXML
	@Coluna(bean = "logradouro.bairro")
	private TableColumn<Fornecedor, String> colunaBairro;
	
	@FXML
	@Coluna(bean = "logradouro.cep")
	private TableColumn<Fornecedor, String> colunaCep;


	@FXML
	private TableView<Fornecedor> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		initFiltroAtivo();
	}

	private void initFiltroAtivo() {
		ObservableList<ItemCombo<Boolean>> listaFiltro = FiltroUtil.criarListaFiltroAtivos();
		filtroAtivo.setItems(listaFiltro);
	}

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaFornecedorCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaFornecedorLista();
	}

	public void imprimir() {

	}

	@Override
	protected EntidadeService<Fornecedor> getEntidadeService() {
		return fornecedorService;
	}


	public TableColumn<Fornecedor, String> getColunaFirma() {
		return colunaFirma;
	}

	public void setColunaFirma(TableColumn<Fornecedor, String> colunaFirma) {
		this.colunaFirma = colunaFirma;
	}

	public TableColumn<Fornecedor, String> getColunaEndereco() {
		return colunaEndereco;
	}

	public void setColunaEndereco(TableColumn<Fornecedor, String> colunaEndereco) {
		this.colunaEndereco = colunaEndereco;
	}

	public TableColumn<Fornecedor, Integer> getColunaNumero() {
		return colunaNumero;
	}

	public void setColunaNumero(TableColumn<Fornecedor, Integer> colunaNumero) {
		this.colunaNumero = colunaNumero;
	}

	public TableColumn<Fornecedor, String> getColunaBairro() {
		return colunaBairro;
	}

	public void setColunaBairro(TableColumn<Fornecedor, String> colunaBairro) {
		this.colunaBairro = colunaBairro;
	}

	public TableColumn<Fornecedor, String> getColunaCep() {
		return colunaCep;
	}

	public void setColunaCep(TableColumn<Fornecedor, String> colunaCep) {
		this.colunaCep = colunaCep;
	}

	@Override
	public TableView<Fornecedor> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Fornecedor> getBaseCadastroController() {
		return fornecedorCadastroController;
	}

}