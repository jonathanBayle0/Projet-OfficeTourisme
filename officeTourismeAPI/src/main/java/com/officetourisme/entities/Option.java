/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "t_option_opt")
@NamedQueries({
    @NamedQuery(name = "Option.findAll", query = "SELECT o FROM Option o"),
    @NamedQuery(name = "Option.findByOptId", query = "SELECT o FROM Option o WHERE o.id = :optId"),
    @NamedQuery(name = "Option.findByOptSujet", query = "SELECT o FROM Option o WHERE o.sujet = :optSujet")})
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opt_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "opt_sujet")
    private String sujet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "option")
    private Set<SortieOption> sortieOptions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "option")
    private Set<ChoixOption> choixOptions;

    public Option() {
    }

    public Option(Long id) {
        this.id = id;
    }

    public Option(Long id, String sujet) {
        this.id = id;
        this.sujet = sujet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long optId) {
        this.id = optId;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String optSujet) {
        this.sujet = optSujet;
    }

    public Set<SortieOption> getSortieOptions() {
        return sortieOptions;
    }

    public void setSortieOptions(Set<SortieOption> sortieOptionSet) {
        this.sortieOptions = sortieOptionSet;
    }

    public Set<ChoixOption> getChoixOptions() {
        return choixOptions;
    }

    public void setChoixOptions(Set<ChoixOption> choixOptionSet) {
        this.choixOptions = choixOptionSet;
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
        if (!(object instanceof Option)) {
            return false;
        }
        Option other = (Option) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Option[ optId=" + id + " ]";
    }
    
}
