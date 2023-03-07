package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PanierDto {
    private Integer id;
    private Integer compteId;
    private Integer sortieId;
}
