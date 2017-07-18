package com.projetos.controle.tela.report;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

import com.projetos.controle_entities.ItemPedido;
import com.projetos.controle_entities.Recebimento;

/**
 * Classe que representa um DataSource genérico do JasperReports. <br>
 * Deve ser extendida apenas se o relatório contiver propriedades que não estão
 * no objeto T.
 * 
 * @author Rafael Frade 28/08/2012
 */
public class RelatorioRecebimentoDataSource extends JRDataSourceGenerico<Recebimento> {

	protected static final Logger LOGGER = Logger.getLogger(RelatorioRecebimentoDataSource.class);

	public RelatorioRecebimentoDataSource(List<Recebimento> lista) {
		super(lista);
	}

	/**
	 * Invocado pelo Jasper para obter os campos do relatório.<br>
	 * Se o relatório necessitar de propriedades que não são atributos<br>
	 * do objeto T, esse método deve ser extendido e devem ser adicionados<br>
	 * ifs que retornam as propriedades necessárias.
	 */
	public Object getFieldValue(JRField propriedade) throws JRException {
		
		if (propriedade.getName().equals("quantidadeItens")) {
			Integer quantidade = 0;
			List<ItemPedido> itensPedido = elemento.getPedido().getItensPedido();
			for (ItemPedido item : itensPedido) {
				quantidade += item.getQuantidadeTotal();
			}
			return quantidade.toString();
		}
		
		return super.getFieldValue(propriedade);
	}

}
