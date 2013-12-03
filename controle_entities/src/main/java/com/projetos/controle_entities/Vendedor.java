package com.projetos.controle_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vendedor database table.
 * 
 */
@Entity
@Table(name="vendedor")
@NamedQuery(name="Vendedor.findAll", query="SELECT v FROM Vendedor v")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="id_logradouro")
	private int idLogradouro;

	@Column(length=50)
	private String nome;

	//bi-directional one-to-one association to Logradouro
	@OneToOne
	@JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	private Logradouro logradouro;

	public Vendedor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdLogradouro() {
		return this.idLogradouro;
	}

	public void setIdLogradouro(int idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

}