package com.projetos.controle.tela.base;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.projetos.controle.tela.InicioAplicacao;
import com.projetos.controle.tela.controller.ClienteCadastroController;
import com.projetos.controle.tela.controller.ClienteListaController;
import com.projetos.controle.tela.controller.PedidoCadastroController;
import com.projetos.controle.tela.controller.PedidoListaController;
import com.projetos.controle.tela.controller.TelaPrincipalController;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfiguracaoBeanTela {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	@Autowired
	private ApplicationContext applicationContext;

	
	@Bean(name = "telaPrincipal")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Parent carregarTelaPrincipal() {
		return carregarTela("/fxml/TelaPrincipal.fxml", TelaPrincipalController.class);
	}

	@Bean(name = "telaClienteLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaClienteLista() {
		return carregarTela("/fxml/ClienteLista.fxml", ClienteListaController.class);
	}
	
	@Bean(name = "telaPedidoLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaPedidoLista() {
		return carregarTela("/fxml/PedidoLista.fxml", PedidoListaController.class);
	}

	@Bean(name = "telaClienteCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Parent carregarTelaClienteCadastro() {
    	return carregarTela("/fxml/ClienteCadastro.fxml", ClienteCadastroController.class);
    }
	
	@Bean(name = "telaPedidoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaPedidoCadastro() {
		return carregarTela("/fxml/PedidoCadastro.fxml", PedidoCadastroController.class);
	}

	public <T extends AbstractController> Parent carregarTela(String fxml, Class<T> classe) {
		try {
			FXMLLoader loader = getLoader(fxml, classe);
			Parent tela = (Parent) loader.load();
			return tela;
		} catch (IOException e) {
			log.error("Erro ao carregar o arquivo: " + fxml);
			throw new RuntimeException(e);
		}
	}
	
	private <T extends AbstractController> FXMLLoader getLoader(String fxml, Class<T> classe) {
		AbstractController controller = applicationContext.getBean(classe);
		CallbackTela callback = new CallbackTela(controller);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.setControllerFactory(callback);
		return loader;
	}

}
