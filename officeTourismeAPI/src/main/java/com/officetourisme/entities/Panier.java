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
@Table(name = "t_panier_pan")
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p"),
    @NamedQuery(name = "Panier.findByPanId", query = "SELECT p FROM Panier p WHERE p.id = :panId")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pan_id")
    private Long id;
    @JoinColumn(name = "cpt_id", referencedColumnName = "cpt_id")
    @ManyToOne(optional = false)
    private Compte compte;
    @JoinColumn(name = "sor_id", referencedColumnName = "sor_id")
    @ManyToOne(optional = false)
    private Sortie sortie;

    public Panier() {
    }

    public Panier(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long panId) {
        this.id = panId;
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
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Panier[ panId=" + id + " ]";
    }
    
}
