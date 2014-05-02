package com.projetos.controle.tela.report;

public class RelatorioException extends Exception {

	private static final long serialVersionUID = 7180995404051847707L;
	private String chaveMensagem;

	public RelatorioException(String chaveMensagem) {
		super();
		this.chaveMensagem = chaveMensagem;

	}
	
	public RelatorioException(String chaveMensagem, Exception e) {
		super(e);
		this.chaveMensagem = chaveMensagem;
	}
	
	public RelatorioException(Exception e) {
		super(e);
	}

	public String getChaveMensagem() {
		return chaveMensagem;
	}

}
