package com.projetos.controle.tela.controller.base;

import java.util.List;

import org.apache.log4j.Logger;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.validacao.MensagemValidacao;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseController<T extends Entidade> extends AbstractController<T> {

    private T entidadeForm;
    private List<T> listaEntidades;
	protected Logger log = Logger.getLogger(this.getClass());

    private MensagemValidacao mensagemValidacao;

    public void filtrar() {
    }

    @SuppressWarnings("unchecked")
	public void novo() {
        try {
            Class<? extends Object> classe = entidadeForm.getClass();
            entidadeForm = (T) classe.newInstance();
        } catch (InstantiationException ex) {
        	tratarErro(ex);
        } catch (IllegalAccessException ex) {
        	tratarErro(ex);
        }
    }

	public void salvar() {
		try {
			if (entidadeForm.getId() != null) {
				validaInclusao();
			} else {
				validaAlteracao();
			}
			getEntidadeService().salvar(entidadeForm);
		} catch (ValidacaoException e) {
			tratarErro(e);
		}
	}

	private void tratarErro(Exception e) {
		log.error(e.getMessage(), e);
	}

    protected void validaAlteracao() throws ValidacaoException {
		throw new ValidacaoException(null, null);
	}

    protected void validaInclusao() throws ValidacaoException {
    	throw new ValidacaoException(null, null);
	}

	public void remover() {
    	getEntidadeService().remover(entidadeForm);
    }

    protected abstract EntidadeService<T> getEntidadeService();

    public T getEntidadeForm() {
        return entidadeForm;
    }

    public void setEntidadeForm(T entidadeForm) {
        this.entidadeForm = entidadeForm;
    }

    public List<T> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<T> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
    
}
