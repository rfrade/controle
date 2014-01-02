package com.projetos.controle.tela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelaPrincipalController extends AbstractController {

	@Autowired
	private ClienteController clienteController;

	public void exibirTelaClienteLista() {
		clienteController.exibirTelaClienteLista();
	}

}
