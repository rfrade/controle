package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;

@Controller
@Lazy
public class PopupConfirmacaoController extends AbstractController implements Initializable {

	private String mensagem;
	private Stage stage;

	@FXML
	private Label labelMensagem;

	@FXML
	private Button botaoConfirmar;
	
	@FXML
	private Button botaoCancelar;
	
	private EventHandler<ActionEvent> confirmHandler;
	private EventHandler<ActionEvent> cancelHandler;
	private EventType<ActionEvent> closeType = new EventType<ActionEvent>("popup.confirmacao.controller.close.action");
	private CloseAction closeAction = new CloseAction();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelMensagem.setText(mensagem);
		
		botaoConfirmar.setOnAction(confirmHandler);
		botaoConfirmar.removeEventHandler(closeType, closeAction);
		botaoConfirmar.addEventHandler(closeType, closeAction);

		if (cancelHandler != null) {
			botaoCancelar.setOnAction(cancelHandler);
		} else {
			botaoCancelar.setOnAction(closeAction);
		}
	}

	public class CloseAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			close();
		}
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public EventHandler<ActionEvent> getConfirmHandler() {
		return confirmHandler;
	}

	public void setConfirmHandler(EventHandler<ActionEvent> confirmHandler) {
		this.confirmHandler = confirmHandler;
	}

	public EventHandler<ActionEvent> getCancelHandler() {
		return cancelHandler;
	}

	public void setCancelHandler(EventHandler<ActionEvent> cancelHandler) {
		this.cancelHandler = cancelHandler;
	}

	public void close() {
		stage.close();
	}

}
