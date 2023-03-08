package com.officetourisme.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Setter
@Getter
public class CommentaireDto {
    private Integer id;
    private int type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private String contenu;
    private Integer compteId;
    private Integer sortieId;
}
