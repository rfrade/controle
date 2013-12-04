package com.projetos.controle_negocio.service.base;

import java.util.List;

import com.projetos.controle_entities.Entidade;

public interface EntidadeService<T extends Entidade> {

    public T salvar(T t);
    
    public void remover(T t);

    public void remover(Integer k);

    public List<T> listar();
	
}
