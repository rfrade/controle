package com.projetos.controle.tela.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Recebimento;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_negocio.service.base.PedidoService;
import com.projetos.controle_negocio.service.base.RecebimentoService;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * 
 * @author Rafael
 */
@Controller
@Lazy
public class RecebimentoListaController extends BaseListController<Recebimento> {

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private RecebimentoCadastroController recebimentoCadastroController;

	@Autowired
	private PedidoService pedidoService;
	
	private Pedido pedido;

	@FXML
	@Coluna(bean = "pedido.id")
	private TableColumn<Recebimento, String> colunaPedido;
	
	@FXML
	@Coluna(bean = "pedido.vendedor.nome")
	private TableColumn<Recebimento, String> colunaVendedor;
	
	@FXML
	@Coluna(bean = "pedido.fornecedor.firma")
	private TableColumn<Recebimento, String> colunaFornecedor;
	
	@FXML
	@Coluna(bean = "valorRecebimento")
	private TableColumn<Recebimento, String> colunaValorRecebimento;
	
	@FXML
	@Coluna(bean = "recebido")
	private TableColumn<Recebimento, String> colunaRecebido;
	
	@FXML
	@Coluna(bean = "dataRecebimento")
	private TableColumn<Recebimento, Date> colunaDataRecebimento;
	
	@FXML
	private TableView<Recebimento> tabela;

	@Override
	protected List<Filtro> getCamposFiltro() {
		Filtro filtro = new Filtro("pedido.id", TipoFiltro.INTEGER, Comparador.EQUALS);
		return Arrays.asList(filtro);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
	}

	@Override
	protected List<Filtro> getFiltrosFixos() {
		Filtro filtroPedido = new Filtro("pedido.id", TipoFiltro.INTEGER, Comparador.EQUALS, pedido.getId());
		List<Filtro> lista = new ArrayList<>();
		lista.add(filtroPedido);
		return lista;
	}

	@Override
	public void exibirTelaCadastro() {
		telaPrincipalController.exibirTelaRecebimentoCadastro();
		super.exibirTelaCadastro();
	}

	@Override
	public void prepararInclusao() {
		Recebimento efCadastro = new Recebimento();
		efCadastro.setPedido(pedido);
		efCadastro.setPercentualComissao(pedido.getComissao());
		efCadastro.setRecebido(false);
		efCadastro.setValorRecebimento(getValorComissao());

		getBaseCadastroController().setEntidadeForm(efCadastro);
		exibirTelaCadastro();
	}

	private double getValorComissao() {
		BigDecimal valorTotal = new BigDecimal(String.valueOf(pedido.getValorTotal()));
		BigDecimal comissao =  new BigDecimal(String.valueOf(pedido.getComissao()));
		BigDecimal cem = new BigDecimal(100);
		
		return valorTotal.multiply(comissao).divide(cem).doubleValue();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}

	public void imprimir() {

	}

	@Override
	public void remover() {
		try {
			entidadeForm = getTabela().getSelectionModel().getSelectedItem();
			if (entidadeForm == null) {
				throw new ValidacaoException("cadastro.selecione_um_registro_para_remover");
			}
			entidadeForm = pedido.removeRecebimento(entidadeForm);
			pedido = pedidoService.salvar(pedido);
//			getEntidadeService().remover(entidadeForm);
			getTabela().getItems().remove(entidadeForm);

			PedidoCadastroController pedidoCadastroController = ApplicationConfig.getBean(PedidoCadastroController.class);
			pedidoCadastroController.setEntidadeForm(pedido);
			//pedidoCadastroController.salvarSemMensagem();

			exibirMensagem("cadastro.removido_com_sucesso");
			
			//pedido = pedidoService.consultarPedido(pedido.getId());
			//pedido = pedidoService.salvar(pedido);
			// Pedido pedido = entidadeForm.getPedido();
			// entidadeForm = getEntidadeService().salvar(entidadeForm);
		} catch (ValidacaoException e) {
			tratarErroValidacao(e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			exibirMensagemNaoMapeada("Não foi possível excluir: "
					+ e.getMessage());
		}}

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

	@Override
	public TableView<Recebimento> getTabela() {
		return tabela;
	}

	@Override
	protected BaseCadastroController<Recebimento> getBaseCadastroController() {
		return recebimentoCadastroController;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}