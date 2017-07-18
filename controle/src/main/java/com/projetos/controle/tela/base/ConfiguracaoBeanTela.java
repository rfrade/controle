package com.projetos.controle.tela.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import com.projetos.controle.tela.controller.ParametroCadastroController;
import com.projetos.controle.tela.controller.ParametroListaController;
import com.projetos.controle.tela.controller.PedidoCadastroController;
import com.projetos.controle.tela.controller.PedidoListaController;
import com.projetos.controle.tela.controller.PopupConfirmacaoController;
import com.projetos.controle.tela.controller.PopupMensagemController;
import com.projetos.controle.tela.controller.PopupTextoController;
import com.projetos.controle.tela.controller.ProdutoCadastroController;
import com.projetos.controle.tela.controller.ProdutoListaController;
import com.projetos.controle.tela.controller.ProdutoListaTelaPedidoController;
import com.projetos.controle.tela.controller.RecebimentoCadastroController;
import com.projetos.controle.tela.controller.RecebimentoListaController;
import com.projetos.controle.tela.controller.RelatorioRecebimentoController;
import com.projetos.controle.tela.controller.TelaPrincipalController;
import com.projetos.controle.tela.controller.VendedorCadastroController;
import com.projetos.controle.tela.controller.VendedorListaController;

@SuppressWarnings("restriction")
@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfiguracaoBeanTela {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	@Autowired
	private ApplicationContext applicationContext;

	private Map<Class<AbstractController>, Parent> telas = new HashMap<Class<AbstractController>, Parent>();
	
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
	
	@Bean(name = "telaProdutoLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaProdutoLista() {
		return carregarTela("/fxml/ProdutoLista.fxml", ProdutoListaController.class);
	}
	
	@Bean(name = "produtoListaTelaPedido")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarProdutoListaTelaPedido() {
		return carregarTela("/fxml/ProdutoListaTelaPedido.fxml", ProdutoListaTelaPedidoController.class);
	}

	@Bean(name = "telaClienteCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
    public Parent carregarTelaClienteCadastro() {
    	return carregarTela("/fxml/ClienteCadastro.fxml", ClienteCadastroController.class);
    }
	
	@Bean(name = "telaFornecedorCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaFornecedorCadastro() {
		return carregarTela("/fxml/FornecedorCadastro.fxml", FornecedorCadastroController.class);
	}
	
	@Bean(name = "telaRecebimentoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaRecebimentoCadastro() {
		return carregarTela("/fxml/RecebimentoCadastro.fxml", RecebimentoCadastroController.class);
	}
	
	@Bean(name = "telaPedidoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaPedidoCadastro() {
		return carregarTela("/fxml/PedidoCadastro.fxml", PedidoCadastroController.class);
	}
	
	@Bean(name = "telaProdutoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaProdutoCadastro() {
		return carregarTela("/fxml/ProdutoCadastro.fxml", ProdutoCadastroController.class);
	}
	
	@Bean(name = "telaRecebimentoLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaRecebimentoLista() {
		return carregarTela("/fxml/RecebimentoLista.fxml", RecebimentoListaController.class);
	}
	
	@Bean(name = "telaRelatorioRecebimentosLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaRelatorioRecebimentosLista() {
		return carregarTela("/fxml/RelatorioRecebimentoLista.fxml", RelatorioRecebimentoController.class);
	}
	
	@Bean(name = "telaParametroLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaParametroLista() {
		return carregarTela("/fxml/ParametroLista.fxml", ParametroListaController.class);
	}
	
	@Bean(name = "telaVendedorLista")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaVendedorLista() {
		return carregarTela("/fxml/VendedorLista.fxml", VendedorListaController.class);
	}
	
	@Bean(name = "telaParametroCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaParametroCadastro() {
		return carregarTela("/fxml/ParametroCadastro.fxml", ParametroCadastroController.class);
	}
	
	@Bean(name = "telaVendedorCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaVendedorCadastro() {
		return carregarTela("/fxml/VendedorCadastro.fxml", VendedorCadastroController.class);
	}
	
	@Bean(name = "telaRelatorioRecebimentosFiltro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaRelatorioRecebimentosFiltro() {
		return carregarTela("/fxml/RelatorioRecebimentoFiltro.fxml", RelatorioRecebimentoController.class);
	}
	
	@Bean(name = "telaItemPedidoCadastro")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarTelaItemPedidoCadastro() {
		return carregarTela("/fxml/ItemPedidoCadastro.fxml", ItemPedidoCadastroController.class);
	}
	
	@Bean(name = "popupConfirmacao")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarPopupConfirmacao() {
		return carregarTela("/fxml/PopupConfirmacao.fxml", PopupConfirmacaoController.class);
	}
	
	@Bean(name = "popupMensagem")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarPopupMensagem() {
		return carregarTela("/fxml/PopupMensagem.fxml", PopupMensagemController.class);
	}
	
	@Bean(name = "popupTexto")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Parent carregarPopupTexto() {
		return carregarTela("/fxml/PopupTexto.fxml", PopupTextoController.class);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Initializable> Parent carregarTela(String fxml, Class<T> classe) {
		try {
			if (!telas.containsKey(classe)) {
				FXMLLoader loader = getLoader(fxml, classe);
				Parent tela = (Parent) loader.load();
				telas.put((Class<AbstractController>) classe, tela);
				
				return tela;
			} else {
				Initializable controller = applicationContext.getBean(classe);
				controller.initialize(null, null);
				return telas.get(classe);
			}
		} catch (IOException e) {
			log.error("Erro ao carregar o arquivo: " + fxml);
			throw new RuntimeException(e);
		}
	}
	
	private <T extends Initializable> FXMLLoader getLoader(String fxml, Class<T> classe) {
		Initializable controller = applicationContext.getBean(classe);
		CallbackTela callback = new CallbackTela(controller);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.setControllerFactory(callback);
		return loader;
	}

}
