package com.projetos.controle.tela.report;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
	
	public enum FormatoRelatorio{
		FORMATO_EXPORT_PDF, FORMATO_EXPORT_DOC
	}
	
	public static <T> byte[] gerarRelatorio(List<T> dados, String nomeRelatorio, FormatoRelatorio formato) throws RelatorioException{
		return gerarRelatorio(dados, nomeRelatorio, formato, new HashMap<String, Object>());
	}
	
	public static <T> byte[]  gerarRelatorio(List<T> dados, String nomeRelatorio, FormatoRelatorio formato, Map<String, Object> parametrosRelatorio) throws RelatorioException {
		if(nomeRelatorio != null && !"".equals(nomeRelatorio)){
			
			try {
				
				byte[] relatorio = null;
				InputStream resource = Thread.class.getResourceAsStream("/report/" + nomeRelatorio);
				
				switch (formato) {
				
					case FORMATO_EXPORT_PDF:
						relatorio = JasperReportsUtil.exportarParaPDF(resource, dados, parametrosRelatorio);
						break;
					case FORMATO_EXPORT_DOC:
						
						break;
				
				}

				return relatorio;
			} catch (Exception e) {
				throw new RelatorioException(e);
			}

		} else{
			throw new RelatorioException("relatorio.nome_do_relatorio_em_branco");
		}
	}

}
