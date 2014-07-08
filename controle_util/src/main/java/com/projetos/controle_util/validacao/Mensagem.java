package com.projetos.controle_util.validacao;

// TODO: EXCLUIR CLASSE
public class Mensagem {

	private CodigoMensagem codigoMensagem;
	private SeveridadeMensagem severidade;
	private String campo;

	public Mensagem(CodigoMensagem codigoMensagem, SeveridadeMensagem severidade, String campo) {
		this.codigoMensagem = codigoMensagem;
		this.severidade = severidade;
		this.campo = campo;
	}

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

	public SeveridadeMensagem getSeveridade() {
		return severidade;
	}

	public void setSeveridade(SeveridadeMensagem severidade) {
		this.severidade = severidade;
	}

}
