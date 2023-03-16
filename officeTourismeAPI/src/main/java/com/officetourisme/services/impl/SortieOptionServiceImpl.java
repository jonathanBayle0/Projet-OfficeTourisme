package com.officetourisme.services.impl;

import com.officetourisme.dtos.SortieOptionDto;
import com.officetourisme.entities.SortieOption;
import com.officetourisme.mappers.SortieOptionMapper;
import com.officetourisme.repositories.SortieOptionRepository;
import com.officetourisme.services.SortieOptionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("SortieOptionService")
public class SortieOptionServiceImpl implements SortieOptionService {

    private final SortieOptionRepository sortieOptionRepository;
    private final SortieOptionMapper sortieOptionMapper;

    public SortieOptionServiceImpl(SortieOptionRepository sortieOptionRepository, SortieOptionMapper sortieOptionMapper) {
        this.sortieOptionRepository = sortieOptionRepository;
        this.sortieOptionMapper = sortieOptionMapper;
    }

    @Override
    public SortieOptionDto saveSortieOption(SortieOptionDto sortieOptionDto) {
        SortieOption sortieOption = sortieOptionMapper.sortieOptionDtoToEntity(sortieOptionDto);
        sortieOptionRepository.save(sortieOption);
        return sortieOptionDto;
    }

    @Override
    public SortieOptionDto getSortieOptionById(Long sortieOptionId) {
        SortieOption sortieOption = sortieOptionRepository.findById(sortieOptionId).orElseThrow(() -> new EntityNotFoundException("SortieOption inexistant"));
        return sortieOptionMapper.sortieOptionEntityToDto(sortieOption);
    }

    /**
     * Permet de supprimer un option d'une sortie directement avec l'id de l'option de sortie
     * @param sortieOptionId
     * @return
     */
    @Override
    public boolean deleteSortieOption(Long sortieOptionId) {
        sortieOptionRepository.findById(sortieOptionId).orElseThrow(() -> new EntityNotFoundException("SortieOption inexistant"));
        sortieOptionRepository.deleteById(sortieOptionId);
        return true;
    }

    @Override
    public List<SortieOptionDto> getAllSortieOptions() {
        List<SortieOptionDto> sortieOptionsDto = new ArrayList<>();
        List<SortieOption> sortieOptions = sortieOptionRepository.findAll();
        // Transformation de l'ensemble de la liste des sortieOptions en dto
        sortieOptions.forEach(sortieOption -> {
            sortieOptionsDto.add(sortieOptionMapper.sortieOptionEntityToDto(sortieOption));
        });
        return sortieOptionsDto;
    }

    /**
     * Permet de supprimer une option de sortie avec passer en parametre
     * @param sortieId
     * @param optionId
     * @return
     */
    public boolean deleteSortieOption(SortieOptionDto sortieOption) {
        Long sortieId = sortieOption.getSortieId();
        Long optionId = sortieOption.getOptionId();
        List<SortieOptionDto> sortieOptionDtos = this.getAllSortieOptions();

        for (SortieOptionDto sortieOptionDto : sortieOptionDtos) {
            // Si une entite est une liaison entre la sortie et l'option, on la supprime
            if (sortieOptionDto.getSortieId() == sortieId && sortieOptionDto.getOptionId() == optionId) {
                this.deleteSortieOption(sortieOptionDto.getId());
                return true;
            }
        }

        return false;
    }
}
