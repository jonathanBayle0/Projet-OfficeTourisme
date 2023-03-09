/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sor_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "sor_nom")
    private String nom;
    @Column(name = "sor_description")
    private String description;
    @Basic(optional = false)
    @Column(name = "sor_date_debut")
    private Timestamp dateDebut;
    @Basic(optional = false)
    @Column(name = "sor_date_fin")
    private Timestamp dateFin;
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
    private Set<Panier> panier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sortie")
    private Set<SortieOption> sortieOptions;

    public Sortie() {
    }

    public Sortie(Long id, String nom, String description, Timestamp dateDebut, Timestamp dateFin, float prix, String adresse, int capacite, Set<Historique> historique, Set<Panier> panier, Set<SortieOption> sortieOptions) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.adresse = adresse;
        this.capacite = capacite;
        this.historique = historique;
        this.panier = panier;
        this.sortieOptions = sortieOptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Set<Historique> getHistorique() {
        return historique;
    }

    public void setHistorique(Set<Historique> historique) {
        this.historique = historique;
    }

    public Set<Panier> getPanier() {
        return panier;
    }

    public void setPanier(Set<Panier> panier) {
        this.panier = panier;
    }

    public Set<SortieOption> getSortieOptions() {
        return sortieOptions;
    }

    public void setSortieOptions(Set<SortieOption> sortieOptions) {
        this.sortieOptions = sortieOptions;
    }
}
