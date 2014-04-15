package com.projetos.controle_util.conversao;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void convertDateToStringTeste() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 13);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.YEAR, 2013);
		Assert.assertEquals("13/06/2013", DateUtil.convertDateToString(cal.getTime()));
	}

}
