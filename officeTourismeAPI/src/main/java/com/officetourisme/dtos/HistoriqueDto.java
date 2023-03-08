package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class HistoriqueDto {
    private Long id;
    private String choix;
    private Long compteId;
    private Long sortieId;
}
