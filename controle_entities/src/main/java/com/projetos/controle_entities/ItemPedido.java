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
	private Integer quantidadeTamanho1;

	@Column(name="quantidade_tamanho_2")
	private Integer quantidadeTamanho2;

	@Column(name="quantidade_tamanho_3")
	private Integer quantidadeTamanho3;

	@Column(name="quantidade_tamanho_4")
	private Integer quantidadeTamanho4;

	@Column(name="quantidade_tamanho_5")
	private Integer quantidadeTamanho5;

	@Column(name="quantidade_tamanho_6")
	private Integer quantidadeTamanho6;

	@Column(name="quantidade_tamanho_7")
	private Integer quantidadeTamanho7;

	@Column(name="quantidade_tamanho_8")
	private Integer quantidadeTamanho8;

	@Column(name="quantidade_total")
	private Integer quantidadeTotal;

	@Column(name="valor_total")
	private Double valorTotal;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	//bi-directional many-to-one association to Pedido
	@ManyToOne()
	@JoinColumn(name="id_pedido", updatable = false)
	private Pedido pedido;

	public ItemPedido() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ItemPedido copy() {
		ItemPedido item = new ItemPedido();
		
		item.setCor(cor);
		item.setDescricao(descricao);
		item.setObservacao(observacao);
		item.setProduto(produto);
		item.setQuantidadeTamanho1(quantidadeTamanho1);
		item.setQuantidadeTamanho2(quantidadeTamanho2);
		item.setQuantidadeTamanho3(quantidadeTamanho3);
		item.setQuantidadeTamanho4(quantidadeTamanho4);
		item.setQuantidadeTamanho5(quantidadeTamanho5);
		item.setQuantidadeTamanho6(quantidadeTamanho6);
		item.setQuantidadeTamanho7(quantidadeTamanho7);
		item.setQuantidadeTamanho8(quantidadeTamanho8);
		item.setQuantidadeTotal(quantidadeTotal);
		item.setValorTotal(valorTotal);
		
		return item;
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

	public Integer getQuantidadeTamanho1() {
		return this.quantidadeTamanho1;
	}

	public void setQuantidadeTamanho1(Integer quantidadeTamanho1) {
		this.quantidadeTamanho1 = quantidadeTamanho1;
	}

	public Integer getQuantidadeTamanho2() {
		return this.quantidadeTamanho2;
	}

	public void setQuantidadeTamanho2(Integer quantidadeTamanho2) {
		this.quantidadeTamanho2 = quantidadeTamanho2;
	}

	public Integer getQuantidadeTamanho3() {
		return this.quantidadeTamanho3;
	}

	public void setQuantidadeTamanho3(Integer quantidadeTamanho3) {
		this.quantidadeTamanho3 = quantidadeTamanho3;
	}

	public Integer getQuantidadeTamanho4() {
		return this.quantidadeTamanho4;
	}

	public void setQuantidadeTamanho4(Integer quantidadeTamanho4) {
		this.quantidadeTamanho4 = quantidadeTamanho4;
	}

	public Integer getQuantidadeTamanho5() {
		return this.quantidadeTamanho5;
	}

	public void setQuantidadeTamanho5(Integer quantidadeTamanho5) {
		this.quantidadeTamanho5 = quantidadeTamanho5;
	}

	public Integer getQuantidadeTamanho6() {
		return this.quantidadeTamanho6;
	}

	public void setQuantidadeTamanho6(Integer quantidadeTamanho6) {
		this.quantidadeTamanho6 = quantidadeTamanho6;
	}

	public Integer getQuantidadeTamanho7() {
		return this.quantidadeTamanho7;
	}

	public void setQuantidadeTamanho7(Integer quantidadeTamanho7) {
		this.quantidadeTamanho7 = quantidadeTamanho7;
	}

	public Integer getQuantidadeTamanho8() {
		return this.quantidadeTamanho8;
	}

	public void setQuantidadeTamanho8(Integer quantidadeTamanho8) {
		this.quantidadeTamanho8 = quantidadeTamanho8;
	}

	public Integer getQuantidadeTotal() {
		return this.quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public Double getValorTotal() {
		return this.valorTotal;
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

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}