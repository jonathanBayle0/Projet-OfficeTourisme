package com.officetourisme.dtos;

import com.officetourisme.entities.Compte;
import com.officetourisme.entities.Sortie;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Setter
@Getter
public class CommentaireDto {
    private Integer id;
    private int type;
    private Date date;
    private String contenu;
    private Integer compteId;
    private Integer sortieId;
}
