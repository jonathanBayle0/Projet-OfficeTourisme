package com.officetourisme.services.impl;

import com.officetourisme.dtos.HistoriqueDto;
import com.officetourisme.dtos.SortieDto;
import com.officetourisme.entities.Historique;
import com.officetourisme.mappers.HistoriqueMapper;
import com.officetourisme.repositories.HistoriqueRepository;
import com.officetourisme.services.HistoriqueService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("HistoriqueService")
public class HistoriqueServiceImpl implements HistoriqueService {
    private final HistoriqueRepository historiqueRepository;
    private final HistoriqueMapper historiqueMapper;
    private final SortieServiceImpl sortieService;
    public HistoriqueServiceImpl(HistoriqueRepository historiqueRepository, HistoriqueMapper historiqueMapper, SortieServiceImpl sortieService) {
        this.historiqueRepository = historiqueRepository;
        this.historiqueMapper = historiqueMapper;
        this.sortieService = sortieService;
    }

    @Override
    public HistoriqueDto saveHistorique(HistoriqueDto historiqueDto) {
        Historique historique = historiqueMapper.historiqueDtoToEntity(historiqueDto);
        historiqueRepository.save(historique);
        return historiqueDto;
    }

    @Override
    public HistoriqueDto getHistoriqueById(Long historiqueId) {
        Historique historique = historiqueRepository.findById(historiqueId).orElseThrow(() -> new EntityNotFoundException("Historique inexistant"));
        return historiqueMapper.historiqueEntityToDto(historique);
    }

    @Override
    public boolean deleteHistorique(Long historiqueId) {
        historiqueRepository.findById(historiqueId).orElseThrow(() -> new EntityNotFoundException("Historique inexistant"));
        historiqueRepository.deleteById(historiqueId);
        return true;
    }

    @Override
    public List<HistoriqueDto> getAllHistoriques() {
        List<HistoriqueDto> historiquesDto = new ArrayList<>();
        List<Historique> historiques = historiqueRepository.findAll();
        // Transformation de l'ensemble de la liste des historiques en dto
        historiques.forEach(historique -> {
            historiquesDto.add(historiqueMapper.historiqueEntityToDto(historique));
        });
        return historiquesDto;
    }

    /**
     * Retourne l'ensemble des sorties de l'historique d'un compte
     * @param compteId
     * @return
     */
    public List<SortieDto> getSortieFromCompte(Long compteId) {
        List<HistoriqueDto> historiqueDtos = this.getAllHistoriques();
        List<SortieDto> sorties = new ArrayList<>();

        for (HistoriqueDto historique : historiqueDtos) {
            // Recuperation des sorties de l'historique d'un compte
            if (historique.getCompteId() == compteId)
                sorties.add(sortieService.getSortieById(historique.getSortieId()));
        }
        return sorties;
    }
}
