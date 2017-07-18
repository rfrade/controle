package com.projetos.controle.tela.base;

import javafx.fxml.Initializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle.tela.controller.PopupConfirmacaoController;
import com.projetos.controle.tela.controller.PopupMensagemController;
import com.projetos.controle.tela.controller.TelaPrincipalController;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_util.validacao.ValidacaoException;

/**
 *
 * @author Rafael
 */
@SuppressWarnings("restriction")
public abstract class AbstractController implements Initializable {

	@Autowired
	protected TelaPrincipalController telaPrincipalController;

	@Autowired
	protected PropertiesLoader propertiesLoader;
	
	@Autowired
	protected PopupConfirmacaoController popupConfirmacaoController;

	@Autowired
	protected PopupMensagemController popupMensagemController;

	
	protected Logger log = Logger.getLogger(this.getClass());

	
	protected void tratarErro(Exception e) {
		log.error(e.getMessage(), e);
		exibirMensagemNaoMapeada("Erro, tire um print da tela e envie por email: " + e.getMessage());
	}


	protected void tratarErro(NegocioException e) {

		// Se existir uma mensagem específica mostra, senão exibe e.getMessage()
		String parametro = e.getParametroMensagem();
		if (parametro != null && !parametro.equals("")) {
			String mensagem = propertiesLoader.getProperty(parametro);
			log.error(mensagem, e);
			exibirMensagemNaoMapeada(mensagem);
			
		} else {
			log.error(e.getMessage(), e);
			exibirMensagemNaoMapeada("Erro, tire um print da tela e envie por email: " + e.getMessage());

		}
	}
	
	protected void tratarErroValidacao(ValidacaoException e) {
		String mensagem = propertiesLoader.getProperty(e.getMensagem());

		if (mensagem == null) {

			if (e.getParam() != null) {
				exibirMensagemNaoMapeada(e.getMensagem() + ": " + e.getParam()[0]);
				return;
			}

			exibirMensagemNaoMapeada(e.getMensagem());
			return;
		
		} else {
			
			exibirMensagemNaoMapeada(mensagem);

		}

	}

	protected void exibirMensagem(String mensagem) {
		exibirMensagemNaoMapeada(propertiesLoader.getProperty(mensagem));
	}

	protected void exibirMensagemNaoMapeada(String mensagem) {
		popupMensagemController.setMensagem(mensagem);
		telaPrincipalController.exibirPopupMensagem();
	}

}
