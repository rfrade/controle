package com.projetos.controle.tela.report;

import java.util.Map;

import com.projetos.controle_util.conversao.NumberUtil;

/**
 * Classe com métodos úteis para geração de relatório
 * @author Rafael
 */
public class RelatorioUtil {

	public static void preencherParametro(Map<String, Object> map, String parametro, Object valor) {
		if (valor != null) {
			map.put(parametro, valor.toString());
		} else {
			map.put(parametro, "");
		}
	}
	
	public static void preencherParametro(Map<String, Object> map, String parametro, Double valor) {
		if (valor != null) {
			map.put(parametro, NumberUtil.convertDoubleToString(valor));
		} else {
			map.put(parametro, "");
		}
	}

}
