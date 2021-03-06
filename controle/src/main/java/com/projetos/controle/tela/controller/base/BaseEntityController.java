package com.projetos.controle.tela.controller.base;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.ConfiguracaoBeanTela;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.TipoCampo;
import com.projetos.controle.tela.util.CelulaFactory;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.conversao.NumberUtil;
import com.projetos.controle_util.reflection.BeanUtil;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * Classe com o comportamento básico de tela extendido da AbstractController e
 * com comportamento adicionado para manutenção de uma entidade, .
 * 
 * @author Rafael
 * @param <T>
 *            Entidade à qual a controller realizará manutenção
 */
@SuppressWarnings("restriction")
public abstract class BaseEntityController<T extends Entidade> extends AbstractController {

	protected T entidadeForm;

	protected ObservableList<T> listaEntidades;

	@Autowired
	protected ConfiguracaoBeanTela configuracaoBeanTela;

	public void exibirPopupConfirmacao() {
		exibirPopupConfirmacao(new DefaultConfirmHandler());
	}

	public void exibirPopupConfirmacao(EventHandler<ActionEvent> confirmHandler) {
		exibirPopupConfirmacao(confirmHandler, null);
	}

	public void exibirPopupConfirmacao(EventHandler<ActionEvent> confirmHandler, EventHandler<ActionEvent> cancelHandler) {
		popupConfirmacaoController.setConfirmHandler(confirmHandler);
		popupConfirmacaoController.setCancelHandler(cancelHandler);
		popupConfirmacaoController.setMensagem(propertiesLoader.getProperty("cadastro.confirma_remocao"));

		Stage stage = telaPrincipalController.exibirPopupConfirmacao();
		popupConfirmacaoController.setStage(stage);
	}

