/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author john-
 */
@Entity
@Table(name = "t_historique_his")
@NamedQueries({
    @NamedQuery(name = "Historique.findAll", query = "SELECT h FROM Historique h"),
    @NamedQuery(name = "Historique.findByHisId", query = "SELECT h FROM Historique h WHERE h.id = :hisId")})
public class Historique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "his_id")
    private Long id;
    @JoinColumn(name = "cpt_id", referencedColumnName = "cpt_id")
    @ManyToOne(optional = false)
    private Compte compte;
    @JoinColumn(name = "sor_id", referencedColumnName = "sor_id")
    @ManyToOne(optional = false)
    private Sortie sortie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historique")
    private Set<ChoixOption> choixOptions;

    public Historique() {
    }

    public Historique(Long id, Compte compte, Sortie sortie, Set<ChoixOption> choixOptions) {
        this.id = id;
        this.compte = compte;
        this.sortie = sortie;
        this.choixOptions = choixOptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public Set<ChoixOption> getChoixOptions() {
        return choixOptions;
    }

    public void setChoixOptions(Set<ChoixOption> choixOptions) {
        this.choixOptions = choixOptions;
    }
}
