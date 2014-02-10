package com.projetos.controle.tela.controller.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.ItemCombo;
import com.projetos.controle.tela.base.PropertiesLoader;
import com.projetos.controle.tela.controller.TelaPrincipalController;
import com.projetos.controle.tela.util.CelulaFactory;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.reflection.BeanUtil;
import com.projetos.controle_util.validacao.MensagemValidacao;

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

    protected MensagemValidacao mensagem;
    
    @Autowired
    protected TelaPrincipalController telaPrincipalController;

    public void imprimir() {

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
    protected abstract void remover();

	public void bindBeanToForm() {
		MirrorList<Field> campos = new Mirror().on(this.getClass()).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(CampoTela.class)) {
				Object campo = new Mirror().on(this).get().field(field);
				String bean = field.getAnnotation(CampoTela.class).bean();
				Object value = BeanUtil.getPropriedade(entidadeForm, bean);
				
				if (campo instanceof TextField && value != null) {
					((TextField) campo).setText(value.toString());
				} else if (campo instanceof RadioButton  && value != null) {
					((RadioButton) campo).setSelected((Boolean)value);
				}
				
			}
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

			campo.setOnKeyPressed(new MaskedTextFieldEventHandler(12, "[[\\d]{3}.]"));
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
			String typedText = paramT.getText();
			if (text.length() >= maxLength) {
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
