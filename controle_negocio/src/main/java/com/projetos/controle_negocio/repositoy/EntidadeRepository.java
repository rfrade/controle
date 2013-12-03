package com.projetos.controle_negocio.repositoy;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_entities.Entidade;

public interface EntidadeRepository<T extends Entidade, K extends Serializable> extends JpaRepository<T, K> {

}