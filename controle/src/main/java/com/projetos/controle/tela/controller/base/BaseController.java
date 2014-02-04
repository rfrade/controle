package com.projetos.controle.tela.controller.base;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.CampoTela;
import com.projetos.controle.tela.base.Coluna;
import com.projetos.controle.tela.base.FiltroTela;
import com.projetos.controle.tela.base.PropertiesLoader;
import com.projetos.controle.tela.controller.ClienteController.ItemCombo;
import com.projetos.controle.tela.controller.TelaPrincipalController;
import com.projetos.controle.tela.util.CelulaFactory;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.reflection.BeanUtil;
import com.projetos.controle_util.validacao.MensagemValidacao;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 * @author Rafael
 * @param <T> Entidade à qual a controller realizará manutenção
 */
public abstract class BaseController<T extends Entidade> extends AbstractController implements Initializable {

    protected T entidadeForm;
    protected ObservableList<T> listaEntidades;
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private PropertiesLoader propertiesLoader;

    protected MensagemValidacao mensagem;
    
    @Autowired
    protected TelaPrincipalController telaPrincipalController;

    public abstract TableView<T> getTabela();
    
    public void pesquisar() {
    	List<Filtro> filtros = getCamposFiltro();
    	List<T> listaTabela = getEntidadeService().filtrar(filtros);
    	loadTable(listaTabela);
    }

    public void imprimir() {

	}
    
    private List<Filtro> getCamposFiltro() {
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

    @Override
	public void initialize(URL url, ResourceBundle resource) {
		loadTable(getEntidadeService().listar());
	}

	protected void loadTable(List<T> lista) {
		listaEntidades = FXCollections.observableArrayList(lista);
		getTabela().setItems(listaEntidades);
		loadColumns();
	}

	public void prepararAlteracao(MouseEvent event) {
	    if (event.getClickCount() > 1) {
	    	T selectedItem = getTabela().getSelectionModel().getSelectedItem();
	    	if (selectedItem != null) {
	    		exibirTelaLista();
	    		entidadeForm = selectedItem;
	    		bindBeanToForm();
	    	}
	    }
	}

	@SuppressWarnings("unchecked")
	public void prepararInclusao() {
		Class<?> classe = new Mirror().on(this.getClass()).reflect().parentGenericType().atPosition(0);
		entidadeForm = (T) new Mirror().on(classe).invoke().constructor().bypasser();
		exibirTelaCadastro();
	}

	public abstract void exibirTelaCadastro();
	public abstract void exibirTelaLista();

	public void salvar() {
		try {
			bindFormToBean();
			if (entidadeForm.getId() == null) {
				validaInclusao();
			} else {
				validaAlteracao();
			}
			getEntidadeService().salvar(entidadeForm);
			JOptionPane.showMessageDialog(null, propertiesLoader.getProperty("cadastro.salvo_com_sucesso"));
		} catch (ValidacaoException e) {
			tratarErro(e);
		}
	}

	private void tratarErro(Exception e) {
		log.error(e.getMessage(), e);
	}

    protected void validaAlteracao() throws ValidacaoException {
		/*throw new ValidacaoException(null, null);*/
	}

    protected void validaInclusao() throws ValidacaoException {
    	/*if (false) {
    		throw new ValidacaoException(null, null);
    	}*/
	}

	public void remover() {
    	getEntidadeService().remover(entidadeForm);
    	if (entidadeForm == null) {
			entidadeForm = getTabela().getSelectionModel().getSelectedItem();
		}
    	getEntidadeService().remover(entidadeForm);
		/*mensagem.setText("Removido com sucesso!");*/
    }

    protected abstract EntidadeService<T> getEntidadeService();

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

	/*public void bindFiltros() {
		bindFieldAnnotation(FiltroTela.class, entidadeFiltro);
	}*/
	
	private void bindFormToBean() {
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
