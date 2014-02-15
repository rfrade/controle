package com.projetos.controle;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projetos.controle.tela.controller.ClienteListaController;

/**
 *
 * @author Rafael
 */
public class ConfiguracoesTest { 

    Logger log = Logger.getLogger(this.getClass());
    
    @Test
    public void iniciarContextoTest() {
        log.info("asdf");
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/applicationContext.xml"});
        log.info(context.getBean(ClienteListaController.class));
        Assert.assertNotNull(context);
    }

}
