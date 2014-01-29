package com.projetos.controle_util.reflection;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;

public class BeanUtil {

	public static Object getPropriedade(Object objeto, String nomePropriedade) {
		if (nomePropriedade.contains(".")) {

			int index = nomePropriedade.indexOf(".");
			String nomeNovaRaiz = nomePropriedade.substring(0, index);
			String nomeNovaPropriedade = nomePropriedade.substring(index + 1);
			Object novaRaiz = new Mirror().on(objeto).get().field(nomeNovaRaiz);

			return getPropriedade(novaRaiz, nomeNovaPropriedade);
		} else {
			if (objeto != null) {
				return new Mirror().on(objeto).get().field(nomePropriedade);
			}
			return null;
		}
	}

	public static void setPropriedade(Object objeto, String nomePropriedade,
			Object valor) {
		if (nomePropriedade.contains(".")) {

			int index = nomePropriedade.indexOf(".");
			String nomeNovaRaiz = nomePropriedade.substring(0, index);
			String nomeNovaPropriedade = nomePropriedade.substring(index + 1);
			Object novaRaiz = new Mirror().on(objeto).get().field(nomeNovaRaiz);

			if (novaRaiz == null) {
				Class<?> classe = new Mirror().on(objeto.getClass()).reflect().field(nomeNovaRaiz).getType();
				novaRaiz = new Mirror().on(classe).invoke().constructor().bypasser();

				new Mirror().on(objeto).set().field(nomeNovaRaiz).withValue(novaRaiz);
			}

			setPropriedade(novaRaiz, nomeNovaPropriedade, valor);
		} else {
			if (valor != null) {
				Field field = new Mirror().on(objeto.getClass()).reflect().field(nomePropriedade);
				if (field == null) {
					throw new IllegalArgumentException("There is no field with name: " + nomePropriedade + " on class: " + objeto.getClass());
				}
				Class<?> type = field.getType();
				if (type.getName() == "int") {
					valor = Integer.valueOf(valor.toString());
				} else if (type.getName() == "long") {
						valor = Integer.valueOf(valor.toString());
				} else if (type == Integer.class) {
					valor = Integer.valueOf(valor.toString());
				} else if (type == Long.class) {
					valor = Long.valueOf(valor.toString());
				}
				new Mirror().on(objeto).set().field(nomePropriedade).withValue(valor);
			}
		}
	}
	
	public static Object invoke(Object objeto, String metodo, Object... args) {
		Object result = new Mirror().on(objeto).invoke().method(metodo).withArgs(args);
		return result;
	}
	
}
