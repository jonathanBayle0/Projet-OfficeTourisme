package com.officetourisme.services.impl;

import com.officetourisme.dtos.PanierDto;
import com.officetourisme.entities.Panier;
import com.officetourisme.mappers.PanierMapper;
import com.officetourisme.repositories.PanierRepository;
import com.officetourisme.services.PanierService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("PanierService")
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierMapper panierMapper;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    @Override
    public PanierDto savePanier(PanierDto panierDto) {
        Panier panier = panierMapper.panierDtoToEntity(panierDto);
        panierRepository.save(panier);
        return panierDto;
    }

    @Override
    public PanierDto getPanierById(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new EntityNotFoundException("Panier inexistant"));
        return panierMapper.panierEntityToDto(panier);
    }

    @Override
    public boolean deletePanier(Long panierId) {
        panierRepository.findById(panierId).orElseThrow(() -> new EntityNotFoundException("Panier inexistant"));
        panierRepository.deleteById(panierId);
        return true;
    }

    @Override
    public List<PanierDto> getAllPaniers() {
        List<PanierDto> paniersDto = new ArrayList<>();
        List<Panier> paniers = panierRepository.findAll();
        // Transformation de l'ensemble de la liste des paniers en dto
        paniers.forEach(panier -> {
            paniersDto.add(panierMapper.panierEntityToDto(panier));
        });
        return paniersDto;
    }
}
