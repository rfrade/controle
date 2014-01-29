package com.projetos.controle.tela.util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import com.projetos.controle_util.reflection.BeanUtil;

public class CelulaFactory<C, T> implements Callback<CellDataFeatures<C, T>, ObservableValue<T>> {
	
	private String nomePropriedade;
	
	public CelulaFactory(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	
	@SuppressWarnings("unchecked")
	public ObservableValue<T> call(CellDataFeatures<C, T> celula) {
		Object propriedade = BeanUtil.getPropriedade(celula.getValue(), this.nomePropriedade);
		return new ReadOnlyObjectWrapper<T>((T)propriedade);
	}
	
}
