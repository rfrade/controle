package com.projetos.controle.tela.util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import net.vidageek.mirror.dsl.Mirror;

public class CelulaFactory<C, T> implements Callback<CellDataFeatures<C, T>, ObservableValue<T>> {
	
	private String nomePropriedade;
	
	public CelulaFactory(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	
	public ObservableValue<T> call(CellDataFeatures<C, T> celula) {
		Object propriedade = getPropriedade(celula.getValue(), this.nomePropriedade);
		/*if (propriedade == null) {
			propriedade = "";
		}*/
		return new ReadOnlyObjectWrapper<T>((T)propriedade);
	}
	
	public Object getPropriedade(Object objeto, String nomePropriedade) {
		if (nomePropriedade.contains(".")) {

			int index = nomePropriedade.indexOf(".");
			String nomeNovaRaiz = nomePropriedade.substring(0, index);
			String nomeNovaPropriedade = nomePropriedade.substring(index + 1);
			Object novaRaiz = new Mirror().on(objeto).get().field(nomeNovaRaiz);

			return getPropriedade(novaRaiz, nomeNovaPropriedade);
		} else {
			return new Mirror().on(objeto).get().field(nomePropriedade);
		}
	}
	
}
