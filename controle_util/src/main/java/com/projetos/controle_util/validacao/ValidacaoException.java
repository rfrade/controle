package com.projetos.controle_util.validacao;

/**
 * Exceção usada na validação de campos da tela.
 * A mensagem do construtor é a exibida na tela.
 * @author Rafael
 *
 */
public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public ValidacaoException(String mensagem) {
		this.setMensagem(mensagem);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
