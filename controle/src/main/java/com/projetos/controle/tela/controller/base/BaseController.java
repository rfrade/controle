package com.projetos.controle.tela.controller.base;

import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.validacao.MensagemValidacao;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseController<T extends Entidade> extends AbstractController<T> {

    private T entidadeSelecionada;
    private List<T> listaEntidades;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private MensagemValidacao mensagemValidacao;

    public void filtrar() {
    }

    @SuppressWarnings("unchecked")
	public void novo() {
        try {
            Class<? extends Object> classe = entidadeSelecionada.getClass();
            entidadeSelecionada = (T) classe.newInstance();
        } catch (InstantiationException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void salvar() {
    	if (entidadeSelecionada.getId() != null) {
    		validaInclusao();
    	} else {
    		validaAlteracao();
    	}
    	getEntidadeService().salvar(entidadeSelecionada);
    }

    private void validaAlteracao() {
		
	}

	private void validaInclusao() {
		
	}

	public void remover() {
    	getEntidadeService().remover(entidadeSelecionada);
    }

    protected abstract EntidadeService<T> getEntidadeService();

    public T getEntidadeSelecionada() {
        return entidadeSelecionada;
    }

    public void setEntidadeSelecionada(T entidadeSelecionada) {
        this.entidadeSelecionada = entidadeSelecionada;
    }

    public List<T> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<T> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
    
}
