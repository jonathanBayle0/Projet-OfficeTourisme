package com.officetourisme.services.impl;

import com.officetourisme.dtos.OptionDto;
import com.officetourisme.dtos.SortieOptionDto;
import com.officetourisme.entities.Option;
import com.officetourisme.mappers.OptionMapper;
import com.officetourisme.repositories.OptionRepository;
import com.officetourisme.services.OptionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("OptionService")
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;
    private final SortieOptionServiceImpl sortieOptionService;

    public OptionServiceImpl(OptionRepository optionRepository, OptionMapper optionMapper, SortieOptionServiceImpl sortieOptionService) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
        this.sortieOptionService = sortieOptionService;
    }

    @Override
    public OptionDto saveOption(OptionDto optionDto) {
        Option option = optionMapper.optionDtoToEntity(optionDto);
        Option opt = optionRepository.save(option);
        return optionMapper.optionEntityToDto(opt);
    }

    @Override
    public OptionDto getOptionById(Long optionId) {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException("Option inexistant"));
        return optionMapper.optionEntityToDto(option);
    }

    @Override
    public boolean deleteOption(Long optionId) {
        optionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException("Option inexistant"));
        optionRepository.deleteById(optionId);
        return true;
    }

    @Override
    public List<OptionDto> getAllOptions() {
        List<OptionDto> optionsDto = new ArrayList<>();
        List<Option> options = optionRepository.findAll();
        // Transformation de l'ensemble de la liste des options en dto
        options.forEach(option -> {
            optionsDto.add(optionMapper.optionEntityToDto(option));
        });
        return optionsDto;
    }

    /**
     * Recuperation de toutes les options liee a une sortie
     * @param sortieId
     * @return
     */
    public List<OptionDto> getAllOptionsBySortie(Long sortieId) {
        List<SortieOptionDto> sortieOptionDtos = sortieOptionService.getAllSortieOptions();
        List<OptionDto> optionDtos = new ArrayList<>();
        // Recuperation des liaisons entre les sorties et les option
        for (SortieOptionDto sortieOptionDto : sortieOptionDtos) {
            // Recuperation des options associe a la sortie
            if (sortieOptionDto.getSortieId() == sortieId) {
                // Ajout de l'option a la liste
                OptionDto optionDto = this.getOptionById(sortieOptionDto.getOptionId());
                optionDtos.add(optionDto);
            }
        }
        return optionDtos;
    }
}
