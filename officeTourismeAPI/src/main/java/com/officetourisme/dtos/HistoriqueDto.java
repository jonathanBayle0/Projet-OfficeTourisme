package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class HistoriqueDto {
    private Integer id;
    private String choix;
    private Integer compteId;
    private Integer sortieId;
}
