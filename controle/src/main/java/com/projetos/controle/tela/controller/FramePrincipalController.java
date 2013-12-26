package com.projetos.controle.tela.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.BaseApplication;

@Controller(value="framePrincipalController")
public class FramePrincipalController extends BaseApplication {

	public void exibirTelaCliente() {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Teste");
		Pane pane = (Pane)FXMLLoader.load(getClass().getResource("/fxml/ClienteLista.fxml"));
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch("");
	}

}
