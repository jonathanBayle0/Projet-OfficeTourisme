package com.officetourisme.services;

import com.officetourisme.dtos.PanierDto;

import java.util.List;

public interface PanierService {
    PanierDto savePanier(PanierDto panierDto);
    PanierDto getPanierById(Integer panierId);
    boolean deletePanier(Integer panierId);
    List<PanierDto> getAllPaniers();
}
