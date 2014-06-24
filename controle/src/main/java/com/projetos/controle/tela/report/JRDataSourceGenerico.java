package com.projetos.controle.tela.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

import com.projetos.controle_util.conversao.DateUtil;
import com.projetos.controle_util.reflection.BeanUtil;

/**
 * Classe que representa um DataSource genérico do JasperReports. <br>
 * Deve ser extendida apenas se o relatório contiver propriedades que não estão
 * no objeto T.
 * 
 * @author Rafael Frade 28/08/2012
 */
public class JRDataSourceGenerico<T> implements JRDataSource {

	private Iterator<T> iterator;
	protected static final Logger LOGGER = Logger.getLogger(JRDataSourceGenerico.class);
	protected T elemento;

	public JRDataSourceGenerico(T objeto) {
		List<T> lista = new ArrayList<T>();
		lista.add(objeto);
		this.iterator = lista.iterator();
	}

	public JRDataSourceGenerico(List<T> lista) {
		this.iterator = lista.iterator();
	}

	public boolean next() throws JRException {
		if (iterator.hasNext()) {
			elemento = iterator.next();
			return true;
		}
		return false;
	}

	/**
	 * Invocado pelo Jasper para obter os campos do relatório.<br>
	 * Se o relatório necessitar de propriedades que não são atributos<br>
	 * do objeto T, esse método deve ser extendido e devem ser adicionados<br>
	 * ifs que retornam as propriedades necessárias.
	 */
	public Object getFieldValue(JRField propriedade) throws JRException {
		Object valor = BeanUtil.getPropriedade(elemento, propriedade.getName());
				//PropertyManager.getValorCampo(propriedade.getName(), elemento);
		if (valor == null) {
			LOGGER.info("Atributo " + propriedade.getName() + " não encontrado no objeto");
			return "";
		} else if (valor instanceof Boolean) {
			return ((Boolean)valor) == true ? "SIM" : "NÃO";
		} else if (valor instanceof Date) {
			return DateUtil.convertDateToString((Date)valor);
		}

		return valor.toString();
	}

	protected T getElemento() {
		return this.elemento;
	}

}
