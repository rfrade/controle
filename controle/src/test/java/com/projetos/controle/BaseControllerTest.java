package com.projetos.controle;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projetos.controle.tela.controller.ClienteController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/services-test.xml"})
public class BaseControllerTest {

	@Autowired
	protected ClienteController clienteController;

	public void test(){}

}
