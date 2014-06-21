package com.projetos.controle_negocio.repositoy;

import com.projetos.controle_entities.Parametro;

public interface ParametroRepository extends EntidadeRepository<Parametro> {

	Parametro getParametroByChave(String chave);

}
