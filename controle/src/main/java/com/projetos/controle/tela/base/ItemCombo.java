package com.projetos.controle.tela.base;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.projetos.controle_util.reflection.BeanUtil;

public class ItemCombo<T> {
	private String label;
	private T valor;
	
	public ItemCombo(String label, T valor) {
		this.label = label;
		this.valor = valor;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public T getValor() {
		return valor;
	}
	public void setValor(T valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCombo<?> other = (ItemCombo<?>) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public static <E> ObservableList<ItemCombo<E>> novaListaCombo(List<E> lista, String label) {
		List<ItemCombo<E>> listaCombo = new ArrayList<>();
		for (E e : lista) {
			String propriedadeLabel = (String) BeanUtil.getPropriedade(e, label);
			ItemCombo<E> item = new ItemCombo<E>(propriedadeLabel, e);
			listaCombo.add(item);
		}
		return FXCollections.observableArrayList(listaCombo);
	}
	
}