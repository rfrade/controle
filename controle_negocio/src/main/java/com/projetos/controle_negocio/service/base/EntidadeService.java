package com.projetos.controle_negocio.service.base;

import com.projetos.controle_entiies.base.Entidade;
import com.projetos.controle_negocio.dao.EntidadeDAO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rafael Frade - 01/08/2013
 */
@Transactional
public abstract class EntidadeService<T extends Entidade> {

    private EntidadeDAO entidadeDAO;

    public void salvar(T t) {
        entidadeDAO.salvar(t);
    }

    public void remover(T t) {
        entidadeDAO.remover(t);
    }

    public EntidadeDAO getEntidadeDAO() {
        return entidadeDAO;
    }

    public void setEntidadeDAO(EntidadeDAO entidadeDAO) {
        this.entidadeDAO = entidadeDAO;
    }

    public List<T> listar() {
        return entidadeDAO.listar();
    }

}
