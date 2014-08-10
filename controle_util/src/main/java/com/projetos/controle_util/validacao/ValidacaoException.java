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
	private String[] param;

	public ValidacaoException(String mensagem) {
		this.setMensagem(mensagem);
	}
	
	public ValidacaoException(String mensagem, String... param) {
		this(mensagem);
		this.setParam(param);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String[] getParam() {
		return param;
	}

	public void setParam(String[] param) {
		this.param = param;
	}

}
