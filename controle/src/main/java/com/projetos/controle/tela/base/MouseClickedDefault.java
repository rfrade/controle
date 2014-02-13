package com.projetos.controle.tela.base;

import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import com.projetos.controle.tela.controller.base.BaseCadastroController;
import com.projetos.controle.tela.controller.base.BaseListController;
import com.projetos.controle_entities.Entidade;

public class MouseClickedDefault<T extends Entidade> implements EventHandler<MouseEvent> {
	
	private TableView<T> tabela;
	private BaseCadastroController<T> baseCadastroController;
	private BaseListController<T> baseListController;

	public MouseClickedDefault(TableView<T> tabela, BaseListController<T> baseListController, BaseCadastroController<T> baseCadastroController) {
		this.tabela = tabela;
		this.baseCadastroController = baseCadastroController;
		this.baseListController = baseListController;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getClickCount() > 1) {
			T selectedItem = this.tabela.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				baseCadastroController.setEntidadeForm(selectedItem);
				baseListController.exibirTelaCadastro();
			}
		}
	}
}