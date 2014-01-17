package com.projetos.controle_util.collection;

import java.util.ArrayList;
import java.util.List;

public class ColecaoUtil {

	public static <T> List<T> converterParaLista(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		for (T t : iterable) {
			list.add(t);
		}
		return list;
	}


}
