package com.projetos.controle_util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import com.projetos.controle_util.conversao.NumberUtil;

public class BeanUtil {

	public static Object getPropriedade(Object objeto, String nomePropriedade) {
		if (nomePropriedade.contains(".")) {

			int index = nomePropriedade.indexOf(".");
			String nomeNovaRaiz = nomePropriedade.substring(0, index);
			String nomeNovaPropriedade = nomePropriedade.substring(index + 1);

			if (objeto == null) {
				return null;
			}

			Object novaRaiz = new Mirror().on(objeto).get().field(nomeNovaRaiz);

			return getPropriedade(novaRaiz, nomeNovaPropriedade);
		} else {
			if (objeto != null) {
				return new Mirror().on(objeto).get().field(nomePropriedade);
			}
			return null;
		}
	}

	public static void setPropriedade(Object objeto, String nomePropriedade, Object valor) {
		if (nomePropriedade.contains(".")) {

			int index = nomePropriedade.indexOf(".");
			String nomeNovaRaiz = nomePropriedade.substring(0, index);
			String nomeNovaPropriedade = nomePropriedade.substring(index + 1);
			Object novaRaiz = new Mirror().on(objeto).get().field(nomeNovaRaiz);

			if (novaRaiz == null) {
				Class<?> classe = new Mirror().on(objeto.getClass()).reflect().field(nomeNovaRaiz).getType();
				novaRaiz = new Mirror().on(classe).invoke().constructor().bypasser();

				preencherValor(objeto, nomeNovaRaiz, novaRaiz, classe);
			}

			setPropriedade(novaRaiz, nomeNovaPropriedade, valor);
		} else {
			if (valor != null) {
				Field field = new Mirror().on(objeto.getClass()).reflect().field(nomePropriedade);
				if (field == null) {
					throw new IllegalArgumentException("There is no field with name: " + nomePropriedade + " on class: " + objeto.getClass());
				}
				Class<?> type = field.getType();
				if (valor != null && valor.equals("")) {
					valor = null;
				} else if (type.getName() == "int") {
					valor = Integer.valueOf(valor.toString());
				} else if (type.getName() == "double") {
					valor = NumberUtil.convertStringToDouble((String) valor);
				} else if (type.getName() == "long") {
					valor = Long.valueOf(valor.toString());
				} else if (type == Integer.class) {
					valor = Integer.valueOf(valor.toString());
				} else if (type == Long.class) {
					valor = Long.valueOf(valor.toString());
				} else if (type == Double.class) {
					valor = NumberUtil.convertStringToDouble((String) valor);
				}
				preencherValor(objeto, nomePropriedade, valor, type);
			}
		}
	}

	private static void preencherValor(Object objeto, String nomePropriedade, Object valor, Class<?> type) {
		if (valor == null) {
			if (type.getName() == "int") {
				valor = 0;
			} else if (type.getName() == "double") {
				valor = 0D;
			} else if (type.getName() == "long") {
				valor = 0L;
			}
		}
		new Mirror().on(objeto).set().field(nomePropriedade).withValue(valor);
	}

	public static Object invoke(Object objeto, String metodo, Object... args) {
		Object result = new Mirror().on(objeto).invoke().method(metodo).withArgs(args);
		return result;
	}

	public static List<Field> getCamposAnotados(Class<?> classe, Class<? extends Annotation> annotation) {
		List<Field> fields = new ArrayList<>();
		MirrorList<Field> campos = new Mirror().on(classe).reflectAll().fields();
		for (Field field : campos) {
			if (field.isAnnotationPresent(annotation)) {
				fields.add(field);
			}
		}

		return fields;
	}
}
