package com.projetos.controle_negocio.service.base;

import java.util.List;

import com.projetos.controle_entities.Entidade;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.Ordenacao;

public interface EntidadeService<T extends Entidade> {

    public T salvar(T t);
    
    public void remover(T t);

    public void remover(Integer id);

    public T findById(Integer id);

    public List<T> listar();

    public List<T> filtrar(List<Filtro> filtros);

    public List<T> filtrar(List<Filtro> filtros, List<Ordenacao> ordenacoes);

    public List<T> filtrar(Filtro... filtros);
}
