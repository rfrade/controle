package com.projetos.controle.tela.controller.base;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.ConfiguracaoBeanTela;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.PropertiesLoader;
import com.projetos.controle.tela.controller.PopupConfirmacaoController;
import com.projetos.controle.tela.controller.PopupMensagemController;
import com.projetos.controle.tela.controller.TelaPrincipalController;
import com.projetos.controle.tela.util.CelulaFactory;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.reflection.BeanUtil;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseController<T extends Entidade> extends AbstractController implements Initializable {

    protected T entidadeForm;
    protected ObservableList<T> listaEntidades;
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	protected PropertiesLoader propertiesLoader;

    @Autowired
    protected TelaPrincipalController telaPrincipalController;

    @Autowired
    private PopupMensagemController popupMensagemController;
    
    @Autowired
    private PopupConfirmacaoController popupConfirmacaoController;

    @Autowired
    protected ConfiguracaoBeanTela configuracaoBeanTela;

    protected void exibirMensagem(String mensagem) {
    	popupMensagemController.setMensagem(propertiesLoader.getProperty(mensagem));
    	telaPrincipalController.exibirPopupMensagem();
    }
    
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
			remover();
			fecharPopupConfirmacao();
		}
	}

	protected void fecharPopupConfirmacao() {
		popupConfirmacaoController.close();
	}

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
				} else if (campo instanceof ChoiceBox) {
					@SuppressWarnings("unchecked")
					Object valor = ((ChoiceBox<ItemCombo<?>>) campo).getSelectionModel().getSelectedItem();
					ItemCombo<?> item = (ItemCombo<?>) valor;
					if (valor != null) {
						Filtro filtro = new Filtro(beanName, config.tipo(), config.comparador(), config.operador(), item.getValor());
						filtros.add(filtro);
					}
				}
			}
		}
		
		return filtros;
    }

    protected void tratarErro(Exception e) {
    	log.error(e.getMessage(), e);
    }

    protected abstract EntidadeService<T> getEntidadeService();
    public abstract void remover();

    @SuppressWarnings("unchecked")
	public void bindBeanToForm() {
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
					((RadioButton) campo).setSelected((Boolean)value);
				} else if (campo instanceof ChoiceBox && value != null) {
					ItemCombo<?> item = new ItemCombo<>(null, value);
					((ChoiceBox<ItemCombo<?>>) campo).getSelectionModel().select(item);
				}
				
			}
		}
	}

	private void preencherTextField(TextField textField, Object value) {
		if (value != null) {
			textField.setText(value.toString());
		} else {
			textField.setText(null);
		}
	}
	
	private void preencherTextArea(TextArea textArea, Object value) {
		if (value != null) {
			textArea.setText(value.toString());
		} else {
			textArea.setText(null);
		}
	}
	
	private void preencherLabel(Label label, Object value) {
		if (value != null) {
			label.setText(value.toString());
		} else {
			label.setText(null);
		}
	}

	protected void bindFormToBean() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(CampoTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String beanName = field.getAnnotation(CampoTela.class).bean();
				
				if (campo instanceof TextField) {
					BeanUtil.setPropriedade(entidadeForm, beanName, ((TextField) campo).getText());
				} else if (campo instanceof RadioButton) {
					BeanUtil.setPropriedade(entidadeForm, beanName, ((RadioButton) campo).isSelected());
				} else if (campo instanceof ChoiceBox) {
					@SuppressWarnings("unchecked")
					Object valor = ((ChoiceBox<ItemCombo<?>>) campo).getSelectionModel().getSelectedItem();
					ItemCombo<?> item = (ItemCombo<?>) valor;
					BeanUtil.setPropriedade(entidadeForm, beanName, item.getValor());
				}
			}
		}
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

			campo.setOnKeyReleased(new MaskedTextFieldEventHandler(12));
		}

	}

	public class MaskedTextFieldEventHandler implements EventHandler<KeyEvent> {

		private int maxLength;
		private String format;
		
		public MaskedTextFieldEventHandler(int maxLength) {
			this.maxLength = maxLength;
		}

		public MaskedTextFieldEventHandler(int maxLenth, String format) {
			this(maxLenth);
			this.format = format;
		}

		@Override
		public void handle(KeyEvent paramT) {
			TextField textField = (TextField) paramT.getSource();
			String text = textField.getText();

			if (text == null) {
				return;
			}
			
			String typedText = paramT.getText();
			if (text.length() >= maxLength) {
				textField.setText(text.substring(0, maxLength));
				return;
			} else if (format != null) {
				String valorMascarado = maskedInput(text + typedText);
				textField.setText(valorMascarado);
			}
		}

		private String maskedInput(String texto) {
			return texto.replaceAll(format, texto);
		}

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

	public PropertiesLoader getPropertiesLoader() {
		return propertiesLoader;
	}

	public void setPropertiesLoader(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;
	}

}
