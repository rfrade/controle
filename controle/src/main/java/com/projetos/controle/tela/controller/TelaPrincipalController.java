package com.projetos.controle.tela.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle.tela.base.ConfiguracaoBeanTela;

@SuppressWarnings("rawtypes")
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelaPrincipalController extends AbstractController {

	@Autowired
	private ConfiguracaoBeanTela configuracaoBeanTela;

	@Autowired
	private BackupController backupController;

	private Stage primaryStage;

	@FXML
	private Pane paneTelaAtiva;

	public void exibirTelaClienteLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaClienteLista();
		exibirTela(telaLista);
	}

	public void exibirTelaFornecedorLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaFornecedorLista();
		exibirTela(telaLista);
	}

	public void exibirTelaPedidoLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaPedidoLista();
		exibirTela(telaLista);
	}

	public void exibirTelaProdutoLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaProdutoLista();
		exibirTela(telaLista);
	}

	public void exibirTelaRelatorioRecebimentos() {
		Parent telaLista = configuracaoBeanTela.carregarTelaRelatorioRecebimentosLista();
		exibirTela(telaLista);
	}

	public void exibirTelaParametroLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaParametroLista();
		exibirTela(telaLista);
	}

	public void exibirTelaVendedorLista() {
		Parent telaLista = configuracaoBeanTela.carregarTelaVendedorLista();
		exibirTela(telaLista);
	}

	public void exibirTelaBackUp() {
		backupController.exibirTelaBackup(primaryStage);
	}

	private void exibirTela(Parent tela) {
		paneTelaAtiva.getChildren().setAll(tela);
	}

	public Stage exibirPopup(Parent tela) {
		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(tela);
		popup.setScene(scene);
		popup.show();
		return popup;
	}

	public void exibirTelaPrincipal() {
		Scene scene = new Scene(configuracaoBeanTela.carregarTelaPrincipal());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Stage exibirTelaRecebimentoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaRecebimentoCadastro());
		return popup;
	}

	public Stage exibirTelaClienteCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaClienteCadastro());
		return popup;
	}

	public Stage exibirTelaFornecedorCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaFornecedorCadastro());
		return popup;
	}

	public Stage exibirTelaPedidoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaPedidoCadastro());
		return popup;
	}

	public Stage exibirTelaProdutoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaProdutoCadastro());
		return popup;
	}

	public Stage exibirTelaItemPedidoCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaItemPedidoCadastro());
		return popup;
	}

	public Stage exibirTelaParametroCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaParametroCadastro());
		return popup;
	}
	
	public Stage exibirTelaVendedorCadastro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaVendedorCadastro());
		return popup;
	}
	
	public Stage exibirProdutoListaTelaPedido() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarProdutoListaTelaPedido());
		return popup;
	}

	public Stage exibirTelaRecebimentoLista() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaRecebimentoLista());
		return popup;
	}

	public Stage exibirTelaRelatorioRecebimentosFiltro() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarTelaRelatorioRecebimentosFiltro());
		return popup;
	}

	public Stage exibirPopupMensagem() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarPopupMensagem());
		return popup;
	}

	public Stage exibirPopupTexto() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarPopupTexto());
		return popup;
	}

	public Stage exibirPopupConfirmacao() {
		Stage popup = this.exibirPopup(configuracaoBeanTela.carregarPopupConfirmacao());
		return popup;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
