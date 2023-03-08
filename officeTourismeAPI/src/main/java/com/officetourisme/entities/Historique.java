/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author john-
 */
@Entity
@Table(name = "t_historique_his")
@NamedQueries({
    @NamedQuery(name = "Historique.findAll", query = "SELECT h FROM Historique h"),
    @NamedQuery(name = "Historique.findByHisId", query = "SELECT h FROM Historique h WHERE h.id = :hisId"),
    @NamedQuery(name = "Historique.findByChoId", query = "SELECT h FROM Historique h WHERE h.choix = :choId")})
public class Historique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "his_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cho_id")
    private String choix;
    @JoinColumn(name = "cpt_id", referencedColumnName = "cpt_id")
    @ManyToOne(optional = false)
    private Compte compte;
    @JoinColumn(name = "sor_id", referencedColumnName = "sor_id")
    @ManyToOne(optional = false)
    private Sortie sortie;

    public Historique() {
    }

    public Historique(Long id) {
        this.id = id;
    }

    public Historique(Long id, String choix) {
        this.id = id;
        this.choix = choix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long hisId) {
        this.id = hisId;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choId) {
        this.choix = choId;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte cptId) {
        this.compte = cptId;
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
        if (!(object instanceof Historique)) {
            return false;
        }
        Historique other = (Historique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historique[ hisId=" + id + " ]";
    }
    
}
