package com.projetos.controle.tela.base;

import javafx.fxml.Initializable;
import javafx.util.Callback;

@SuppressWarnings("restriction")
public class CallbackTela implements Callback<Class<?>, Object> {

	private Initializable controller;

	public CallbackTela(Initializable controller) {
		this.controller = controller;
	}

	public Object call(Class<?> arg0) {
		return controller;
	}

}
