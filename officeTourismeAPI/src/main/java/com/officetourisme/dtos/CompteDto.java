package com.officetourisme.dtos;

import com.officetourisme.entities.Commentaire;
import com.officetourisme.entities.Historique;
import com.officetourisme.entities.Panier;
import lombok.*;

import java.util.Set;

@Data
@Setter
@Getter
public class CompteDto {
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private Character statut;
    private Set<HistoriqueDto> historique;
    private Set<PanierDto> panier;
}
