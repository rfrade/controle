package com.projetos.controle_negocio.filtro;

public enum OperadorLogico {
	AND("and"),
	OR("or");
	
	private String operation;
	
	private OperadorLogico(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

}
