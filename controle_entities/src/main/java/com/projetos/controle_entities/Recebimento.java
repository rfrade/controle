package com.projetos.controle_entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the recebimento database table.
 * 
 */
@Entity
@Table(name="recebimento")
@NamedQuery(name="Recebimento.findAll", query="SELECT r FROM Recebimento r")
public class Recebimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_recebimento")
	private Date dataRecebimento;

	@Column(columnDefinition = "BIT", length = 1)
	private boolean recebido;

	@Column(name="valor_recebimento")
	private double valorRecebimento;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	public Recebimento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public boolean getRecebido() {
		return this.recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}

	public double getValorRecebimento() {
		return this.valorRecebimento;
	}

	public void setValorRecebimento(double valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}