package com.projetos.controle.tela;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

public class PedidoCadastroControllerTest {

	protected Logger log = Logger.getLogger(this.getClass());

	@Test
	public void calcularValorDesconto() {
		BigDecimal valor1 = new BigDecimal("12");
		BigDecimal valor2 = new BigDecimal("6");
		BigDecimal valor3 = new BigDecimal("6");
		BigDecimal valor4 = new BigDecimal("6");

		List<BigDecimal> descontos = Arrays.asList(valor1, valor2, valor3, valor4);
		
		BigDecimal descontoFinal = BigDecimal.ZERO;
		BigDecimal cem = new BigDecimal(100);
		for (BigDecimal valor : descontos) {
			if (valor != null && !valor.equals(BigDecimal.ZERO)) {
				BigDecimal valorAbatido = valor.divide(cem).multiply(descontoFinal);
				descontoFinal = descontoFinal.add(valor.subtract(valorAbatido));
			}
		}
		
		descontoFinal = descontoFinal.setScale(2, RoundingMode.DOWN);
		Assert.assertEquals("26.90", descontoFinal.toString());
		
	}

}
