package com.projetos.controle.tela.base;

import javax.swing.JOptionPane;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*@Component
@Aspect*/
public class FormBindingAspect {

	/*@Around("@annotation(com.projetos.controle.tela.base.FormBinding)")*/
	public void bindingMethod(ProceedingJoinPoint pjp) throws Throwable {
		pjp.proceed();
		JOptionPane.showMessageDialog(null, "bindingMethod");
	}

}
