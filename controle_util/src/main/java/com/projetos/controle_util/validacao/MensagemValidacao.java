package com.projetos.controle_util.validacao;

public class MensagemValidacao {

	private CodigoMensagem codigoMensagem;
	private String campo;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public CodigoMensagem getCodigoMensagem() {
		return codigoMensagem;
	}

	public void setCodigoMensagem(CodigoMensagem codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}

}
