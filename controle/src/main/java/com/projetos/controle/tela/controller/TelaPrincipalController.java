package com.projetos.controle.tela.controller;

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
	private ClienteController clienteController;

	@Autowired
	private ConfiguracaoBeanTela configuracaoBeanTela;

	private Stage primaryStage;
	
	@FXML
	private Pane paneTelaAtiva;

	public void exibirTelaClienteLista() {
		Parent telaClienteLista = configuracaoBeanTela.carregarTelaClienteLista();
		exibirTela(telaClienteLista);
	}

	private void exibirTela(Parent tela) {
		paneTelaAtiva.getChildren().setAll(tela);
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
		Scene scene = new Scene(configuracaoBeanTela.carregarTelaPrincipal());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void exibirTelaClienteCadastro() {
		this.exibirPopup(configuracaoBeanTela.carregarTelaClienteCadastro());
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
}
