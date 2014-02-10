package com.projetos.controle.tela.controller.base;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseListController<T extends Entidade> extends BaseController<T> implements Initializable {

    protected ObservableList<T> listaEntidades;
	
	public void prepararAlteracao(MouseEvent event) {
	    if (event.getClickCount() > 1) {
	    	T selectedItem = getTabela().getSelectionModel().getSelectedItem();
	    	if (selectedItem != null) {
	    		getBaseCadastroController().setEntidadeForm(selectedItem);
	    		exibirTelaCadastro();
	    	}
	    }
	}

	public abstract TableView<T> getTabela();
	public abstract void exibirTelaLista();

	protected void loadTable(List<T> lista) {
		listaEntidades = FXCollections.observableArrayList(lista);
		getTabela().setItems(listaEntidades);
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
	}

	public void remover() {
		getEntidadeService().remover(entidadeForm);
		if (entidadeForm == null) {
			entidadeForm = getTabela().getSelectionModel().getSelectedItem();
		}
		getEntidadeService().remover(entidadeForm);
		/*mensagem.setText("Removido com sucesso!");*/
	}

	public void prepararInclusao() {
		exibirTelaCadastro();
	}

	public abstract void exibirTelaCadastro();

	protected abstract BaseCadastroController<T> getBaseCadastroController();

	public ObservableList<T> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(ObservableList<T> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

}
