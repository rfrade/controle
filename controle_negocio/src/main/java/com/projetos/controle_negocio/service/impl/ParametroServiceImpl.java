package com.projetos.controle_negocio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Parametro;
import com.projetos.controle_entities.QParametro;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.repositoy.ParametroRepository;
import com.projetos.controle_negocio.service.base.ParametroService;

@Service
@Lazy
public class ParametroServiceImpl extends EntidadeServiceImpl<Parametro> implements ParametroService {

	@Autowired
	private ParametroRepository parametroRepository;

	@Override
	protected EntidadeRepository<Parametro> getRepository() {
		return parametroRepository;
	}

	/**
	 * @param chave
	 * @return Parametro a partir da chave passada
	 * @throws NegocioException - caso não encontre o parametro
	 */
	public Parametro getParametroByChave(String chave) throws NegocioException {
		Parametro parametro = parametroRepository.getParametroByChave(chave);
		if (parametro == null || parametro.getValor() == null
				|| parametro.getValor().equals("")) {

			// A mensagem no formato "parametro.____" é a que está mapeada no mensagem.properties
			String message = "parametro." + chave;
			NegocioException negocioException = new NegocioException(message);
			throw negocioException;
		}

		return parametro;
	}

	@Override
	public EntityPathBase<Parametro> getEntityPathBase() {
		return QParametro.parametro;
	}

	public Parametro getCaminhoImportacaoProdutos() throws NegocioException {
		String chave = "caminho_diretorio_importacao";
		return this.getParametroByChave(chave);
	}
	
	@Override
	public Parametro getCaminhoRelatorioPedidos() throws NegocioException {
		return this.getParametroByChave("caminho_relatorio_pedido");
	}

	@Override
	public Parametro getCaminhoRelatorioRecebimentos() throws NegocioException {
		return this.getParametroByChave("caminho_relatorio_recebimentos");
	}

	@Override
	public Parametro getCaminhoBackupArquivos() throws NegocioException {
		return this.getParametroByChave("caminho_arquivos_backup");
	}
	
	@Override
	public Parametro getCaminhoMysqldump() throws NegocioException {
		return this.getParametroByChave("caminho_mysql_dump");
	}

	@Override
	public Parametro getNumeroDeMesesConsultaPedido() throws NegocioException {
		return this.getParametroByChave("numero_meses_anteriores");
	}

}
