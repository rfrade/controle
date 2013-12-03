package com.projetos.controle_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@Table(name="fornecedor")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	private byte ativo;

	@Column(length=15)
	private String cnpj;

	@Column(length=50)
	private String comprador;

	@Column(length=100)
	private String firma;

	@Column(length=15)
	private String inscricao;

	//bi-directional many-to-one association to Logradouro
	@ManyToOne
	@JoinColumn(name="id_logradouro")
	private Logradouro logradouro;

	public Fornecedor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getComprador() {
		return this.comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getFirma() {
		return this.firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getInscricao() {
		return this.inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

}