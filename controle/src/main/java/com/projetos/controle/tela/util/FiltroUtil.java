package com.projetos.controle.tela.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.projetos.controle.tela.base.ItemCombo;

/**
 * Classe com utilitários para criação de listas com filtros.
 * @author Rafael
 */
public class FiltroUtil {

	public static ObservableList<ItemCombo<Boolean>> criarListaFiltroAtivos() {
		Map<String, Boolean> mapa = new HashMap<String, Boolean>();
		mapa.put("ATIVO", true);
		mapa.put("NÃO ATIVO", false);
		mapa.put("TODOS", null);
		return criarListaFiltro(mapa);
	}
	
	public static <T> ObservableList<ItemCombo<T>> criarListaFiltro(Map<String, T> mapa) {
		List<ItemCombo<T>> lista = new ArrayList<>();
		for (Entry<String, T> entry : mapa.entrySet()) {
			ItemCombo<T> item = new ItemCombo<>(entry.getKey(), entry.getValue());
			lista.add(item);
		}
		ObservableList<ItemCombo<T>> itens = FXCollections.observableArrayList(lista);
		return itens;
	}

	
}
