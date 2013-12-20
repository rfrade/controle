package com.projetos.controle.tela;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.junit.Test;

import com.projetos.controle.tela.base.TelaBaseTeste;

public class ClienteCadastroTeste extends TelaBaseTeste {
	
	@Test
	public void iniciar() {
		launch("");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Teste");
		Pane pane = (Pane)FXMLLoader.load(getClass().getResource("/fxml/ClienteCadastro.fxml"));
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
