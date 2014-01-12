package com.projetos.controle_util;

import junit.framework.Assert;

import org.junit.Test;

import com.projetos.controle_util.reflection.BeanUtil;

public class BeanUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void setPropertyTest() {
		Teste teste = new Teste();
		
		String nome = "José";
		int idade = 17;
		BeanUtil.setPropriedade(teste, "nome", nome);
		BeanUtil.setPropriedade(teste, "teste2.idade", String.valueOf(idade));
		
		Assert.assertEquals(nome, teste.getNome());
		Assert.assertEquals(idade, teste.getTeste2().getIdade());
		
		BeanUtil.setPropriedade(teste, "teste2.valor", String.valueOf(idade));
	}
	
	@Test
	public void getPropertyTest() {
		boolean truth = false;
		Teste teste = new Teste();
		teste.setTruth(truth);
		Object propriedade = BeanUtil.getPropriedade(teste, "truth");
		Assert.assertEquals(truth, propriedade);
	}
	
	public class Teste {
		private String nome;
		private boolean truth;
		private Teste2 teste2;
		
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Teste2 getTeste2() {
			return teste2;
		}

		public void setTeste2(Teste2 teste2) {
			this.teste2 = teste2;
		}

		public boolean isTruth() {
			return truth;
		}

		public void setTruth(boolean truth) {
			this.truth = truth;
		}
	}
	
	public class Teste2 {
		private int idade;

		public int getIdade() {
			return idade;
		}

		public void setIdade(int idade) {
			this.idade = idade;
		}
	}
	
}
