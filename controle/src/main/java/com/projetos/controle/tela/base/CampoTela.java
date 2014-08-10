package com.projetos.controle.tela.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CampoTela {

	String bean();
	int tamanho() default 200;
	String nome() default "";
	TipoCampo tipoCampo() default TipoCampo.STRING;
}
