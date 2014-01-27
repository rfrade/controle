package com.projetos.controle_entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@Table(name="pedido")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Entidade, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=60)
	private String cobranca;

	@Column(length=30)
	private String colecao;

	private double comicao;

	@Column(length=60)
	private String condicoes;

	@Temporal(TemporalType.DATE)
	@Column(name="data_pedido")
	private Date dataPedido;

	@Column(length=60)
	private String entrega;

	@Column(name="id_cliente")
	private int idCliente;

	@Column(name="id_fornecedor")
	private int idFornecedor;

	@Column(name="id_vendedor")
	private int idVendedor;

	@Column(length=60)
	private String transportador;

	//bi-directional many-to-one association to ItemPedido
	@OneToMany(mappedBy="pedido")
	private List<ItemPedido> itemPedidos;

	//bi-directional many-to-one association to Recebimento
	@OneToMany(mappedBy="pedido")
	private List<Recebimento> recebimentos;

	public Pedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCobranca() {
		return this.cobranca;
	}

	public void setCobranca(String cobranca) {
		this.cobranca = cobranca;
	}

	public String getColecao() {
		return this.colecao;
	}

	public void setColecao(String colecao) {
		this.colecao = colecao;
	}

	public double getComicao() {
		return this.comicao;
	}

	public void setComicao(double comicao) {
		this.comicao = comicao;
	}

	public String getCondicoes() {
		return this.condicoes;
	}

	public void setCondicoes(String condicoes) {
		this.condicoes = condicoes;
	}

	public Date getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getEntrega() {
		return this.entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdFornecedor() {
		return this.idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public int getIdVendedor() {
		return this.idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getTransportador() {
		return this.transportador;
	}

	public void setTransportador(String transportador) {
		this.transportador = transportador;
	}

	public List<ItemPedido> getItemPedidos() {
		return this.itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public ItemPedido addItemPedido(ItemPedido itemPedido) {
		getItemPedidos().add(itemPedido);
		itemPedido.setPedido(this);

		return itemPedido;
	}

	public ItemPedido removeItemPedido(ItemPedido itemPedido) {
		getItemPedidos().remove(itemPedido);
		itemPedido.setPedido(null);

		return itemPedido;
	}

	public List<Recebimento> getRecebimentos() {
		return this.recebimentos;
	}

	public void setRecebimentos(List<Recebimento> recebimentos) {
		this.recebimentos = recebimentos;
	}

	public Recebimento addRecebimento(Recebimento recebimento) {
		getRecebimentos().add(recebimento);
		recebimento.setPedido(this);

		return recebimento;
	}

	public Recebimento removeRecebimento(Recebimento recebimento) {
		getRecebimentos().remove(recebimento);
		recebimento.setPedido(null);

		return recebimento;
	}

}