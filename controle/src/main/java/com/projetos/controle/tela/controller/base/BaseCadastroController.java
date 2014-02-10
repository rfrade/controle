package com.projetos.controle.tela.controller.base;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import net.vidageek.mirror.dsl.Mirror;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseCadastroController<T extends Entidade> extends BaseController<T> implements Initializable {

    protected ObservableList<T> listaEntidades;
	
    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle resource) {
    	if (entidadeForm == null) {
    		Class<?> classe = new Mirror().on(this.getClass()).reflect().parentGenericType().atPosition(0);
    		entidadeForm = (T) new Mirror().on(classe).invoke().constructor().bypasser();
    	} else {
    		bindBeanToForm();
    	}
    	mascararCampos();
	}

	public void salvar() {
		try {
			bindFormToBean();
			if (entidadeForm.getId() == null) {
				validaInclusao();
			} else {
				validaAlteracao();
			}
			getEntidadeService().salvar(entidadeForm);
			JOptionPane.showMessageDialog(null, propertiesLoader.getProperty("cadastro.salvo_com_sucesso"));
		} catch (ValidacaoException e) {
			tratarErro(e);
		}
	}

	@Override
	protected void remover() {
		getEntidadeService().remover(entidadeForm);
	}

	protected void validaAlteracao() throws ValidacaoException {
		/*throw new ValidacaoException(null, null);*/
	}

    protected void validaInclusao() throws ValidacaoException {
    	/*if (false) {
    		throw new ValidacaoException(null, null);
    	}*/
	}

}
