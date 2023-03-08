package com.officetourisme.services.impl;

import com.officetourisme.dtos.SortieDto;
import com.officetourisme.entities.Sortie;
import com.officetourisme.mappers.SortieMapper;
import com.officetourisme.repositories.SortieRepository;
import com.officetourisme.services.SortieService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("SortieService")
public class SortieServiceImpl implements SortieService {

    private final SortieRepository sortieRepository;
    private final SortieMapper sortieMapper;

    public SortieServiceImpl(SortieRepository sortieRepository, SortieMapper sortieMapper) {
        this.sortieRepository = sortieRepository;
        this.sortieMapper = sortieMapper;
    }

    @Override
    public SortieDto saveSortie(SortieDto sortieDto) {
        Sortie sortie = sortieMapper.sortieDtoToEntity(sortieDto);
        sortieRepository.save(sortie);
        return sortieDto;
    }

    @Override
    public SortieDto getSortieById(Long sortieId) {
        Sortie sortie = sortieRepository.findById(sortieId).orElseThrow(() -> new EntityNotFoundException("Sortie inexistant"));
        return sortieMapper.sortieEntityToDto(sortie);
    }

    @Override
    public boolean deleteSortie(Long sortieId) {
        sortieRepository.findById(sortieId).orElseThrow(() -> new EntityNotFoundException("Sortie inexistant"));
        sortieRepository.deleteById(sortieId);
        return true;
    }

    @Override
    public List<SortieDto> getAllSorties() {
        List<SortieDto> sortiesDto = new ArrayList<>();
        List<Sortie> sorties = sortieRepository.findAll();
        // Transformation de l'ensemble de la liste des sorties en dto
        sorties.forEach(sortie -> {
            sortiesDto.add(sortieMapper.sortieEntityToDto(sortie));
        });
        return sortiesDto;
    }
}
