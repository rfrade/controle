package com.projetos.controle_negocio.filtro;


public class Filtro {

	private String nomePropriedade;
	private Object valor;

	public Filtro() {
	}

	public boolean isValido() {
		return valor != null;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

}
