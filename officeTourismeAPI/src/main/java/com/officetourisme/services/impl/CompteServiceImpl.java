package com.officetourisme.services.impl;

import com.officetourisme.dtos.CompteDto;
import com.officetourisme.entities.Compte;
import com.officetourisme.mappers.CompteMapper;
import com.officetourisme.repositories.CompteRepository;
import com.officetourisme.services.CompteService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("CompteService")
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public CompteDto saveCompte(CompteDto compteDto) {
        if (isMailExist(compteDto)) throw new EntityExistsException();
        Compte compte = compteMapper.compteDtoToEntity(compteDto);
        compteRepository.save(compte);
        return compteDto;
    }

    @Override
    public CompteDto getCompteById(Long compteId) {
        Compte compte = compteRepository.findById(compteId).orElseThrow(() -> new EntityNotFoundException("Compte inexistant"));
        return compteMapper.compteEntityToDto(compte);
    }

    @Override
    public boolean deleteCompte(Long compteId) {
        compteRepository.findById(compteId).orElseThrow(() -> new EntityNotFoundException("Compte inexistant"));
        compteRepository.deleteById(compteId);
        return true;
    }

    @Override
    public List<CompteDto> getAllComptes() {
        List<CompteDto> comptesDto = new ArrayList<>();
        List<Compte> comptes = compteRepository.findAll();
        // Transformation de l'ensemble de la liste des comptes en dto
        comptes.forEach(compte -> {
            comptesDto.add(compteMapper.compteEntityToDto(compte));
        });
        return comptesDto;
    }

    /**
     * Vérifie si le mail est déjà utilisé pour un autre compte
     * @param compte
     * @return true si le mail est déjà utilisé, false sinon
     */
    public boolean isMailExist(CompteDto compte) {
        List<CompteDto> comptes = this.getAllComptes();
        for(CompteDto cpt : comptes) {
            // On si vérifie que l'email existe (et que les comptes sont différents)
            if(compte.getMail().equals(cpt.getMail()) && compte.getMail() != cpt.getMail()) return true;
        }
        return false;
    }
}
