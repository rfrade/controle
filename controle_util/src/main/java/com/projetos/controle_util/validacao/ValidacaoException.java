package com.projetos.controle_util.validacao;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private Mensagem mensagemValidacao;
	private String codigoMensagem;

	public ValidacaoException(Mensagem mensagemValidacao) {
		this.mensagemValidacao = mensagemValidacao;
	}
	
	public ValidacaoException(String codigoMensagem) {
		this.setCodigoMensagem(codigoMensagem);
	}

	public ValidacaoException(CodigoMensagem codigoMensagem, SeveridadeMensagem severidade, String campo) {
		this.mensagemValidacao = new Mensagem(codigoMensagem, severidade, campo);
	}

	public Mensagem getMensagemValidacao() {
		return mensagemValidacao;
	}

	public String getCodigoMensagem() {
		return codigoMensagem;
	}

	public void setCodigoMensagem(String codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}

}
