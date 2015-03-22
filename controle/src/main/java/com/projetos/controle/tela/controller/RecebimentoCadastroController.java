package com.projetos.controle.tela.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.TipoCampo;
import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle_entities.Pedido;
import com.projetos.controle_entities.Recebimento;
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
public class RecebimentoCadastroController extends BaseCadastroController<Recebimento> {

	@Autowired
	private RecebimentoService recebimentoService;

	@Autowired
	private PedidoService pedidoService;

	@FXML
	@CampoTela(bean = "dataRecebimento")
	private DatePicker dataRecebimento;

	@FXML
	@CampoTela(bean = "pedido.valorTotal", tipoCampo = TipoCampo.MOEDA, nome = "Valor Faturado")
	private Label valorFaturado;

	@FXML
	@CampoTela(bean = "percentualComissao", tipoCampo = TipoCampo.MOEDA, nome = "Porcentagem")
	private TextField porcentagem;

	@FXML
	@CampoTela(bean = "valorRecebimento", tipoCampo = TipoCampo.MOEDA, nome = "Valor Comissão")
	private TextField valorComissao;

	@FXML
	@CampoTela(bean = "recebido")
	private ComboBox<ItemCombo<Boolean>> recebido;

	@FXML
	private TableView<Recebimento> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

		this.valorComissao.focusedProperty().addListener(new ComissaoChangeListener());

		List<ItemCombo<Boolean>> lista = new ArrayList<>();
		ItemCombo<Boolean> ativo = new ItemCombo<>("SIM", true);
		ItemCombo<Boolean> naoAtivo = new ItemCombo<>("NÃO", false);
		lista.add(ativo);
		lista.add(naoAtivo);

		ObservableList<ItemCombo<Boolean>> itens = FXCollections.observableArrayList(lista);
		recebido.setItems(itens);
		bindBeanToForm();
	}

	@Override
	protected void salvar() throws ValidacaoException {
		super.salvar();
		pedidoService.salvar(entidadeForm.getPedido());
		Pedido pedido = pedidoService.findById(entidadeForm.getPedido().getId());
		entidadeForm.setPedido(pedido);
//		pedidoService.salvar(entidadeForm.getPedido());
		
		PedidoCadastroController pedidoCadastroController = ApplicationConfig.getBean(PedidoCadastroController.class);
		pedidoCadastroController.setEntidadeForm(pedido);
		pedidoCadastroController.salvarSemMensagem();
	}

	public class ComissaoChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
			
			// Handling only when focus is out.
			String text = valorComissao.getText();
			if (!focusIn && text != null && !text.equals("")) {
				try {
					bindFormToBean();
					BigDecimal cem = new BigDecimal(100);
					BigDecimal vComissao = new BigDecimal(text.replace(",", "."));
//					vComissao = vComissao.setScale(2, BigDecimal.ROUND_FLOOR);
					BigDecimal vTotal = BigDecimal.valueOf(entidadeForm.getPedido().getValorTotal());
//					vTotal = vTotal.setScale(2, BigDecimal.ROUND_FLOOR);
					BigDecimal valorPorcentagem = cem.multiply(vComissao).setScale(2, BigDecimal.ROUND_FLOOR)
							.divide(vTotal, BigDecimal.ROUND_FLOOR);

					valorPorcentagem = valorPorcentagem.setScale(2, BigDecimal.ROUND_FLOOR);
					entidadeForm.setPercentualComissao(valorPorcentagem.doubleValue());
					bindBeanToForm();
				} catch (ValidacaoException e) {
					tratarErroValidacao(e);
				}
			}
		}
	}

	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

}