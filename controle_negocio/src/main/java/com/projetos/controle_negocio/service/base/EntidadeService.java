package com.projetos.controle_negocio.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projetos.controle_entities.base.Entidade;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;

/**
 * @author Rafael Frade - 01/08/2013
 */
@Transactional
public abstract class EntidadeService<T extends Entidade, K extends Serializable> {

    private EntidadeRepository<T, K> repository;

    public void salvar(T t) {
    	repository.save(t);
    }
    
    public void remover(T t) {
    	repository.delete(t);
    }

    public void remover(K k) {
    	repository.delete(k);
    }

    public List<T> listar() {
        return repository.findAll();
    }

}
