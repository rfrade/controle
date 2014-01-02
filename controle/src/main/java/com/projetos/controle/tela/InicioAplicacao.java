package com.projetos.controle.tela;

import java.util.Calendar;

import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projetos.controle.tela.base.BaseApplication;
import com.projetos.controle.tela.base.GerenciadorTela;

public class InicioAplicacao extends BaseApplication {

	private static Logger log = Logger.getLogger(InicioAplicacao.class);

	public static void main(String[] args) {
		log.info("Aplicação iniciada em " + Calendar.getInstance().getTime());
		launch("Controle 1.0");

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		GerenciadorTela gerenciadorTela = context.getBean(GerenciadorTela.class);

		gerenciadorTela.setPrimaryStage(primaryStage);
		primaryStage.setTitle("Aplicação Controle 1.0");

		gerenciadorTela.exibirTelaPrincipal();
	}

}