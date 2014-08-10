package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.TipoCampo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Fornecedor;
import com.projetos.controle_entities.Produto;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.FornecedorService;
import com.projetos.controle_negocio.service.base.ProdutoService;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class ProdutoCadastroController extends BaseCadastroController<Produto> {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@FXML
	@CampoTela(bean = "referencia")
	private TextField referencia;

	@FXML
	@CampoTela(bean = "valorUnitario", tipoCampo = TipoCampo.MOEDA, nome = "Valor Venda")
	private TextField valorVenda;

	@FXML
	@CampoTela(bean = "descricao")
	private TextField descricao;

	@FXML
	@CampoTela(bean = "tamanho")
	private TextField tamanho;

	@FXML
	@CampoTela(bean = "fornecedor")
	private ComboBox<ItemCombo<Fornecedor>> fornecedor;

	@Override
	protected void salvar() throws ValidacaoException {
		super.salvar();
		produtoService.desativarProdutos(entidadeForm.getReferencia(), entidadeForm.getFornecedor());
		entidadeForm.setAtivo(true);
		entidadeForm = produtoService.salvar(entidadeForm);
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		Filtro filtro = new Filtro("ativo", TipoFiltro.BOOLEAN, Comparador.EQUALS, true);
		List<Fornecedor> fornecedores = fornecedorService.filtrar(filtro);
		
		ObservableList<ItemCombo<Fornecedor>> itensFornecedor = ItemCombo.novaListaCombo(fornecedores, "firma");
		fornecedor.setItems(itensFornecedor);
	}

	@Override
	protected Produto novaEntidadeForm() {
		Produto produto = super.novaEntidadeForm();
		produto.setAtivo(true);
		return produto;
	}
	
	@Override
	protected EntidadeService<Produto> getEntidadeService() {
		return produtoService;
	}

}