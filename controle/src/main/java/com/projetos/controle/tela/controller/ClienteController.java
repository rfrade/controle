package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

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
	private TelaPrincipalController telaPrincipalController;

	@FXML
	private TableView<Cliente> tabelaCliente;

	@FXML
	private TableColumn<Cliente, String> colunaFirma;
	
	@FXML
	private TableColumn<Cliente, String> colunaEndereco;
	
	@FXML
	private TableColumn<Cliente, Integer> colunaNumero;
	
	@FXML
	private TableColumn<Cliente, String> colunaBairro;
	
	@FXML
	private TableColumn<Cliente, String> colunaCep;

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
	private RadioButton inativo;
	
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
		telaPrincipalController.exibirTelaClienteCadastro();
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		listaClientes = FXCollections.observableArrayList(clienteService.listar());
		tabelaCliente.setItems(listaClientes);
		iniciarColunas();
	}

	public void exibirTelaClienteCadastroModoAlteracao(MouseEvent event) {
	    if (event.getClickCount() > 1) {
	    	entidadeForm = tabelaCliente.getSelectionModel().getSelectedItem();
	    	exibirTelaClienteCadastro();
	    }
	}

	private void iniciarColunas() {
		/*colunaFirma = new TableColumn<Cliente, String>();
		colunaEndereco = new TableColumn<Cliente, String>();
		colunaNumero = new TableColumn<Cliente, Integer>();
		colunaBairro = new TableColumn<Cliente, String>();
		colunaCep = new TableColumn<Cliente, String>();*/
		colunaFirma.setCellValueFactory(new PropertyValueFactory<Cliente, String>("firma"));
		/*colunaEndereco.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Cliente, String> cliente) {
		         // p.getValue() returns the Person instance for a particular TableView row
		         return null;
		     }
		  });
		colunaNumero.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("logradouro.numero"));
		colunaBairro.setCellValueFactory(new PropertyValueFactory<Cliente, String>("logradouro.bairro"));
		colunaCep.setCellValueFactory(new PropertyValueFactory<Cliente, String>("logradouro.cep"));*/
	}

	/*public class TabelaClienteCelulaFactory implements Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>> {
		
		private String nomePropriedade;
		
		public TabelaClienteCelulaFactory(String nomePropriedade) {
			this.nomePropriedade = nomePropriedade;
		}
		
		public ObservableValue<String> call(CellDataFeatures<Cliente, String> cliente) {
			return new ReadOnlyObjectWrapper<>();
		}
		
	}*/

	/*public class TabelaClienteObject {
		private SimpleStringProperty firma;
		private SimpleStringProperty endereco;
		private SimpleIntegerProperty numero;
		private SimpleStringProperty bairro;
		private SimpleStringProperty cep;
		
		public TabelaClienteObject(Cliente cliente, Logradouro logradouro) {
			firma = new SimpleStringProperty(initialValue);
			endereco = new SimpleStringProperty();
			numero =;
			bairro = new SimpleStringProperty();
			cep = new SimpleStringProperty;
		}
		
	}*/

	public void exibirTelaClienteLista() {
		entidadeForm = new Cliente();
		telaPrincipalController.exibirTelaClienteLista();
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

	public TableView<Cliente> getTabelaCliente() {
		return tabelaCliente;
	}

	public void setTabelaCliente(TableView<Cliente> tabelaCliente) {
		this.tabelaCliente = tabelaCliente;
	}
	
	public RadioButton getInativo() {
		return inativo;
	}

	public void setInativo(RadioButton inativo) {
		this.inativo = inativo;
	}

}