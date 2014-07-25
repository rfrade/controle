package com.projetos.controle_negocio.filtro;

public class Ordenacao {

	private TipoOrdenacao tipoOrdenacao;
	private String campo;

	public Ordenacao(String campo, TipoOrdenacao tipoOrdenacao) {
		this.campo = campo;
		this.tipoOrdenacao = tipoOrdenacao;
	}

	public TipoOrdenacao getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	public String getCampo() {
		return campo;
	}

	public enum TipoOrdenacao {

		ASC("asc"), DESC("desc");

		private String metodoOrdenacao;

		private TipoOrdenacao(String metodoOrdenacao) {
			this.metodoOrdenacao = metodoOrdenacao;
		}

		public String getMetodoOrdenacao() {
			return metodoOrdenacao;
		}

	}

}
