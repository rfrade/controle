package com.projetos.controle.tela;

import java.util.Calendar;

import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projetos.controle.tela.base.BaseApplication;
import com.projetos.controle.tela.base.PropertiesPathLoader;
import com.projetos.controle.tela.controller.TelaPrincipalController;

public class InicioAplicacao extends BaseApplication {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	public static void main(String[] args) {
		log.info("Aplicação iniciada em " + Calendar.getInstance().getTime());
		launch("Controle 1.0");

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*String property = PropertiesPathLoader.getProperty(PropertiesPathLoader.RELATORIO_PEDIDOS);
		System.out.println(property);*/

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ApplicationConfig.setContext(context);
		TelaPrincipalController telaPrincipalController = context.getBean(TelaPrincipalController.class);

		telaPrincipalController.setPrimaryStage(primaryStage);
		primaryStage.setTitle("Aplicação Controle 1.0");

		telaPrincipalController.exibirTelaPrincipal();
	}

}