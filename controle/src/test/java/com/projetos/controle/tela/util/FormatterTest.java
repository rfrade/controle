package com.projetos.controle.tela.util;

import org.junit.Test;

public class FormatterTest {

	@Test
	public void formatTest() {
		System.out.println("12345".replace("[\\d]{1}", "$+."));
	}
	
}
