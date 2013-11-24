package com.projetos.controle.tela.base;

import com.projetos.controle.tela.controller.base.BaseController;

/**
 * @author Rafael
 */
public abstract class BaseFrame extends AbstractFrame {

    private BaseController controller;

    public BaseFrame() {
        super.controller = this.controller;
    }

    public BaseController getController() {
        return controller;
    }

    public void setController(BaseController controller) {
        this.controller = controller;
    }

}