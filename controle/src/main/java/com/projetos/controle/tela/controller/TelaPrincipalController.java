package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.ApplicationConfig;
import com.projetos.controle.tela.base.ConfiguracaoBeanTela;

@SuppressWarnings({ "rawtypes", "restriction" })
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelaPrincipalController implements Initializable {

	@Autowired
	private ConfiguracaoBeanTela configuracaoBeanTela;

	private Stage primaryStage;

	@FXML
	private Pane paneTelaAtiva;

	public void exibirTelaClienteLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaClienteLista();
		exibirTela(telaLista);
	}

	public void exibirTelaFornecedorLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaFornecedorLista();
		exibirTela(telaLista);
	}

	public void exibirTelaPedidoLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaPedidoLista();
		exibirTela(telaLista);
	}

	public void exibirTelaProdutoLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaProdutoLista();
		exibirTela(telaLista);
	}

	public void exibirTelaRelatorioRecebimentos() {
		Parent telaLista = configuracaoBeanTela.carregarTelaRelatorioRecebimentosLista();
		exibirTela(telaLista);
	}

	public void exibirTelaParametroLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaParametroLista();
		exibirTela(telaLista);
	}

	public void exibirTelaVendedorLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaVendedorLista();
		exibirTela(telaLista);
	}

	public void exibirTelaBackUp() {
		BackupController backupController = ApplicationConfig.getBean(BackupController.class);
		backupController.exibirTelaBackup(primaryStage);
	}

	private void exibirTela(Parent tela) {
		paneTelaAtiva.getChildren().setAll(tela);
	}

	
	Map<Class, Stage> mapaPopups = new HashMap<Class, Stage>();
	/**
	 *  Verifica se o popup já foi criado, senão o cria e o adiciona no mapa
	 */
	public Stage exibirPopup(Class classeControler, Parent tela) {
		
		if (!this.mapaPopups.containsKey(classeControler)) {
			Stage popup = new Stage();
			popup.initOwner(primaryStage);
			popup.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(tela);
			popup.setScene(scene);
			
			this.mapaPopups.put(classeControler, popup);
			popup.show();
			return popup;
			
		} else {
			Stage popup = this.mapaPopups.get(classeControler);
			popup.show();
			return popup;
		}

		
	}

	public void exibirTelaPrincipal() {
		Scene scene = new Scene(configuracaoBeanTela.carregarTelaPrincipal());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Stage exibirTelaRecebimentoCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaRecebimentoCadastro();
		Stage popup = this.exibirPopup(RecebimentoCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaClienteCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaClienteCadastro();
		Stage popup = this.exibirPopup(ClienteCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaFornecedorCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaFornecedorCadastro();
		Stage popup = this.exibirPopup(FornecedorCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaPedidoCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaPedidoCadastro();
		Stage popup = this.exibirPopup(PedidoCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaProdutoCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaProdutoCadastro();
		Stage popup = this.exibirPopup(ProdutoCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaItemPedidoCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaItemPedidoCadastro();
		Stage popup = this.exibirPopup(ItemPedidoCadastroController.class, tela);
		return popup;
	}

	public Stage exibirTelaParametroCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaParametroCadastro();
		Stage popup = this.exibirPopup(ParametroCadastroController.class, tela);
		return popup;
	}
	
	public Stage exibirTelaVendedorCadastro() {
		Parent tela = configuracaoBeanTela.carregarTelaVendedorCadastro();
		Stage popup = this.exibirPopup(VendedorCadastroController.class, tela);
		return popup;
	}
	
	public Stage exibirProdutoListaTelaPedido() {
		Parent tela = configuracaoBeanTela.carregarProdutoListaTelaPedido();
		Stage popup = this.exibirPopup(ProdutoListaTelaPedidoController.class, tela);
		return popup;
	}

	public Stage exibirTelaRecebimentoLista() {
		Parent tela = configuracaoBeanTela.carregarTelaRecebimentoLista();
		Stage popup = this.exibirPopup(RecebimentoListaController.class, tela);
		return popup;
	}

	public Stage exibirTelaRelatorioRecebimentosFiltro() {
		Parent tela = configuracaoBeanTela.carregarTelaRelatorioRecebimentosFiltro();
		Stage popup = this.exibirPopup(RelatorioRecebimentoController.class, tela);
		return popup;
	}

	public Stage exibirPopupMensagem() {
		Parent tela = configuracaoBeanTela.carregarPopupMensagem();
		Stage popup = this.exibirPopup(PopupMensagemController.class, tela);
		return popup;
	}

	public Stage exibirPopupTexto() {
		Parent tela = configuracaoBeanTela.carregarPopupTexto();
		Stage popup = this.exibirPopup(PopupTextoController.class, tela);
		return popup;
	}

	public Stage exibirPopupConfirmacao() {
		Parent tela = configuracaoBeanTela.carregarPopupConfirmacao();
		Stage popup = this.exibirPopup(PopupConfirmacaoController.class, tela);
		return popup;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
