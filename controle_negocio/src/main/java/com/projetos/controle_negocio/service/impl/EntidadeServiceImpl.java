package com.projetos.controle_negocio.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;
import com.projetos.controle_negocio.service.base.EntidadeService;

/**
 * @author Rafael Frade - 01/08/2013
 */

@Transactional
public abstract class EntidadeServiceImpl<T extends Entidade> implements EntidadeService<T> {

    protected abstract EntidadeRepository<T> getRepository();

    public void salvar(T t) {
    	getRepository().save(t);
    }
    
    public void remover(T t) {
    	getRepository().delete(t);
    }

    public void remover(Integer k) {
    	getRepository().delete(k);
    }

    public List<T> listar() {
        return getRepository().findAll();
    }

}