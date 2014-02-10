package com.projetos.controle.tela.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Vendedor;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.PedidoService;

@Controller
@Lazy
public class PedidoCadastroController extends BaseCadastroController<Pedido> {

	@Autowired
	private PedidoService pedidoService;

	@FXML
	@CampoTela(bean = "id")
	private Label labelNumeroPedido;

	@FXML
	@CampoTela(bean = "fornecedor.firma")
	private ChoiceBox<ItemCombo<Fornecedor>> fornecedor;

	@FXML
	@CampoTela(bean = "cliente.firma")
	private Label cliente;

	@FXML
	@CampoTela(bean = "transportador")
	private TextField transportador;

	@FXML
	@CampoTela(bean = "condicoes")
	private TextField condicoes;

	@FXML
	@CampoTela(bean = "cobranca")
	private TextField cobranca;

	@FXML
	@CampoTela(bean = "comissao")
	private TextField comissao;

	@FXML
	@CampoTela(bean = "entrega")
	private TextField entrega;

	@FXML
	@CampoTela(bean = "vendedor.nome")
	private ChoiceBox<ItemCombo<Vendedor>> vendedor;

	@FXML
	@CampoTela(bean = "colecao")
	private TextField colecao;

	@FXML
	@CampoTela(bean = "observacao")
	private TextArea observacao;
	
	@FXML
	@CampoTela(bean = "desconto1")
	private TextField desconto1;
	
	@FXML
	@CampoTela(bean = "desconto2")
	private TextField desconto2;
	
	@FXML
	@CampoTela(bean = "desconto3")
	private TextField desconto3;
	
	@FXML
	@CampoTela(bean = "desconto4")
	private TextField desconto4;
	
	@FXML
	@CampoTela(bean = "descontoTotal")
	private TextField descontoTotal;

	public void calcularDesconto() {

	}

	public void procurarCliente() {

	}

	@Override
	protected EntidadeService<Pedido> getEntidadeService() {
		return pedidoService;
	}

	public Label getLabelNumeroPedido() {
		return labelNumeroPedido;
	}

	public void setLabelNumeroPedido(Label labelNumeroPedido) {
		this.labelNumeroPedido = labelNumeroPedido;
	}

	public ChoiceBox<ItemCombo<Fornecedor>> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ChoiceBox<ItemCombo<Fornecedor>> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Label getCliente() {
		return cliente;
	}

	public void setCliente(Label cliente) {
		this.cliente = cliente;
	}

	public TextField getTransportador() {
		return transportador;
	}

	public void setTransportador(TextField transportador) {
		this.transportador = transportador;
	}

	public TextField getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(TextField condicoes) {
		this.condicoes = condicoes;
	}

	public TextField getCobranca() {
		return cobranca;
	}

	public void setCobranca(TextField cobranca) {
		this.cobranca = cobranca;
	}

	public TextField getComissao() {
		return comissao;
	}

	public void setComissao(TextField comissao) {
		this.comissao = comissao;
	}

	public TextField getEntrega() {
		return entrega;
	}

	public void setEntrega(TextField entrega) {
		this.entrega = entrega;
	}

	public ChoiceBox<ItemCombo<Vendedor>> getVendedor() {
		return vendedor;
	}

	public void setVendedor(ChoiceBox<ItemCombo<Vendedor>> vendedor) {
		this.vendedor = vendedor;
	}

	public TextField getColecao() {
		return colecao;
	}

	public void setColecao(TextField colecao) {
		this.colecao = colecao;
	}

	public TextArea getObservacao() {
		return observacao;
	}

	public void setObservacao(TextArea observacao) {
		this.observacao = observacao;
	}

	public TextField getDesconto1() {
		return desconto1;
	}

	public void setDesconto1(TextField desconto1) {
		this.desconto1 = desconto1;
	}

	public TextField getDesconto2() {
		return desconto2;
	}

	public void setDesconto2(TextField desconto2) {
		this.desconto2 = desconto2;
	}

	public TextField getDesconto3() {
		return desconto3;
	}

	public void setDesconto3(TextField desconto3) {
		this.desconto3 = desconto3;
	}

	public TextField getDesconto4() {
		return desconto4;
	}

	public void setDesconto4(TextField desconto4) {
		this.desconto4 = desconto4;
	}

	public TextField getDescontoTotal() {
		return descontoTotal;
	}

	public void setDescontoTotal(TextField descontoTotal) {
		this.descontoTotal = descontoTotal;
	}

}
