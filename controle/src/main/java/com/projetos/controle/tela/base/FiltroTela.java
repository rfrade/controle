package com.projetos.controle.tela.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.OperadorLogico;
import com.projetos.controle_negocio.filtro.TipoFiltro;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FiltroTela {

	String campo();
	TipoFiltro tipo();
	Comparador comparador() default Comparador.CONTAINS;
	OperadorLogico operador() default OperadorLogico.AND;

}
