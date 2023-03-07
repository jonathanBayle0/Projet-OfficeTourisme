package com.officetourisme.services;

import com.officetourisme.dtos.CompteDto;

import java.util.List;

public interface CompteService {
    CompteDto saveCompte(CompteDto compteDto);
    CompteDto getCompteById(Integer compteId);
    boolean deleteCompte(Integer compteId);
    List<CompteDto> getAllComptes();
}
