package com.projetos.controle_negocio.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.projetos.controle_entities.Entidade;
import com.projetos.controle_entities.QFornecedor;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.service.base.EntidadeService;
import com.projetos.controle_util.collection.ColecaoUtil;
import com.projetos.controle_util.reflection.BeanUtil;

/**
 * @author Rafael Frade - 01/08/2013
 */

@Transactional
public abstract class EntidadeServiceImpl<T extends Entidade> implements EntidadeService<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Logger log = Logger.getLogger(this.getClass());

	protected abstract EntidadeRepository<T> getRepository();

    public T salvar(T t) {
    	return getRepository().save(t);
    }
    
    public void remover(T t) {
    	getRepository().delete(t);
    }

    public void remover(Integer k) {
    	getRepository().delete(k);
    }

    @Override
    public T findById(Integer id) {
    	return getRepository().findOne(id);
    }

    public List<T> listar() {
        return getRepository().findAll();
    }

    public abstract EntityPathBase<T> getEntityPathBase();

    @Override
    public List<T> filtrar(Filtro... filtros) {
    	List<Filtro> list = Arrays.asList(filtros);
    	return filtrar(list);
    }

	@Override
    public List<T> filtrar(List<Filtro> filtros) {
		
		List<BooleanExpression> predicados = new ArrayList<>();
		EntityPathBase<T> qClass = getEntityPathBase();
		
		for (Filtro filtro : filtros) {

			if (filtro.isValido()) {
				Object path = BeanUtil.getPropriedade(qClass, filtro.getNomePropriedade());
				String metodoComparacao = filtro.getComparador().getMetodo();
				BooleanExpression expression = (BooleanExpression)BeanUtil.invoke(path, metodoComparacao, filtro.getValor());
				predicados.add(expression);
			}
		}

		Predicate[] predicadosArray = predicados.toArray(new Predicate[predicados.size()]);
		JPAQuery jpaQuery = new JPAQuery(entityManager);
		List<T> resultado = (List<T>) jpaQuery.from(qClass).where(predicadosArray).list(qClass);

		return ColecaoUtil.converterParaLista(resultado);
    }

	/*private Predicate getPredicados(List<BooleanExpression> predicados) {
		BooleanExpression predicado = predicados.get(0);
		for (int i = 1; i < predicados.size(); i++) {
			predicado = predicado.and(predicados.get(i));
		}
		return predicado;
	}*/

}