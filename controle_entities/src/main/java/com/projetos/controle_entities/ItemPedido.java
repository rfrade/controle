package com.projetos.controle_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_pedido database table.
 * 
 */
@Entity
@Table(name="item_pedido")
@NamedQuery(name="ItemPedido.findAll", query="SELECT i FROM ItemPedido i")
public class ItemPedido implements Entidade, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=20)
	private String cor;

	@Column(length=100)
	private String descricao;

	@Column(length=100)
	private String observacao;

	@Column(name="quantidade_tamanho_1")
	private int quantidadeTamanho1;

	@Column(name="quantidade_tamanho_2")
	private int quantidadeTamanho2;

	@Column(name="quantidade_tamanho_3")
	private int quantidadeTamanho3;

	@Column(name="quantidade_tamanho_4")
	private int quantidadeTamanho4;

	@Column(name="quantidade_tamanho_5")
	private int quantidadeTamanho5;

	@Column(name="quantidade_tamanho_6")
	private int quantidadeTamanho6;

	@Column(name="quantidade_tamanho_7")
	private int quantidadeTamanho7;

	@Column(name="quantidade_tamanho_8")
	private int quantidadeTamanho8;

	@Column(name="quantidade_total")
	private int quantidadeTotal;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	public ItemPedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getQuantidadeTamanho1() {
		return this.quantidadeTamanho1;
	}

	public void setQuantidadeTamanho1(int quantidadeTamanho1) {
		this.quantidadeTamanho1 = quantidadeTamanho1;
	}

	public int getQuantidadeTamanho2() {
		return this.quantidadeTamanho2;
	}

	public void setQuantidadeTamanho2(int quantidadeTamanho2) {
		this.quantidadeTamanho2 = quantidadeTamanho2;
	}

	public int getQuantidadeTamanho3() {
		return this.quantidadeTamanho3;
	}

	public void setQuantidadeTamanho3(int quantidadeTamanho3) {
		this.quantidadeTamanho3 = quantidadeTamanho3;
	}

	public int getQuantidadeTamanho4() {
		return this.quantidadeTamanho4;
	}

	public void setQuantidadeTamanho4(int quantidadeTamanho4) {
		this.quantidadeTamanho4 = quantidadeTamanho4;
	}

	public int getQuantidadeTamanho5() {
		return this.quantidadeTamanho5;
	}

	public void setQuantidadeTamanho5(int quantidadeTamanho5) {
		this.quantidadeTamanho5 = quantidadeTamanho5;
	}

	public int getQuantidadeTamanho6() {
		return this.quantidadeTamanho6;
	}

	public void setQuantidadeTamanho6(int quantidadeTamanho6) {
		this.quantidadeTamanho6 = quantidadeTamanho6;
	}

	public int getQuantidadeTamanho7() {
		return this.quantidadeTamanho7;
	}

	public void setQuantidadeTamanho7(int quantidadeTamanho7) {
		this.quantidadeTamanho7 = quantidadeTamanho7;
	}

	public int getQuantidadeTamanho8() {
		return this.quantidadeTamanho8;
	}

	public void setQuantidadeTamanho8(int quantidadeTamanho8) {
		this.quantidadeTamanho8 = quantidadeTamanho8;
	}

	public int getQuantidadeTotal() {
		return this.quantidadeTotal;
	}

	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}