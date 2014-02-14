package com.projetos.controle.tela.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods annotated with FormBinding has the method bindFormToBean called before the execution
 * and the method bindBeanToForm called after.
 * 
 * @author Rafael
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormBinding {

}