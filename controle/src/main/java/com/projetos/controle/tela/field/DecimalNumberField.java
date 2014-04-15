package com.projetos.controle.tela.field;

import javafx.scene.control.TextField;

public class DecimalNumberField extends TextField {

	public String getFormattedText() {
		String text = super.getText();
		return text.replace(",", ".");
	}

}
