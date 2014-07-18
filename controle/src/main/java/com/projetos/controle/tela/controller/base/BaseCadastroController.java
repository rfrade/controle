package com.projetos.controle.tela.controller.base;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import javax.validation.ConstraintViolation;

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
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
    	if (entidadeForm == null) {
    		entidadeForm = novaEntidadeForm();
    	}
    	bindBeanToForm();
    	mascararCampos();
	}

	public void salvarComMensagem() {
		try {
			salvar();
			exibirMensagem("cadastro.salvo_com_sucesso");
			entidadeForm = novaEntidadeForm();
			bindBeanToForm();
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		}
	}

	public void salvarSemMensagem() throws ValidacaoException {
		salvar();
		bindBeanToForm();
	}

	protected void salvar() throws ValidacaoException {
		bindFormToBean();
		validaPersistencia();
		entidadeForm = getEntidadeService().salvar(entidadeForm);
		atualizarTabela();
	}

	private void atualizarTabela() {
		if (!tabela.getItems().contains(entidadeForm)) {
			tabela.getItems().add(entidadeForm);
		} else {
			tabela.getItems().remove(entidadeForm);
			tabela.getItems().add(entidadeForm);
		}
		//TODO: Verificar ordenação padrão das tabelas
	}

	public void remover() {
		//TODO: Excluir o método
		getEntidadeService().remover(entidadeForm);
		exibirMensagem("cadastro.removido_com_sucesso");
	}

	/**
	 * @return nova entidade do tipo parametrizado
	 */
	@SuppressWarnings("unchecked")
	protected T novaEntidadeForm() {
		Class<?> classe = new Mirror().on(this.getClass()).reflect().parentGenericType().atPosition(0);
		return (T) new Mirror().on(classe).invoke().constructor().bypasser();
	}

    protected void validaPersistencia() throws ValidacaoException {
    	Set<ConstraintViolation<T>> violation = getValidator().validate(entidadeForm);
    	if (violation.iterator().hasNext()) {
    		String message = violation.iterator().next().getMessage();
    		throw new ValidacaoException(message);
    	}
    }
    
	public void setTabela(TableView<T> tabela) {
		this.tabela = tabela;
	}

}
