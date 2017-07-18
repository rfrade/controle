package com.projetos.controle_negocio.repositoy;

import com.projetos.controle_entities.Parametro;

/**
 * Essa interface não tem implementação pois a implementação é gerada
 * automaticamente pela API de JPA do spring
 * @author Rafael
 */
public interface ParametroRepository extends EntidadeRepository<Parametro> {

	Parametro getParametroByChave(String chave);

}
