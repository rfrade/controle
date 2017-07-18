package com.projetos.controle_negocio.exception;

public class NegocioException extends Exception {

	
	private String parametroMensagem = "";
	
	private static final long serialVersionUID = -5490348535196648548L;

	
	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(Throwable cause) {
		super(cause);
	}

	public NegocioException(String parametro, Throwable cause) {
		super(cause);
		this.parametroMensagem = parametro;
	}

	public String getParametroMensagem() {
		return parametroMensagem;
	}

}
