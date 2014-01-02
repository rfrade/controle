package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.GerenciadorTela;
import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_entities.Logradouro;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.EntidadeService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ClienteController extends BaseController<Cliente> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private GerenciadorTela gerenciadorTela;

	@FXML
	private TableView<Cliente> clienteTable;
	
	@FXML
	private TextField firma;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private TextField numero;
	
	@FXML
	private TextField bairro;
	
	@FXML
	private TextField cep;
	
	@FXML
	private TextField estado;
	
	@FXML
	private TextField cidade;
	
	@FXML
	private TextField ddd;
	
	@FXML
	private TextField telefone;
	
	@FXML
	private TextField cnpj;
	
	@FXML
	private TextField inscricaoEstadual;
	
	@FXML
	private TextField comprador;
	
	@FXML
	private TextField email;
	
	@FXML
	private RadioButton ativo;
	
	@FXML
	private Label mensagem;

	private ObservableList<Cliente> listaClientes;

	@Override
	public void salvar() {
		entidadeForm.setAtivo(ativo.isSelected());
		entidadeForm.setCnpj(cnpj.getText());
		entidadeForm.setComprador(comprador.getText());
		entidadeForm.setFirma(firma.getText());
		entidadeForm.setInscricao(inscricaoEstadual.getText());
		
		Logradouro logradouro = new Logradouro();
		logradouro.setBairro(bairro.getText());
		logradouro.setCep(cep.getText());
		logradouro.setCidade(cidade.getText());
//		logradouro.setComplemento(complemento.getText());
		logradouro.setDdd(ddd.getText());
		logradouro.setEmail(email.getText());
		logradouro.setEndereco(endereco.getText());
		logradouro.setEstado(estado.getText());
		logradouro.setNumero(Integer.valueOf(numero.getText()));
		logradouro.setTelefone(telefone.getText());
		entidadeForm.setLogradouro(logradouro);
		
		super.salvar();
		mensagem.setText("Salvo com sucesso!");
	}

	public void exibirTelaClienteCadastro() {
		gerenciadorTela.exibirTelaClienteCadastro();
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		entidadeForm = new Cliente();
		listaClientes = FXCollections.observableArrayList(clienteService.listar());
		clienteTable = new TableView<Cliente>();
		clienteTable.setItems(listaClientes);
	}

	public void exibirTelaClienteLista() {
		gerenciadorTela.exibirTelaClienteLista();
	}

	@Override
	protected EntidadeService<Cliente> getEntidadeService() {
		return clienteService;
	}

	public ObservableList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ObservableList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public TableView<Cliente> getClienteTable() {
		return clienteTable;
	}

	public void setClienteTable(TableView<Cliente> clienteTable) {
		this.clienteTable = clienteTable;
	}


	public TextField getFirma() {
		return firma;
	}

	public void setFirma(TextField firma) {
		this.firma = firma;
	}

	public TextField getEndereco() {
		return endereco;
	}

	public void setEndereco(TextField endereco) {
		this.endereco = endereco;
	}

	public TextField getNumero() {
		return numero;
	}

	public void setNumero(TextField numero) {
		this.numero = numero;
	}

	public TextField getBairro() {
		return bairro;
	}

	public void setBairro(TextField bairro) {
		this.bairro = bairro;
	}

	public TextField getCep() {
		return cep;
	}

	public void setCep(TextField cep) {
		this.cep = cep;
	}

	public TextField getEstado() {
		return estado;
	}

	public void setEstado(TextField estado) {
		this.estado = estado;
	}

	public TextField getCidade() {
		return cidade;
	}

	public void setCidade(TextField cidade) {
		this.cidade = cidade;
	}

	public TextField getDdd() {
		return ddd;
	}

	public void setDdd(TextField ddd) {
		this.ddd = ddd;
	}

	public TextField getTelefone() {
		return telefone;
	}

	public void setTelefone(TextField telefone) {
		this.telefone = telefone;
	}

	public TextField getCnpj() {
		return cnpj;
	}

	public void setCnpj(TextField cnpj) {
		this.cnpj = cnpj;
	}

	public TextField getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(TextField inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public TextField getComprador() {
		return comprador;
	}

	public void setComprador(TextField comprador) {
		this.comprador = comprador;
	}

	public TextField getEmail() {
		return email;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public RadioButton getAtivo() {
		return ativo;
	}

	public void setAtivo(RadioButton ativo) {
		this.ativo = ativo;
	}

	public Label getMensagem() {
		return mensagem;
	}

	public void setMensagem(Label mensagem) {
		this.mensagem = mensagem;
	}

}