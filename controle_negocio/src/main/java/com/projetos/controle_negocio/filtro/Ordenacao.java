package com.projetos.controle_negocio.filtro;

public class Ordenacao {

	private TipoOrdenacao tipoOrdenacao;
	private String campo;

	public Ordenacao(String campo, TipoOrdenacao tipoOrdenacao) {
		this.campo = campo;
		this.tipoOrdenacao = tipoOrdenacao;
	}

	public enum TipoOrdenacao {
		ASC,
		DESC
	}

}
