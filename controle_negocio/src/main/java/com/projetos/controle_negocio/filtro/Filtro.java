package com.projetos.controle_negocio.filtro;

public class Filtro {

	private String nomePropriedade;
	private OperadorLogico operadorLogico;
	private Comparador comparador;
	private TipoFiltro tipoFiltro;
	private Object valor;

	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro) {
		this.nomePropriedade = nomePropriedade;
		if (tipoFiltro == TipoFiltro.BOOLEAN) {
			comparador = Comparador.EQUALS;
		} else {
			comparador = Comparador.CONTAINS_IGNORE_CASE;
		}
		operadorLogico = OperadorLogico.AND;
	}
	
	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Object valor) {
		this(nomePropriedade, tipoFiltro);
		this.valor = valor;
	}
	
	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador, OperadorLogico operadorLogico) {
		this(nomePropriedade, tipoFiltro);
		this.comparador = comparador;
		this.operadorLogico = operadorLogico;
	}
	
	public Filtro(String nomePropriedade, TipoFiltro tipoFiltro, Comparador comparador, OperadorLogico operadorLogico, Object valor) {
		this(nomePropriedade, tipoFiltro);
		this.comparador = comparador;
		this.operadorLogico = operadorLogico;
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
