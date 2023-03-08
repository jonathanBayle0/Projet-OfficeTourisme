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
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private Character statut;
    private Set<Historique> historique;
    private Set<Commentaire> commentaires;
    private Set<Panier> panier;
}
