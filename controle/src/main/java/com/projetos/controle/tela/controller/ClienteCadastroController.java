package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.EntidadeService;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ClienteCadastroController extends BaseCadastroController<Cliente> {

	@Autowired
	private ClienteService clienteService;

	@FXML
	@CampoTela(bean = "firma")
	private TextField firma;
	
	@FXML
	@CampoTela(bean = "logradouro.endereco")
	private TextField endereco;
	
	@FXML
	@CampoTela(bean = "logradouro.numero")
	private TextField numero;
	
	@FXML
	@CampoTela(bean = "logradouro.bairro")
	private TextField bairro;
	
	@FXML
	@CampoTela(bean = "logradouro.cep")
	private TextField cep;
	
	@FXML
	@CampoTela(bean = "logradouro.estado")
	private TextField estado;
	
	@FXML
	@CampoTela(bean = "logradouro.cidade")
	private TextField cidade;
	
	@FXML
	@CampoTela(bean = "logradouro.ddd")
	private TextField ddd;
	
	@FXML
	@CampoTela(bean = "logradouro.telefone")
	private TextField telefone;
	
	@FXML
	@CampoTela(bean = "cnpj")
	private TextField cnpj;
	
	@FXML
	@CampoTela(bean = "inscricao")
	private TextField inscricaoEstadual;
	
	@FXML
	@CampoTela(bean = "comprador")
	private TextField comprador;
	
	@FXML
	@CampoTela(bean = "logradouro.email")
	private TextField email;
	
	@FXML
	@CampoTela(bean = "ativo")
	private RadioButton ativo;

	@FXML
	private TableView<Cliente> tabela;

	@Override
	protected EntidadeService<Cliente> getEntidadeService() {
		return clienteService;
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		setClienteAtivo();
	}

	@Override
	public void salvarComMensagem() {
		super.salvarComMensagem();
		setClienteAtivo();
	}

	private void setClienteAtivo() {
		if (entidadeForm.getId() == null) {
			entidadeForm.setAtivo(true);
			ativo.setSelected(true);
		}
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

}