/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "t_sortieoption_sop")
@NamedQueries({
    @NamedQuery(name = "Sortieoption.findAll", query = "SELECT s FROM SortieOption s"),
    @NamedQuery(name = "Sortieoption.findBySopId", query = "SELECT s FROM SortieOption s WHERE s.id = :sopId")})
public class SortieOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sop_id")
    private Long id;
    @JoinColumn(name = "opt_id", referencedColumnName = "opt_id")
    @ManyToOne(optional = false)
    private Option option;
    @JoinColumn(name = "sor_id", referencedColumnName = "sor_id")
    @ManyToOne(optional = false)
    private Sortie sortie;

    public SortieOption() {
    }

    public SortieOption(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long sopId) {
        this.id = sopId;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option optId) {
        this.option = optId;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sorId) {
        this.sortie = sorId;
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
        if (!(object instanceof SortieOption)) {
            return false;
        }
        SortieOption other = (SortieOption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Sortieoption[ sopId=" + id + " ]";
    }
    
}
