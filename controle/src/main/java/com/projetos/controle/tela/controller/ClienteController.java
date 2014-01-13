package com.projetos.controle.tela.controller;

import java.lang.reflect.Field;
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
import javafx.scene.input.MouseEvent;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle.tela.util.CelulaFactory;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.reflection.BeanUtil;

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

	/*@FXML
	@CampoTela(bean = "inativo")*/
	private RadioButton inativo;
	
	@FXML
	private Label mensagem;

	private ObservableList<Cliente> listaClientes;

	@Override
	public void salvar() {
		bindFormToBean();
		super.salvar();
		mensagem.setText("Salvo com sucesso!");
	}

	@Override
	public void remover() {
		if (entidadeForm == null) {
			entidadeForm = tabelaCliente.getSelectionModel().getSelectedItem();
		}
		super.remover();
		mensagem.setText("Removido com sucesso!");
	}

	public void bindBeanToForm() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(CampoTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(CampoTela.class).bean();
				Object value = BeanUtil.getPropriedade(entidadeForm, bean);
				
				if (campo instanceof TextField && value != null) {
					((TextField) campo).setText(value.toString());
				} else if (campo instanceof RadioButton  && value != null) {
					((RadioButton) campo).setSelected((Boolean)value);
				}
				
			}
		}
	}

	public void bindFormToBean() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(CampoTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(CampoTela.class).bean();
				
				if (campo instanceof TextField) {
					BeanUtil.setPropriedade(entidadeForm, bean, ((TextField) campo).getText());
				} else if (campo instanceof RadioButton) {
					BeanUtil.setPropriedade(entidadeForm, bean, ((RadioButton) campo).isSelected());
				}
			}
		}
	}

	public void exibirTelaClienteCadastro() {
		entidadeForm = new Cliente();
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
	    	Cliente selectedItem = tabelaCliente.getSelectionModel().getSelectedItem();
	    	if (selectedItem != null) {
	    		exibirTelaClienteCadastro();
	    		entidadeForm = selectedItem;
	    		bindBeanToForm();
	    	}
	    }
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarColunas() {
		
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(Coluna.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(Coluna.class).bean();
				
				CelulaFactory<Cliente, ?> celulaFactory = new CelulaFactory<>(bean);
				if (campo instanceof TableColumn) {
					((TableColumn) campo).setCellValueFactory(celulaFactory);
				} else {
					throw new IllegalArgumentException("Field: " + campo + " on class: " + this.getClass() + " must be of type TableColumn.");
				}
			}
		}
	}

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