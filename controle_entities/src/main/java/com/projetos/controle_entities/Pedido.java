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

	private double comissao;

	@Column(length=60)
	private String condicoes;

	@Temporal(TemporalType.DATE)
	@Column(name="data_pedido")
	private Date dataPedido;

	@Column(length=60)
	private String entrega;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name="id_vendedor")
	private Vendedor vendedor;

	@Column(length=60)
	private String transportador;
	
	@Column(length=180)
	private String observacao;

	@Column
	private double desconto1;
	
	@Column
	private double desconto2;
	
	@Column
	private double desconto3;
	
	@Column
	private double desconto4;
	
	@Column(name="desconto_total")
	private double descontoTotal;

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

	public double getDesconto1() {
		return desconto1;
	}

	public void setDesconto1(double desconto1) {
		this.desconto1 = desconto1;
	}

	public double getDesconto2() {
		return desconto2;
	}

	public void setDesconto2(double desconto2) {
		this.desconto2 = desconto2;
	}

	public double getDesconto3() {
		return desconto3;
	}

	public void setDesconto3(double desconto3) {
		this.desconto3 = desconto3;
	}

	public double getDesconto4() {
		return desconto4;
	}

	public void setDesconto4(double desconto4) {
		this.desconto4 = desconto4;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public double getDescontoTotal() {
		return descontoTotal;
	}

	public void setDescontoTotal(double descontoTotal) {
		this.descontoTotal = descontoTotal;
	}

}