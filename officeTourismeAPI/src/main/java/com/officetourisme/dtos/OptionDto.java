package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Setter
@Getter
public class OptionDto {
    private Long id;
    private String sujet;
    private Set<SortieOptionDto> sortieOptions;
    private Set<ChoixOptionDto> choixOptions;
}
