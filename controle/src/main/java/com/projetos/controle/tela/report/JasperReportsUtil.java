package com.projetos.controle.tela.report;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

public class JasperReportsUtil {

	private static Logger log = Logger.getLogger(JasperReportsUtil.class);
	
	public static <T> byte[] exportarParaPDF(InputStream inputStreamRelatorio,List<T> listaDeDados, Map<String, Object> map) throws JRException {
		byte[] relatorio = null;

		log.info("Iniciado processamento do relatório.");
		JasperPrint relatorioImpresso = preencherRelatorio(inputStreamRelatorio, listaDeDados, map);
		relatorio = JasperExportManager.exportReportToPdf(relatorioImpresso);
		log.info("Finalizado processamento do relatório.");
		
		return relatorio;
	}
	
	public static <T> JasperPrint preencherRelatorio(
			InputStream inputStreamRelatorio, List<T> listaDeDados, Map<String, Object> map) throws JRException {
	
		byte[] bytes = JRLoader.loadBytes(inputStreamRelatorio);
//		JasperPrint print = JasperFillManager.fillReport(report, null, null);
		// exportacao do relatorio para outro formato, no caso PDF 
//		JasperExportManager.exportReportToPdfFile(print, "relatorios/RelatorioClientes.pdf");
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDeDados);

		return JasperFillManager.fillReport(inputStreamRelatorio, map,dataSource);
	}
	
}
