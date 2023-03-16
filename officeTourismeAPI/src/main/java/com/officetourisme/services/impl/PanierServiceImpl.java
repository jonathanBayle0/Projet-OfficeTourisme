package com.officetourisme.services.impl;

import com.officetourisme.dtos.HistoriqueDto;
import com.officetourisme.dtos.PanierDto;
import com.officetourisme.dtos.SortieDto;
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
    private final SortieServiceImpl sortieService;
    private final HistoriqueServiceImpl historiqueService;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper, SortieServiceImpl sortieService, HistoriqueServiceImpl historiqueService) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
        this.sortieService = sortieService;
        this.historiqueService = historiqueService;
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

    /**
     * Retourne l'ensemble des sorties du panier d'un compte
     * @param compteId
     * @return
     */
    public List<SortieDto> getSortieFromCompte(Long compteId) {
        List<PanierDto> panierDtos = this.getAllPaniers();
        List<SortieDto> sorties = new ArrayList<>();

        for (PanierDto panier : panierDtos) {
            // Recuperation des sorties du panier d'un compte
            if (panier.getCompteId() == compteId)
                sorties.add(sortieService.getSortieById(panier.getSortieId()));
        }
        return sorties;
    }

    /**
     * Transfert des elements du panier vers l'historique
     * @param compteId
     * @return
     */
    public boolean transfertPanierToHistorique(Long compteId) {
        List<PanierDto> panierDtos = this.getAllPaniers();
        for (PanierDto panier : panierDtos) {
            // Recuperation des informations du panier d'un compte
            if (panier.getCompteId() == compteId) {
                // Initialisation de l'historique
                HistoriqueDto hist = new HistoriqueDto();
                hist.setCompteId(panier.getCompteId());
                hist.setSortieId(panier.getSortieId());
                // Enregistrement de l'historique
                historiqueService.saveHistorique(hist);
                // Suppression dans le panier
                this.deletePanier(panier.getId());
            }
        }
        return true;
    }
}
