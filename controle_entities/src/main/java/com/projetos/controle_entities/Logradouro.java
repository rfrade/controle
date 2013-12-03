package com.projetos.controle_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="logradouro")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Fornecedor
	@OneToMany(mappedBy="logradouro")
	private List<Fornecedor> fornecedors;

	//bi-directional one-to-one association to Vendedor
	@OneToOne(mappedBy="logradouro")
	private Vendedor vendedor;

	public Logradouro() {
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

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setLogradouro(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setLogradouro(null);

		return cliente;
	}

	public List<Fornecedor> getFornecedors() {
		return this.fornecedors;
	}

	public void setFornecedors(List<Fornecedor> fornecedors) {
		this.fornecedors = fornecedors;
	}

	public Fornecedor addFornecedor(Fornecedor fornecedor) {
		getFornecedors().add(fornecedor);
		fornecedor.setLogradouro(this);

		return fornecedor;
	}

	public Fornecedor removeFornecedor(Fornecedor fornecedor) {
		getFornecedors().remove(fornecedor);
		fornecedor.setLogradouro(null);

		return fornecedor;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}