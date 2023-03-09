package com.officetourisme.dtos;

import com.officetourisme.entities.Commentaire;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
public class CommentaireDto implements Serializable {

    private String id;
    private String type;
    private String date;
    private String contenu;
    private String compteId;
    private String sortieId;

    public CommentaireDto(Commentaire commentaire) {
        this.id = commentaire.getId();
        this.type = commentaire.getType();
        this.date = commentaire.getDate();
        this.contenu = commentaire.getContenu();
        this.compteId = commentaire.getCompteId();
        this.sortieId = commentaire.getSortieId();
    }

    public CommentaireDto() {
    }
}
