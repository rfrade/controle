package com.projetos.controle.tela.util;

import java.util.Date;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.reflection.BeanUtil;

public class CelulaFactory<C, T> implements Callback<CellDataFeatures<C, T>, ObservableValue<T>> {
	
	private String nomePropriedade;
	
	public CelulaFactory(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	
	@SuppressWarnings("unchecked")
	public ObservableValue<T> call(CellDataFeatures<C, T> celula) {
		Object propriedade = BeanUtil.getPropriedade(celula.getValue(), this.nomePropriedade);
		if (propriedade instanceof Date) {
			String string = DateUtil.convertDateToString( (Date) propriedade);
			return new ReadOnlyObjectWrapper<T>((T)string);
		}
		return new ReadOnlyObjectWrapper<T>((T)propriedade);
	}
	
}
