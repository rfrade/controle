package com.projetos.controle_negocio.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.repositoy.EntidadeRepository;

/**
 * @author Rafael Frade - 01/08/2013
 */
@Service
@Transactional
public abstract class EntidadeService<T extends Entidade> {

    private EntidadeRepository<T, Integer> repository;

    public void salvar(T t) {
    	repository.save(t);
    }
    
    public void remover(T t) {
    	repository.delete(t);
    }

    public void remover(Integer k) {
    	repository.delete(k);
    }

    public List<T> listar() {
        return repository.findAll();
    }

}
