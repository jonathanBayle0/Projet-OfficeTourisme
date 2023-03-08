package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PanierDto {
    private Long id;
    private Long compteId;
    private Long sortieId;
}
