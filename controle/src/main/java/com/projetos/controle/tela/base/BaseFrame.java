package com.projetos.controle.tela.base;

import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle_entities.Entidade;

/**
 * @author Rafael
 */
//TODO: Excluir essa classe
@Deprecated
public abstract class BaseFrame<T extends Entidade> extends AbstractFrame {

	private static final long serialVersionUID = 1L;

    public BaseFrame() {
    }

    protected abstract BaseController<T> getController();

}