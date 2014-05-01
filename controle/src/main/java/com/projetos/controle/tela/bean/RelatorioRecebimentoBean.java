package com.projetos.controle.tela.bean;

import java.util.Date;
import java.util.List;

import com.projetos.controle_entities.Fornecedor;

public class RelatorioRecebimentoBean {

	private List<Fornecedor> listaFornecedor;
	private Boolean recebido;
	private Date dataApartir;
	private Date dataAte;
	private String colecao;

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public Date getDataApartir() {
		return dataApartir;
	}

	public void setDataApartir(Date dataApartir) {
		this.dataApartir = dataApartir;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public Boolean getRecebido() {
		return recebido;
	}

	public void setRecebido(Boolean recebido) {
		this.recebido = recebido;
	}

	public String getColecao() {
		return colecao;
	}

	public void setColecao(String colecao) {
		this.colecao = colecao;
	}

}
