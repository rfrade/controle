package com.projetos.controle_negocio.repositoy;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_entities.base.Entidade;

public abstract class EntidadeRepository<T extends Entidade, K extends Serializable> implements JpaRepository<T, K> {

}