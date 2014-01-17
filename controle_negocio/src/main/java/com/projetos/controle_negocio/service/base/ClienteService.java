package com.projetos.controle_negocio.service.base;

import java.util.List;

import com.projetos.controle_entities.Cliente;

public interface ClienteService extends EntidadeService<Cliente> {

	public List<Cliente> filtrar(Cliente filtro);

}
