package com.projetos.controle.tela.base;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.projetos.controle.tela.InicioAplicacao;
import com.projetos.controle.tela.controller.ClienteController;
import com.projetos.controle.tela.controller.TelaPrincipalController;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GerenciadorTela {

	private Stage primaryStage;
	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	@Autowired
	private ApplicationContext applicationContext;

	private void exibirTela(Parent tela) {
		Scene scene = new Scene(tela);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void exibirPopup(Parent tela) {
		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(tela);
		popup.setScene(scene);
		popup.show();
	}
	
	public void exibirTelaPrincipal() {
		this.exibirTela(carregarTelaPrincipal());
	}

	public void exibirTelaClienteLista() {
		this.exibirTela(carregarTelaClienteLista());
	}
	
	public void exibirTelaClienteCadastro() {
		this.exibirPopup(carregarTelaClienteCadastro());
	}
	
	@Bean(name = "telaPrincipal")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Parent carregarTelaPrincipal() {
		return carregarTela("/fxml/TelaPrincipal.fxml", TelaPrincipalController.class);
	}

	@Bean(name = "telaClienteLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaClienteLista() {
		return carregarTela("/fxml/ClienteLista.fxml", ClienteController.class);
	}

	@Bean(name = "telaClienteCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Parent carregarTelaClienteCadastro() {
    	return carregarTela("/fxml/ClienteCadastro.fxml", ClienteController.class);
    }

	public <T extends AbstractController> Parent carregarTela(String fxml, Class<T> classe) {
		try {
			FXMLLoader loader = getLoader(fxml, classe);
			Parent tela = (Parent) loader.load();
			return tela;
		} catch (IOException e) {
			log.error("Não foi possível carregar o arquivo: " + fxml);
			throw new RuntimeException(e);
		}
	}
	
	/*private <T extends AbstractController> Window carregarPopup(String fxml, Class<T> classe) {
		try {
			FXMLLoader loader = getLoader(fxml, classe);
			Window popup = (Window) loader.load();
			return popup;
		} catch (IOException e) {
			log.error("Não foi possível carregar o arquivo: " + fxml);
			throw new RuntimeException(e);
		}
	}*/

	private <T extends AbstractController> FXMLLoader getLoader(String fxml, Class<T> classe) {
		AbstractController controller = applicationContext.getBean(classe);
		CallbackTela callback = new CallbackTela(controller);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.setControllerFactory(callback);
		return loader;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
