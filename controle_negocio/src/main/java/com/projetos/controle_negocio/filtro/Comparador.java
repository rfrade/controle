package com.projetos.controle_negocio.filtro;

public enum Comparador {

	EQUALS("eq"),
	EQUALS_IGNORE_CASE("equals"),
	NOT_EQUALS("ne"),
	CONTAINS("contains"),
	CONTAINS_IGNORE_CASE("containsIgnoreCase"),
	STARTS_WITH("startsWith"),
	STARTS_WITH_IGNORE_CASE("startsWithIgnoreCase"),
	GREATER_THAN("gt"),
	GREATER_OR_EQUALS("goe"),
	LOWER_THAN("lt"),
	LOWER_OR_EQUALS("loe"),
	IN("in");
	
	private String metodo;
	
	private Comparador(String metodo) {
		this.metodo = metodo;
	}

	public String getMetodo() {
		return metodo;
	}
	
}
