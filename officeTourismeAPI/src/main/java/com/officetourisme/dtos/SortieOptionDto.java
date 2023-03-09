package com.officetourisme.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Setter
@Getter
public class SortieOptionDto {
    private Long id;
    private Long optionId;
    private Long sortieId;
}
