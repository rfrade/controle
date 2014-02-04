package com.projetos.controle_util.validacao;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private MensagemValidacao mensagemValidacao;

	public ValidacaoException(MensagemValidacao mensagemValidacao) {
		this.mensagemValidacao = mensagemValidacao;
	}

	public ValidacaoException(CodigoMensagem codigoMensagem, SeveridadeMensagem severidade, String campo) {
		this.mensagemValidacao = new MensagemValidacao(codigoMensagem, severidade, campo);
	}

	public MensagemValidacao getMensagemValidacao() {
		return mensagemValidacao;
	}

}
