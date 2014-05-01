package com.projetos.controle.tela.controller.base;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import net.vidageek.mirror.dsl.Mirror;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseCadastroController<T extends Entidade> extends BaseEntityController<T> {

    protected ObservableList<T> listaEntidades;
    
    private TableView<T> tabela;
	
    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle resource) {
    	if (entidadeForm == null) {
    		Class<?> classe = new Mirror().on(this.getClass()).reflect().parentGenericType().atPosition(0);
    		entidadeForm = (T) new Mirror().on(classe).invoke().constructor().bypasser();
    	}
    	bindBeanToForm();
    	mascararCampos();
	}

	public void salvarComMensagem() {
		try {
			salvar();
			exibirMensagem("cadastro.salvo_com_sucesso");
		} catch (ValidacaoException e) {
			tratarErro(e);
		}
	}

	public void salvarSemMensagem() {
		try {
			salvar();
		} catch (ValidacaoException e) {
			tratarErro(e);
		}
	}

	private void salvar() throws ValidacaoException {
		bindFormToBean();
		if (entidadeForm.getId() == null) {
			validaInclusao();
		} else {
			validaAlteracao();
		}
		getEntidadeService().salvar(entidadeForm);
		if (!tabela.getItems().contains(entidadeForm)) {
			tabela.getItems().add(entidadeForm);
		} else {
			tabela.getItems().remove(entidadeForm);
			tabela.getItems().add(entidadeForm);
		}
	}

	public void remover() {
		//TODO: Excluir o método
		getEntidadeService().remover(entidadeForm);
		exibirMensagem("cadastro.removido_com_sucesso");
	}

	protected void validaAlteracao() throws ValidacaoException {
		/*throw new ValidacaoException(null, null);*/
	}

    protected void validaInclusao() throws ValidacaoException {
    	/*if (false) {
    		throw new ValidacaoException(null, null);
    	}*/
	}

    
	public void setTabela(TableView<T> tabela) {
		this.tabela = tabela;
	}

}
