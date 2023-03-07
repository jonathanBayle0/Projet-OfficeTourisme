/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;

/**
 *
 * @author john-
 */
@Entity
@Table(name = "t_sortie_sor")
@NamedQueries({
    @NamedQuery(name = "Sortie.findAll", query = "SELECT s FROM Sortie s"),
    @NamedQuery(name = "Sortie.findBySorId", query = "SELECT s FROM Sortie s WHERE s.id = :sorId"),
    @NamedQuery(name = "Sortie.findBySorNom", query = "SELECT s FROM Sortie s WHERE s.nom = :sorNom"),
    @NamedQuery(name = "Sortie.findBySorDescription", query = "SELECT s FROM Sortie s WHERE s.description = :sorDescription"),
    @NamedQuery(name = "Sortie.findBySordateDebut", query = "SELECT s FROM Sortie s WHERE s.dateDebut = :sordateDebut"),
    @NamedQuery(name = "Sortie.findBySordateFin", query = "SELECT s FROM Sortie s WHERE s.dateFin = :sordateFin"),
    @NamedQuery(name = "Sortie.findBySorPrix", query = "SELECT s FROM Sortie s WHERE s.prix = :sorPrix"),
    @NamedQuery(name = "Sortie.findBySorAdresse", query = "SELECT s FROM Sortie s WHERE s.adresse = :sorAdresse"),
    @NamedQuery(name = "Sortie.findBySorCapacite", query = "SELECT s FROM Sortie s WHERE s.capacite = :sorCapacite")})
public class Sortie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sor_id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "sor_nom")
    private String nom;
    @Column(name = "sor_description")
    private String description;
    @Basic(optional = false)
    @Column(name = "sor_dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "sor_dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "sor_prix")
    private float prix;
    @Basic(optional = false)
    @Column(name = "sor_adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "sor_capacite")
    private int capacite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sortie")
    private Set<Historique> historique;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sortie")
    private Set<Commentaire> commentaires;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sortie")
    private Set<Panier> panier;

    public Sortie() {
    }

    public Sortie(Integer id) {
        this.id = id;
    }

    public Sortie(Integer id, String nom, Date dateDebut, Date dateFin, float prix, String adresse, int capacite) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.adresse = adresse;
        this.capacite = capacite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer sorId) {
        this.id = sorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String sorNom) {
        this.nom = sorNom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String sorDescription) {
        this.description = sorDescription;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date sordateDebut) {
        this.dateDebut = sordateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date sordateFin) {
        this.dateFin = sordateFin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float sorPrix) {
        this.prix = sorPrix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String sorAdresse) {
        this.adresse = sorAdresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int sorCapacite) {
        this.capacite = sorCapacite;
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
        if (!(object instanceof Sortie)) {
            return false;
        }
        Sortie other = (Sortie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sortie[ sorId=" + id + " ]";
    }
    
}
