package com.projetos.controle_entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=30)
	@Size(max = 30, message = "Campo BAIRRO tem que ter até 30 caracteres")
	private String bairro;

	@Column(length=8)
	@Size(max = 8, message = "Campo CEP tem que ter até 8 caracteres")
	private String cep;

	@Column(length=30)
	@Size(max = 30, message = "Campo CIDADE tem que ter até 30 caracteres")
	private String cidade;

	@Column(length=20)
	@Size(max = 20, message = "Campo CEP tem que ter até 20 caracteres")
	private String complemento;

	@Column(length=3)
	@Size(max = 3, message = "Campo DDD tem que ter até 3 caracteres")
	private String ddd;

	@Column(length=100)
	@Size(max = 100, message = "Campo EMAIL tem que ter até 100 caracteres")
	private String email;

	@Column(length=120)
	@Size(max = 120, message = "Campo ENDEREÇO tem que ter até 120 caracteres")
	private String endereco;

	@Column(length=2)
	@Size(max = 2, message="Campo ESTADO tem que ter até 2 caracteres")
	private String estado;

	private int numero;

	@Column(length=12)
	@Size(max = 12, message = "Campo TELEFONE tem que ter até 12 caracteres")
	private String telefone;
	
	@Column(length=12)
	@Size(max = 12, message = "Campo CELULAR tem que ter até 12 caracteres")
	private String celular;
	
	@Column(length=3, name="ddd_celular")
	@Size(max = 3, message = "Campo DDD tem que ter até 3 caracteres")
	private String dddCelular;

	public Logradouro() {
	}

	@Override
	public String toString() {
		return "Logradouro [id=" + id + ", cidade=" + cidade + ", endereco=" + endereco + ", numero=" + numero + "]";
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

}