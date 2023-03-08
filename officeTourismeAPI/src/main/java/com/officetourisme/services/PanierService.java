package com.officetourisme.services;

import com.officetourisme.dtos.PanierDto;

import java.util.List;

public interface PanierService {
    PanierDto savePanier(PanierDto panierDto);
    PanierDto getPanierById(Long panierId);
    boolean deletePanier(Long panierId);
    List<PanierDto> getAllPaniers();
}
