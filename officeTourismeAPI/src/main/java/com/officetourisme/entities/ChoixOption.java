/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author john-
 */
@Entity
@Table(name = "t_choixoption_cho")
@NamedQueries({
    @NamedQuery(name = "Choixoption.findAll", query = "SELECT c FROM ChoixOption c"),
    @NamedQuery(name = "Choixoption.findByChoId", query = "SELECT c FROM ChoixOption c WHERE c.id = :choId")})
public class ChoixOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cho_id")
    private Long id;
    @JoinColumn(name = "his_id", referencedColumnName = "his_id")
    @ManyToOne(optional = false)
    private Historique historique;
    @JoinColumn(name = "opt_id", referencedColumnName = "opt_id")
    @ManyToOne(optional = false)
    private Option option;

    public ChoixOption() {
    }

    public ChoixOption(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long choId) {
        this.id = choId;
    }

    public Historique getHistorique() {
        return historique;
    }

    public void setHistorique(Historique hisId) {
        this.historique = hisId;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option optId) {
        this.option = optId;
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
        if (!(object instanceof ChoixOption)) {
            return false;
        }
        ChoixOption other = (ChoixOption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Choixoption[ choId=" + id + " ]";
    }
    
}
