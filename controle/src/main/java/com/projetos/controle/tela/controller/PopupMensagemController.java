package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;

@Controller
@Lazy
public class PopupMensagemController extends AbstractController implements Initializable {

	private String mensagem;

	@FXML
	private Label labelMensagem;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelMensagem.setText(mensagem);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
