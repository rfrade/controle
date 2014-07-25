package com.projetos.controle_entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
	@Size(max = 60, message = "Campo CEP tem que ter até 30 caracteres")
	private String cobranca;

	@Column(length=30)
	@Size(max = 60, message = "Campo COLEÇÃO tem que ter até 60 caracteres")
	private String colecao;

	@Column
	private double comissao;

	@Column(length=60)
	@Size(max = 60, message = "Campo CONDIÇÕES tem que ter até 60 caracteres")
	private String condicoes;

	@Temporal(TemporalType.DATE)
	@Column(name="data_pedido")
	private Date dataPedido;

	@Column(length=60)
	@Size(max = 60, message = "Campo ENTREGA tem que ter até 60 caracteres")
	private String entrega;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	@NotNull(message = "Selecione um CLIENTE")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	@NotNull(message = "Selecione um FORNECEDOR")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name="id_vendedor")
	@NotNull(message = "Selecione um VENDEDOR")
	private Vendedor vendedor;

	@Column(length=60)
	@Size(max = 60, message = "Campo TRANSPORTADOR tem que ter até 60 caracteres")
	private String transportador;
	
	@Column(length=180)
	@Size(max = 180, message = "Campo OBSERVAÇÃO tem que ter até 180 caracteres")
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

	@Column(name="valor_total")
	private double valorTotal;
	
	@Column(name="valor_sub_total")
	private double valorSubTotal;
	
	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();

//	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.REMOVE)
//	private Recebimento recebimento = new ArrayList<>();

	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name="id_recebimento", insertable=true, updatable=true)
	private Recebimento recebimento;

	@Transient
	private Integer quantidadeItens;

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

	public ItemPedido addItemPedido(ItemPedido itemPedido) {
		getItensPedido().add(itemPedido);
		itemPedido.setPedido(this);

		return itemPedido;
	}

	public ItemPedido removeItemPedido(ItemPedido itemPedido) {
		getItensPedido().remove(itemPedido);
		itemPedido.setPedido(null);

		return itemPedido;
	}

	public Integer getQuantidadeItens() {
		Integer quantidadeTotal = 0;
		if (itensPedido == null) {
			return 0;
		}
		for (ItemPedido item : itensPedido) {
			if (item.getQuantidadeTamanho1() != null) {
				quantidadeTotal += item.getQuantidadeTamanho1();
			}
			if (item.getQuantidadeTamanho2() != null) {
				quantidadeTotal += item.getQuantidadeTamanho2();
			}
			if (item.getQuantidadeTamanho3() != null) {
				quantidadeTotal += item.getQuantidadeTamanho3();
			}
			if (item.getQuantidadeTamanho4() != null) {
				quantidadeTotal += item.getQuantidadeTamanho4();
			}
			if (item.getQuantidadeTamanho5() != null) {
				quantidadeTotal += item.getQuantidadeTamanho5();
			}
			if (item.getQuantidadeTamanho6() != null) {
				quantidadeTotal += item.getQuantidadeTamanho6();
			}
			if (item.getQuantidadeTamanho7() != null) {
				quantidadeTotal += item.getQuantidadeTamanho7();
			}
			if (item.getQuantidadeTamanho8() != null) {
				quantidadeTotal += item.getQuantidadeTamanho8();
			}
		}
		return quantidadeTotal;
	}

	/*public Recebimento addRecebimento(Recebimento recebimento) {
		getRecebimentos().add(recebimento);
		recebimento.setPedido(this);

		return recebimento;
	}

	public Recebimento removeRecebimento(Recebimento recebimento) {
		getRecebimentos().remove(recebimento);
		recebimento.setPedido(null);

		return recebimento;
	}*/

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

	public List<ItemPedido> getItensPedido() {
		/*if (itensPedido == null) {
			itensPedido = new ArrayList<>();
		}*/
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(double valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
		recebimento.setPedido(this);
	}

	/**
	 * Copia as propriedades, sem o id e com a data atual
	 * @return novo pedido
	 */
	public Pedido copy() {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setCobranca(cobranca);
		pedido.setColecao(colecao);
		pedido.setCondicoes(condicoes);
		pedido.setDataPedido(new Date());
		pedido.setDesconto1(desconto1);
		pedido.setDesconto2(desconto2);
		pedido.setDesconto3(desconto3);
		pedido.setDesconto4(desconto4);
		pedido.setDescontoTotal(descontoTotal);
		pedido.setEntrega(entrega);
		pedido.setFornecedor(fornecedor);
		pedido.setObservacao(observacao);
		pedido.setTransportador(transportador);
		pedido.setValorSubTotal(valorSubTotal);
		pedido.setValorTotal(valorTotal);
		pedido.setVendedor(vendedor);

		for (ItemPedido item : itensPedido) {
			ItemPedido novoItem = item.copy();
			pedido.addItemPedido(novoItem);
		}
		
		return pedido;
	}
	
}