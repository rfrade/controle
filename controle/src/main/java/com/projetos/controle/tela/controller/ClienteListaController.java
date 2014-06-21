package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
import com.projetos.controle.tela.util.FiltroUtil;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.EntidadeService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ClienteListaController extends BaseListController<Cliente> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteCadastroController clienteCadastroController;
	
	@FXML
	@FiltroTela(campo = "firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroFirma;
	
	@FXML
	@FiltroTela(campo = "logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;
	
	@FXML
	@FiltroTela(campo = "ativo", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ComboBox<ItemCombo<Boolean>> filtroAtivo;

	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Cliente, String> colunaFirma;
	
	@FXML
	@Coluna(bean = "logradouro.endereco")
	private TableColumn<Cliente, String> colunaEndereco;
	
	@FXML
	@Coluna(bean = "logradouro.numero")
	private TableColumn<Cliente, Integer> colunaNumero;
	
	@FXML
	@Coluna(bean = "logradouro.bairro")
	private TableColumn<Cliente, String> colunaBairro;
	
	@FXML
	@Coluna(bean = "logradouro.cep")
	private TableColumn<Cliente, String> colunaCep;

	@FXML
	@Coluna(bean = "logradouro.cidade")
	private TableColumn<Cliente, String> colunaCidade;

	@FXML
	private TableView<Cliente> tabela;

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
		telaPrincipalController.exibirTelaClienteCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}

	public void imprimir() {

	}

	@Override
	protected EntidadeService<Cliente> getEntidadeService() {
		return clienteService;
	}


	public TableColumn<Cliente, String> getColunaFirma() {
		return colunaFirma;
	}

	public void setColunaFirma(TableColumn<Cliente, String> colunaFirma) {
		this.colunaFirma = colunaFirma;
	}

	public TableColumn<Cliente, String> getColunaEndereco() {
		return colunaEndereco;
	}

	public void setColunaEndereco(TableColumn<Cliente, String> colunaEndereco) {
		this.colunaEndereco = colunaEndereco;
	}

	public TableColumn<Cliente, Integer> getColunaNumero() {
		return colunaNumero;
	}

	public void setColunaNumero(TableColumn<Cliente, Integer> colunaNumero) {
		this.colunaNumero = colunaNumero;
	}

	public TableColumn<Cliente, String> getColunaBairro() {
		return colunaBairro;
	}

	public void setColunaBairro(TableColumn<Cliente, String> colunaBairro) {
		this.colunaBairro = colunaBairro;
	}

	public TableColumn<Cliente, String> getColunaCep() {
		return colunaCep;
	}

	public void setColunaCep(TableColumn<Cliente, String> colunaCep) {
		this.colunaCep = colunaCep;
	}

	@Override
	public TableView<Cliente> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Cliente> getBaseCadastroController() {
		return clienteCadastroController;
	}

}