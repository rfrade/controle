package com.projetos.controle.tela.base;

import javafx.util.Callback;

public class CallbackTela implements Callback<Class<?>, Object> {

	private AbstractController controller;

	public CallbackTela(AbstractController controller) {
		this.controller = controller;
	}

	public Object call(Class<?> arg0) {
		return controller;
	}

}
