package com.projetos.controle_util.validacao;

public enum CodigoMensagem {

	;
	
	private String codigoMensagem;
	
	private CodigoMensagem(String codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}

	public String getCodigoMensagem() {
		return codigoMensagem;
	}

}
