package com.projetos.controle.tela.base;

import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author Rafael
 */
//TODO: Excluir essa classe
@Deprecated
public class AbstractFrame extends JFrame {

    protected AbstractController controller;

    protected void limparCampos() {
        Class<AbstractFrame> classe = (Class<AbstractFrame>) this.getClass();
        final Field[] fields = classe.getFields();
        for (Field field : fields) {
            if (field.getType().getName().equals(JTextField.class.getName())) {
                //JTextField textField = (JTextField) field.get;
            }
        }
    }

    /*public static <T> T getFieldValue(Field field) {
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }

        String nomeCampo = field.getName();
        String primeiraLetra = nomeCampo.substring(0, 1);
        String finalNome = nomeCampo.substring(1, nomeCampo.length());
        String nomeCampoTratado = primeiraLetra + finalNome;
        String nomeMetodo = "get" + nomeCampoTratado;
        
        field.setAccessible(accessible);
        field.get
    }*/
    
}
