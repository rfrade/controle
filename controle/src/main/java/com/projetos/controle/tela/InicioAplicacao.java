package com.projetos.controle.tela;

import java.util.Calendar;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projetos.controle.tela.base.BaseApplication;
import com.projetos.controle.tela.controller.TelaPrincipalController;

@SuppressWarnings("restriction")
public class InicioAplicacao extends BaseApplication {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	public static void main(String[] args) {
		log.info("Aplicação iniciada em " + Calendar.getInstance().getTime());
		launch("Controle 1.0");

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ApplicationConfig.setContext(context);

		TelaPrincipalController telaPrincipalController = context.getBean(TelaPrincipalController.class);

		telaPrincipalController.setPrimaryStage(primaryStage);
		primaryStage.setTitle("Aplicação Controle 1.0");

		addCloseButtonListener(primaryStage);
		
		telaPrincipalController.exibirTelaPrincipal();
	}

	// Necessário para matar o processo da VM
	public static void addCloseButtonListener(Stage primaryStage) {
		primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
	}

}