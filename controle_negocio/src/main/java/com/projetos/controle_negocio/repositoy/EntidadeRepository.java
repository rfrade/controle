package com.projetos.controle_negocio.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.projetos.controle_entities.Entidade;

public interface EntidadeRepository<T extends Entidade> extends JpaRepository<T, Integer>, QueryDslPredicateExecutor<T> {

}