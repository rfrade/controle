package com.projetos.controle_entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the logradouro database table.
 * 
 */
@Entity
@Table(name="logradouro")
@NamedQuery(name="Logradouro.findAll", query="SELECT l FROM Logradouro l")
public class Logradouro implements Entidade, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=30)
	private String bairro;

	@Column(length=8)
	private String cep;

	@Column(length=30)
	private String cidade;

	@Column(length=20)
	private String complemento;

	@Column(length=3)
	private String ddd;

	@Column(length=100)
	private String email;

	@Column(length=120)
	private String endereco;

	@Column(length=2)
	private String estado;

	private int numero;

	@Column(length=9)
	private String telefone;

	public Logradouro() {
	}

	@Override
	public String toString() {
		return "Logradouro [id=" + id + ", cidade=" + cidade + ", endereco=" + endereco + ", numero=" + numero + "]";
	}

	public Integer getId() {
		return this.id;
	}

	/*public void setId(Integer id) {
		this.id = id;
	}*/

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}