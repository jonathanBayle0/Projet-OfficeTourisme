package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
public class SortieDto {
    private Integer id;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private float prix;
    private String adresse;
    private int capacite;
    private Set<HistoriqueDto> historique;
    private Set<CommentaireDto> commentaires;
    private Set<PanierDto> panier;
}
