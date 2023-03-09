package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Setter
@Getter
public class HistoriqueDto {
    private Long id;
    private Long compteId;
    private Long sortieId;
    private Set<ChoixOptionDto> choixOptions;
}
