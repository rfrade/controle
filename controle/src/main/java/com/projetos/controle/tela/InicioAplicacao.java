package com.projetos.controle.tela;

import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

import com.projetos.controle.tela.base.BaseApplication;

public class InicioAplicacao extends BaseApplication {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	public static void main(String[] args) {
		log.info("Aplicação iniciada em " + Calendar.getInstance().getTime());
		launch("Controle 1.0");

		/*ApplicationContext context = new ClassPathXmlApplicationContext("");*/
		
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