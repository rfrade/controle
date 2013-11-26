/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetos.controle_entities;

import com.projetos.controle_entities.base.Entidade;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "tipo_empresa")
public class TipoEmpresa implements Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "indicador_tipo")
    private String indicadorTipo;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndicadorTipo() {
        return indicadorTipo;
    }

    public void setIndicadorTipo(String indicadorTipo) {
        this.indicadorTipo = indicadorTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpresa)) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.projetos.controle_entiies.TipoEmpresa[ id=" + id + " ]";
    }
    
}
