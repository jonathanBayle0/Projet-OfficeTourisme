package com.officetourisme.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Getter
@Setter
public class SortieDto {
    private Integer id;
    private String nom;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp dateDebut;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp dateFin;
    private float prix;
    private String adresse;
    private int capacite;
    private Set<HistoriqueDto> historique;
    private Set<CommentaireDto> commentaires;
    private Set<PanierDto> panier;
}
