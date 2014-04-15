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
import com.projetos.controle.tela.controller.FornecedorCadastroController;
import com.projetos.controle.tela.controller.FornecedorListaController;
import com.projetos.controle.tela.controller.ItemPedidoCadastroController;
import com.projetos.controle.tela.controller.PedidoCadastroController;
import com.projetos.controle.tela.controller.PedidoListaController;
import com.projetos.controle.tela.controller.PopupConfirmacaoController;
import com.projetos.controle.tela.controller.PopupMensagemController;
import com.projetos.controle.tela.controller.RecebimentoCadastroController;
import com.projetos.controle.tela.controller.RecebimentoListaController;
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
	
	@Bean(name = "telaFornecedorLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaFornecedorLista() {
		return carregarTela("/fxml/FornecedorLista.fxml", FornecedorListaController.class);
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
	
	@Bean(name = "telaFornecedorCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaFornecedorCadastro() {
		return carregarTela("/fxml/FornecedorCadastro.fxml", FornecedorCadastroController.class);
	}
	
	@Bean(name = "telaRecebimentoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaRecebimentoCadastro() {
		return carregarTela("/fxml/RecebimentoCadastro.fxml", RecebimentoCadastroController.class);
	}
	
	@Bean(name = "telaPedidoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaPedidoCadastro() {
		return carregarTela("/fxml/PedidoCadastro.fxml", PedidoCadastroController.class);
	}
	
	@Bean(name = "telaRecebimentoLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaRecebimentoLista() {
		return carregarTela("/fxml/RecebimentoLista.fxml", RecebimentoListaController.class);
	}
	
	@Bean(name = "telaItemPedidoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarTelaItemPedidoCadastro() {
		return carregarTela("/fxml/ItemPedidoCadastro.fxml", ItemPedidoCadastroController.class);
	}
	
	@Bean(name = "popupConfirmacao")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarPopupConfirmacao() {
		return carregarTela("/fxml/PopupConfirmacao.fxml", PopupConfirmacaoController.class);
	}
	
	@Bean(name = "popupMensagem")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Parent carregarPopupMensagem() {
		return carregarTela("/fxml/PopupMensagem.fxml", PopupMensagemController.class);
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
