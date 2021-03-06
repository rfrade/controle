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
@SuppressWarnings("restriction")
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
	@CampoTela(bean = "valorFaturado", tipoCampo = TipoCampo.MOEDA, nome = "Valor Faturado")
	private TextField valorFaturado;

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

	// Criado para evitar que o fato de desconsiderar valores depois de 2 casas
	// decimais não resulte em uma diminuição constante do percentual de comssão
	private BigDecimal percentualAnterior;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);

//		this.valorComissao.focusedProperty().addListener(new ComissaoChangeListener());
		this.porcentagem.focusedProperty().addListener(new PercentualChangeListener());
		this.valorFaturado.focusedProperty().addListener(new ValorFaturadoChangeListener());

		List<ItemCombo<Boolean>> lista = new ArrayList<>();
		ItemCombo<Boolean> ativo = new ItemCombo<>("SIM", true);
		ItemCombo<Boolean> naoAtivo = new ItemCombo<>("NÃO", false);
		lista.add(ativo);
		lista.add(naoAtivo);

		ObservableList<ItemCombo<Boolean>> itens = FXCollections.observableArrayList(lista);
		recebido.setItems(itens);
		
		percentualAnterior = BigDecimal.valueOf(entidadeForm.getPercentualComissao());
		
		bindBeanToForm();
	}

	@Override
	protected void salvar() throws ValidacaoException {
		super.salvar();
		pedidoService.salvar(entidadeForm.getPedido());
//		Pedido pedido = pedidoService.findById(entidadeForm.getPedido().getId());
//		entidadeForm.setPedido(pedido);
//		pedidoService.salvar(entidadeForm.getPedido());
		
		PedidoCadastroController pedidoCadastroController = ApplicationConfig.getBean(PedidoCadastroController.class);
		pedidoCadastroController.setEntidadeForm(entidadeForm.getPedido());
		//pedidoCadastroController.salvarSemMensagem();
	}

	/**
	 * Se alterar o percentual, tem que recalcular o valor do recebimento
	 */
	public class PercentualChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0,
				Boolean arg1, Boolean focusIn) {

			// Handling only when focus is out.
			String text = valorComissao.getText();
			if (!focusIn && text != null && !text.equals("")) {
				try {
					bindFormToBean();

					if (percentualAnterior.doubleValue() != entidadeForm.getPercentualComissao()) {
						calcularValorComissao();
					}

					bindBeanToForm();
				} catch (ValidacaoException e) {
					tratarErroValidacao(e);
				}
			}

		}

	}
		
	/*public class ComissaoChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0,
				Boolean arg1, Boolean focusIn) {

			// Handling only when focus is out.
			String text = valorComissao.getText();
			if (!focusIn && text != null && !text.equals("")) {
				try {
					bindFormToBean();
					alterarValorComissao(focusIn, text);
					bindBeanToForm();
				} catch (ValidacaoException e) {
					tratarErroValidacao(e);
				}
			}

		}

		private void alterarValorComissao(Boolean focusIn, String text) {
			BigDecimal cem = new BigDecimal(100);
			BigDecimal vComissao = new BigDecimal(text.replace(",", "."));
			BigDecimal vTotal = BigDecimal.valueOf(entidadeForm.getValorFaturado()).setScale(2,	BigDecimal.ROUND_FLOOR);;
			
			BigDecimal valorPorcentagem = cem.multiply(vComissao).setScale(2, BigDecimal.ROUND_FLOOR)
					.divide(vTotal, BigDecimal.ROUND_FLOOR);

			valorPorcentagem = valorPorcentagem.setScale(2,	BigDecimal.ROUND_FLOOR);
			
			BigDecimal diferencaPercentuais = percentualAnterior.subtract(valorPorcentagem);
			
			// Só altera o percentual se a alteração do valor tiver ocorrido.
			// Devido ao "setScale(2,BigDecimal.ROUND_FLOOR)" o valor da porcentagem
			// variava 0,01 a cada recálculo.
			if (diferencaPercentuais.doubleValue() > 0.02d || diferencaPercentuais.doubleValue() < -0.02d) {
				percentualAnterior = valorPorcentagem;
				entidadeForm.setPercentualComissao(valorPorcentagem.doubleValue());
			}

		}
	}*/

	public class ValorFaturadoChangeListener implements ChangeListener<Boolean> {
		
		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean focusIn) {
			
			// Handling only when focus is out.
			String text = valorComissao.getText();
			if (!focusIn && text != null && !text.equals("")) {
				try {
					bindFormToBean();

					// Calcula a comissão a partir do novo valor faturado
					calcularValorComissao();

					bindBeanToForm();
				} catch (ValidacaoException e) {
					tratarErroValidacao(e);
				}
			}
		}
	}

	private void calcularValorComissao() {
		BigDecimal valorFaturado = BigDecimal.valueOf(entidadeForm.getValorFaturado());
		BigDecimal cem = new BigDecimal(100);
		BigDecimal percentualComissao = BigDecimal.valueOf(entidadeForm.getPercentualComissao());
		BigDecimal novoValorComissao = valorFaturado.setScale(2, BigDecimal.ROUND_FLOOR).multiply(percentualComissao)
				.divide(cem, BigDecimal.ROUND_FLOOR);

		novoValorComissao = novoValorComissao.setScale(2,	BigDecimal.ROUND_FLOOR);
		entidadeForm.setValorRecebimento(novoValorComissao.doubleValue());
	}
	
	@Override
	protected EntidadeService<Recebimento> getEntidadeService() {
		return recebimentoService;
	}

}