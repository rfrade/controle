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
public class Vendedor implements Entidade, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=50)
	private String nome;

	//bi-directional one-to-one association to Logradouro
	@OneToOne
	@JoinColumn(name="id_logradouro")
	private Logradouro logradouro;

	public Vendedor() {
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + nome + "]";
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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