	public class DefaultConfirmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			try {
				remover();
				fecharPopupConfirmacao();
			} catch (ValidacaoException e) {
				tratarErroValidacao(e);
			}
		}
	}

	protected void fecharPopupConfirmacao() {
		popupConfirmacaoController.close();
	}

	/**
	 * Preenche os dados da tela com os dados do bean (entidadeForm)
	 */
	@SuppressWarnings("unchecked")
	protected void bindBeanToForm() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(CampoTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(CampoTela.class).bean();
				Object value = BeanUtil.getPropriedade(entidadeForm, bean);

				if (campo instanceof TextField) {

					preencherTextField((TextField) campo, value);

				} else if (campo instanceof TextArea) {
					preencherTextArea((TextArea) campo, value);

				} else if (campo instanceof Label) {
					preencherLabel((Label) campo, value);

				} else if (campo instanceof RadioButton && value != null) {
					((RadioButton) campo).setSelected((Boolean) value);

				} else if (campo instanceof DatePicker && value != null) {
					LocalDate localDate = fromDateToLocalDate((Date) value);
					((DatePicker) campo).setValue(localDate);

				} else if (campo instanceof ComboBox && value != null) {
					ItemCombo<?> item = new ItemCombo<>(null, value);
					((ComboBox<ItemCombo<?>>) campo).getSelectionModel().select(item);

				} else if (campo instanceof PasswordField && value != null) {
					preencherTextField((PasswordField) campo, value);

				}

			}
		}
	}

	private void preencherTextArea(TextArea textArea, Object value) {
		if (value != null) {
			textArea.setText(value.toString());
		} else {
			textArea.setText("");
		}
	}

	private void preencherLabel(Label label, Object value) {
		if (value != null) {
			if (value instanceof Date) {
				String valor = DateUtil.convertDateToString((Date) value);
				label.setText(valor);
			} else if (value instanceof Double) {
				String valor = NumberUtil.convertDoubleToString((Double) value);
				label.setText(valor);
			} else {
				label.setText(value.toString());
			}

		} else {
			label.setText(null);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Filtro> getCamposFiltro() {
		List<Filtro> filtros = new ArrayList<>();
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(FiltroTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				FiltroTela config = field.getAnnotation(FiltroTela.class);
				String beanName = config.campo();

				if (campo instanceof TextField) {
					String valor = ((TextField) campo).getText();
					if (valor != null && !valor.equals("")) {
						Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), valor);
						filtros.add(filtro);
					}

				} else if (campo instanceof RadioButton) {
					Boolean valor = ((RadioButton) campo).isSelected();
					Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), valor);
					filtros.add(filtro);

				} else if (campo instanceof DatePicker) {
					LocalDate localDate = ((DatePicker) campo).getValue();
					if (localDate != null) {
						Date valor = fromLocalDateToDate(localDate);
						Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), valor);
						filtros.add(filtro);
					}

				} else if (campo instanceof ComboBox) {
					Object valor = ((ComboBox<ItemCombo<?>>) campo).getSelectionModel().getSelectedItem();
					ItemCombo<?> item = (ItemCombo<?>) valor;
					if (valor != null) {
						Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), item.getValor());
						filtros.add(filtro);
					}

				} else if (campo instanceof TableView) {
					ObservableList<? extends Entidade> lista = ((TableView<? extends Entidade>) campo).getSelectionModel().getSelectedItems();
					if (lista != null && !lista.isEmpty()) {
						Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), lista);
						filtros.add(filtro);
					}
				}
			}
		}

		return filtros;
	}

	private void preencherTextField(TextField textField, Object value) {
		if (value != null) {
			if (value instanceof Date) {
				String valor = DateUtil.convertDateToString((Date) value);
				textField.setText(valor);
			} else if (value instanceof Double) {
				String valor = NumberUtil.convertDoubleToString((Double) value);
				textField.setText(valor);
			} else {
				textField.setText(value.toString());
			}

		} else {
			textField.setText(null);
		}
	}

	/**
	 * Preenche os dados do bean (entidadeForm) com os dados da tela 
	 */
	protected void bindFormToBean() throws ValidacaoException {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();

		for (Field field : campos) {

			if (field.isAnnotationPresent(CampoTela.class)) {

				Object campo = new Mirror().on(this).get().field(field);

				CampoTela annotation = field.getAnnotation(CampoTela.class);
				String beanName = annotation.bean();


				if (campo instanceof TextField) {
					validarTextField(campo, annotation, beanName);

				} else if (campo instanceof TextArea) {
					BeanUtil.setPropriedade(entidadeForm, beanName, ((TextArea) campo).getText());

				/*} else if (campo instanceof DecimalNumberField) {
					BeanUtil.setPropriedade(entidadeForm, beanName, ((DecimalNumberField) campo).getFormattedText());*/

				} else if (campo instanceof RadioButton) {
					BeanUtil.setPropriedade(entidadeForm, beanName, ((RadioButton) campo).isSelected());

				} else if (campo instanceof DatePicker) {
					LocalDate localDate = ((DatePicker) campo).getValue();
					Date date = fromLocalDateToDate(localDate);

					if (date == null) {
						BeanUtil.setNull(entidadeForm, beanName);
					} else {
						BeanUtil.setPropriedade(entidadeForm, beanName, date);
					}

				} else if (campo instanceof ComboBox) {
					@SuppressWarnings("unchecked")
					Object valor = ((ComboBox<ItemCombo<?>>) campo).getSelectionModel().getSelectedItem();
					ItemCombo<?> item = (ItemCombo<?>) valor;

					if (valor != null) {
						BeanUtil.setPropriedade(entidadeForm, beanName, item.getValor());

					}

				}
			}
		}
	}

	private void validarTextField(Object campo, CampoTela annotation, String beanName) throws ValidacaoException {
		try {
			String text = ((TextField) campo).getText();
			validar(annotation, text);
			BeanUtil.setPropriedade(entidadeForm, beanName, text);
			if (text == null || text.equals("")) {
				if (annotation.tipoCampo() == TipoCampo.INTEIRO) {
					((TextField) campo).setText("0");
					
				} else if (annotation.tipoCampo() == TipoCampo.MOEDA) {
					((TextField) campo).setText("0,00");
					
				}
			}
		} catch (ValidacaoException e) {
			((TextField) campo).setText("");
			((TextField) campo).requestFocus();
			throw e;
		}
	}

	public void validar(CampoTela annotation, Object campo) throws ValidacaoException {

		Matcher matcher = null;
		if (campo == null) {
			return;
		} else if (annotation.tipoCampo() == TipoCampo.MOEDA) {

			matcher = getMatcherDouble(campo);


		} else if (annotation.tipoCampo() == TipoCampo.INTEIRO) {

			matcher = getMatcherInteiro(campo);
			
		} else {
			return;
		}

		if (!matcher.matches()) {
			String nome = annotation.nome();
			throw new ValidacaoException("cadastro.campo_com_valor_invalido", nome);
		}

	}

	private Matcher getMatcherDouble(Object campo) {
		String string = campo.toString();
		return getMatcher("(\\d*?)(\\,\\d{1,2})?", string);
	}
	
	private Matcher getMatcherInteiro(Object campo) {
		String string = campo.toString();
		return getMatcher("(\\d*?)", string);
	}
	
	private Matcher getMatcher(String patternString, String campo) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(campo);
		return matcher;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void loadColumns() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(Coluna.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(Coluna.class).bean();

				CelulaFactory<T, ?> celulaFactory = new CelulaFactory<>(bean);
				if (campo instanceof TableColumn) {
					((TableColumn) campo).setCellValueFactory(celulaFactory);
				} else {
					throw new IllegalArgumentException("Field: " + campo + " on class: " + this.getClass() + " must be of type TableColumn.");
				}
			}
		}
	}

	protected void mascararCampos() {

		List<Field> camposMascara = getCamposMascara();
		for (Field field : camposMascara) {
			TextField campo = (TextField) new Mirror().on(this).get().field(field);

			if (campo == null) {
				throw new RuntimeException("Campo com valor nulo: " + field.getName());
			}

			/* campo.setOnKeyReleased(new MaskedTextFieldEventHandler(12)); */
		}

	}

	/*
	 * public class MaskedTextFieldEventHandler implements
	 * EventHandler<KeyEvent> {
	 * 
	 * private int maxLength; private String format;
	 * 
	 * public MaskedTextFieldEventHandler(int maxLength) { this.maxLength =
	 * maxLength; }
	 * 
	 * public MaskedTextFieldEventHandler(int maxLenth, String format) {
	 * this(maxLenth); this.format = format; }
	 * 
	 * @Override public void handle(KeyEvent paramT) { TextField textField =
	 * (TextField) paramT.getSource(); String text = textField.getText();
	 * 
	 * if (text == null) { return; }
	 * 
	 * String typedText = paramT.getText(); if (text.length() >= maxLength) {
	 * textField.setText(text.substring(0, maxLength)); return; } else if
	 * (format != null) { String valorMascarado = maskedInput(text + typedText);
	 * textField.setText(valorMascarado); } }
	 * 
	 * private String maskedInput(String texto) { return
	 * texto.replaceAll(format, texto); }
	 * 
	 * }
	 */

	/**
	 * Convert de LocalDate para Date
	 * 
	 * @param localDate
	 * @return Date
	 */
	protected Date fromLocalDateToDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		cal.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		cal.set(Calendar.YEAR, localDate.getYear());

		return cal.getTime();
	}

	/**
	 * Convert de Date para LocalDate
	 * 
	 * @param date
	 * @return LocalDate
	 */
	private LocalDate fromDateToLocalDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		LocalDate localDate = LocalDate.of(year, month + 1, dayOfMonth);
		return localDate;
	}

	private List<Field> getCamposMascara() {
		List<Field> camposAnotados = BeanUtil.getCamposAnotados(this.getClass(), CampoTela.class);
		List<Field> camposMascara = new ArrayList<>();

		for (Field field : camposAnotados) {
			if (field.getType() == TextField.class) {
				camposMascara.add(field);
			}
		}

		return camposMascara;
	}

	protected String getDataAgora() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		int dia = localDate.getDayOfMonth();
		int mes = localDate.getMonthValue();
		int ano = localDate.getYear();
		int hora = localTime.getHour();
		int minuto = localTime.getMinute();
		int segundo = localTime.getSecond();
		return ano + "_" + mes + "_" + dia + "_" + hora + "_" + minuto + "_" + segundo;
	}

	@Bean(name = "validator")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy
	protected Validator getValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

	protected abstract EntidadeService<T> getEntidadeService();

	public abstract void remover() throws ValidacaoException;

	public T getEntidadeForm() {
		return entidadeForm;
	}

	public void setEntidadeForm(T entidadeForm) {
		this.entidadeForm = entidadeForm;
	}

	public ObservableList<T> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(ObservableList<T> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

}
