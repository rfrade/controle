package com.projetos.controle.tela.controller.base;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import com.projetos.controle.tela.base.MouseClickedDefault;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseListController<T extends Entidade> extends BaseController<T> implements Initializable {

    protected ObservableList<T> listaEntidades;
    private EventHandler<MouseEvent> defaultClickHandler;

	public void prepararAlteracao(MouseEvent event) {
	}

	public abstract void exibirTelaLista();

	protected void loadTable(List<T> lista) {
		listaEntidades = FXCollections.observableArrayList(lista);
		getTabela().getItems().setAll(listaEntidades);
		loadColumns();
	}

    public void pesquisar() {
    	List<Filtro> filtros = getCamposFiltro();
    	List<T> listaTabela = getEntidadeService().filtrar(filtros);
    	loadTable(listaTabela);
    }

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		loadTable(getEntidadeService().listar());
		defaultClickHandler = new MouseClickedDefault<T>(getTabela(), this, getBaseCadastroController());
		getTabela().setOnMouseClicked(defaultClickHandler);
		bindBeanToForm();
	}

	public void remover() {
		entidadeForm = getTabela().getSelectionModel().getSelectedItem();
		if (entidadeForm == null) {
			exibirMensagem("cadastro.selecione_um_registro_para_remover");
			return;
		}
		getEntidadeService().remover(entidadeForm);
		getTabela().getItems().remove(entidadeForm);
		exibirMensagem("cadastro.removido_com_sucesso");
	}

	public void prepararInclusao() {
		getBaseCadastroController().setEntidadeForm(null);
		exibirTelaCadastro();
	}

	public void exibirTelaCadastro() {
		getBaseCadastroController().setTabela(getTabela());
	}

	public abstract TableView<T> getTabela();
	protected abstract BaseCadastroController<T> getBaseCadastroController();

	public ObservableList<T> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(ObservableList<T> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

}
