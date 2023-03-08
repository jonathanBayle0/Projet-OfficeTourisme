package com.officetourisme.services;

import com.officetourisme.dtos.CompteDto;

import java.util.List;

public interface CompteService {
    CompteDto saveCompte(CompteDto compteDto);
    CompteDto getCompteById(Long compteId);
    boolean deleteCompte(Long compteId);
    List<CompteDto> getAllComptes();
}
