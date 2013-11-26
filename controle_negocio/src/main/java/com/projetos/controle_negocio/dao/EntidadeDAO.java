package com.projetos.controle_negocio.dao;

import com.projetos.controle_entities.base.Entidade;
import java.util.List;

/**
 *
 * @author Rafael
 */
public abstract class EntidadeDAO<T extends Entidade> {

    public void salvar(T entidade) {
        
    }

    public void remover(T entidade) {
        
    }

    public List<T> listar() {
        return null;
    }

}
