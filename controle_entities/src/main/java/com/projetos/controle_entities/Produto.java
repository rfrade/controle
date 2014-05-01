package com.projetos.controle_entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(name="produto")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Entidade, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=100)
	private String descricao;

	private int quantidade;

	@Column(length=10)
	private String referencia;

	@Column(length=50)
	private String tamanho;

	@Column(name="valor_unitario")
	private double valorUnitario;

	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;

	public Produto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public double getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + referencia + ", " + descricao + "]";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}