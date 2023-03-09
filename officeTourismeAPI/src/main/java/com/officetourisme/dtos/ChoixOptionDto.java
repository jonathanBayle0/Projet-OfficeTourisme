package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Data
@Getter
@Setter
public class ChoixOptionDto {
    private Long id;
    private HistoriqueDto historique;
    private OptionDto option;
}
