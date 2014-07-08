package com.projetos.controle_negocio.filtro;

public class Filtro {

	private String nomePropriedade;
	private OperadorLogico operadorLogico;
	private Comparador comparador;
	private TipoFiltro tipoFiltro;
	private Object valor;

	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador) {
		this.nomePropriedade = nomePropriedade;
		this.tipoFiltro = tipoFiltro;
		this.comparador = comparador;
		this.operadorLogico = OperadorLogico.AND;
	}

	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador, OperadorLogico operadorLogico) {
		this(nomePropriedade, tipoFiltro, comparador);
		this.operadorLogico = operadorLogico;
	}
	
	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador, Object valor) {
		this(nomePropriedade, tipoFiltro, comparador);
		this.valor = valor;
	}

	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador, OperadorLogico operadorLogico, Object valor) {
		this(nomePropriedade, tipoFiltro, comparador, operadorLogico);
		this.valor = valor;
	}

	public boolean isValido() {
		return valor != null;
	}

	@Override
	public String toString() {
		return operadorLogico + " " + nomePropriedade + " " + comparador.getMetodo() + " " + valor;
	}

	public Object getValor() {
		switch (tipoFiltro) {
		case INTEGER:
			return Integer.valueOf(valor.toString());
		case BOOLEAN:
			return valor;
		case DATE:
			return valor;
		case LONG:
			return Long.valueOf(valor.toString());
		case STRING:
			return valor;
		case LIST:
			return valor;
		case OBJECT:
			return valor;
		default:
			return valor;
		}
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

	public OperadorLogico getOperadorLogico() {
		return operadorLogico;
	}

	public void setOperadorLogico(OperadorLogico operadorLogico) {
		this.operadorLogico = operadorLogico;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public TipoFiltro getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(TipoFiltro tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}
