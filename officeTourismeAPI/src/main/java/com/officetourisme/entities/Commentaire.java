/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author john-
 */
@Entity
@Table(name = "t_commentaire_com")
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c"),
    @NamedQuery(name = "Commentaire.findByComId", query = "SELECT c FROM Commentaire c WHERE c.id = :comId"),
    @NamedQuery(name = "Commentaire.findByComType", query = "SELECT c FROM Commentaire c WHERE c.type = :comType"),
    @NamedQuery(name = "Commentaire.findByComDate", query = "SELECT c FROM Commentaire c WHERE c.date = :comDate")})
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "com_type")
    private int type;
    @Basic(optional = false)
    @Column(name = "com_date")
    private Timestamp date;
    @Column(name = "com_contenu")
    private String contenu;
    @JoinColumn(name = "cpt_id", referencedColumnName = "cpt_id")
    @ManyToOne(optional = false)
    private Compte compte;
    @JoinColumn(name = "sor_id", referencedColumnName = "sor_id")
    @ManyToOne(optional = false)
    private Sortie sortie;

    public Commentaire() {
    }

    public Commentaire(Long id) {
        this.id = id;
    }

    public Commentaire(Long id, int type, Timestamp date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long comId) {
        this.id = comId;
    }

    public int getType() {
        return type;
    }

    public void setType(int comType) {
        this.type = comType;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp comDate) {
        this.date = comDate;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String comContenu) {
        this.contenu = comContenu;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commentaire[ comId=" + id + " ]";
    }
    
}
