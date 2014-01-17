package com.projetos.controle_negocio.filtro;


public class Filtro {

	private String nomePropriedade;
	private Object valor;

	public boolean isValido() {
		return valor != null;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

}
