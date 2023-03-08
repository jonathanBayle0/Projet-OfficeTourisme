/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author john-
 */
@Entity
@Data
@Table(name = "t_compte_cpt")
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByCptId", query = "SELECT c FROM Compte c WHERE c.id = :cptId"),
    @NamedQuery(name = "Compte.findByCptNom", query = "SELECT c FROM Compte c WHERE c.nom = :cptNom"),
    @NamedQuery(name = "Compte.findByCptPrenom", query = "SELECT c FROM Compte c WHERE c.prenom = :cptPrenom"),
    @NamedQuery(name = "Compte.findByCptMail", query = "SELECT c FROM Compte c WHERE c.mail = :cptMail"),
    @NamedQuery(name = "Compte.findByCptMdp", query = "SELECT c FROM Compte c WHERE c.mdp = :cptMdp"),
    @NamedQuery(name = "Compte.findByCptStatut", query = "SELECT c FROM Compte c WHERE c.statut = :cptStatut")})
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpt_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cpt_nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "cpt_prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "cpt_mail")
    private String mail;
    @Basic(optional = false)
    @Column(name = "cpt_mdp")
    private String mdp;
    @Basic(optional = false)
    @Column(name = "cpt_statut")
    private Character statut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compte")
    private Set<Historique> historique;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compte")
    private Set<Commentaire> commentaires;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compte")
    private Set<Panier> panier;

    public Compte() {
    }

    public Compte(Long id) {
        this.id = id;
    }

    public Compte(Long id, String nom, String prenom, String mail, String mdp, Character statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long cptId) {
        this.id = cptId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String cptNom) {
        this.nom = cptNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String cptPrenom) {
        this.prenom = cptPrenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String cptMail) {
        this.mail = cptMail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String cptMdp) {
        this.mdp = cptMdp;
    }

    public Character getStatut() {
        return statut;
    }

    public void setStatut(Character cptStatut) {
        this.statut = cptStatut;
    }

    public Set<Historique> getHistorique() {
        return historique;
    }

    public void setHistorique(Set<Historique> historiqueSet) {
        this.historique = historiqueSet;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaireSet) {
        this.commentaires = commentaireSet;
    }

    public Set<Panier> getPanier() {
        return panier;
    }

    public void setPanier(Set<Panier> panierSet) {
        this.panier = panierSet;
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
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compte[ cptId=" + id + " ]";
    }
    
}
