/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetos.controle_entities;

import java.io.Serializable;

/**
 *
 * @author Rafael
 */
public interface Entidade<K> extends Serializable {

    public K getId();

}
