package com.projetos.controle.tela.controller.base;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle_entiies.base.Entidade;
import com.projetos.controle_negocio.service.base.EntidadeService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseController<T extends Entidade> extends AbstractController {

    private T entidadeSelecionada;
    private List<T> listaEntidades;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private EntidadeService<T> service;

    public void filtrar() {
    }

    public void novo() {
        try {
            Class<? extends Object> classe = entidadeSelecionada.getClass();
            entidadeSelecionada = (T) classe.newInstance();
        } catch (InstantiationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        service.salvar(entidadeSelecionada);
    }

    public void remover() {
        service.remover(entidadeSelecionada);
    }

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
