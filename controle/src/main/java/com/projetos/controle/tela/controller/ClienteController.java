package com.projetos.controle.tela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.controller.base.BaseController;
import com.projetos.controle_entities.Cliente;
import com.projetos.controle_negocio.service.base.ClienteService;
import com.projetos.controle_negocio.service.base.EntidadeService;

/**
 *
 * @author Rafael
 */
@Controller
public class ClienteController extends BaseController<Cliente> {

	@Autowired
	private ClienteService clienteService;

	@Override
    protected EntidadeService<Cliente> getEntidadeService() {
    	return clienteService;
    }
    
}