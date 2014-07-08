package com.projetos.controle.tela.controller.base;

import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.field.DecimalNumberField;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_util.reflection.BeanUtil;
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
