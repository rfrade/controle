package com.projetos.controle_util.validacao;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private MensagemValidacao mensagemValidacao;

	public ValidacaoException(MensagemValidacao mensagemValidacao) {

	}

	public ValidacaoException(String campo, CodigoMensagem codigoMensagem) {
		this.mensagemValidacao = new MensagemValidacao();
		this.mensagemValidacao.setCampo(campo);
		this.mensagemValidacao.setCodigoMensagem(codigoMensagem);
	}

}
