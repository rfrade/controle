package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.ConfiguracaoBeanTela;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelaPrincipalController extends AbstractController {

	@Autowired
	private ConfiguracaoBeanTela configuracaoBeanTela;

	private Stage primaryStage;
	
	@FXML
	private Pane paneTelaAtiva;

	public void exibirTelaClienteLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaClienteLista();
		exibirTela(telaLista);
	}
	
	public void exibirTelaPedidoLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaPedidoLista();
		exibirTela(telaLista);
	}
	
	private void exibirTela(Parent tela) {
		paneTelaAtiva.getChildren().setAll(tela);
	}

	public Stage exibirPopup(Parent tela) {
		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(tela);
		popup.setScene(scene);
		popup.show();
		return popup;
	}
	
	public void exibirTelaPrincipal() {
		Scene scene = new Scene(configuracaoBeanTela.carregarTelaPrincipal());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Stage exibirTelaClienteCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaClienteCadastro());
		return popup;
	}

	public Stage exibirTelaPedidoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaPedidoCadastro());
		return popup;
	}
	
	public Stage exibirTelaItemPedidoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaItemPedidoCadastro());
		return popup;
	}
	
	public Stage exibirPopupMensagem() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarPopupMensagem());
		return popup;
	}
	
	public Stage exibirPopupConfirmacao() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarPopupConfirmacao());
		return popup;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
