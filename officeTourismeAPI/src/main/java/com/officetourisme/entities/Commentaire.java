/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.officetourisme.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author jonathan
 */
@Document(collection = "t_commentaire_com")
public class Commentaire {

    @Id
    private String id;
    @Field("com_type")
    private String type;
    @Field("com_date")
    private String date;
    @Field("com_contenu")
    private String contenu;
    @Field("cpt_id")
    private String compteId;
    @Field("sor_id")
    private String sortieId;

    public Commentaire() {
    }

    public Commentaire(String id, String type, String date, String contenu, String compteId, String sortieId) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.contenu = contenu;
        this.compteId = compteId;
        this.sortieId = sortieId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getCompteId() {
        return compteId;
    }

    public void setCompteId(String compteId) {
        this.compteId = compteId;
    }

    public String getSortieId() {
        return sortieId;
    }

    public void setSortieId(String sortieId) {
        this.sortieId = sortieId;
    }
}
