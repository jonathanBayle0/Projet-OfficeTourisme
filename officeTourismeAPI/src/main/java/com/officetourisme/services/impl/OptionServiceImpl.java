package com.officetourisme.services.impl;

import com.officetourisme.dtos.OptionDto;
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

    public OptionServiceImpl(OptionRepository optionRepository, OptionMapper optionMapper) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
    }

    @Override
    public OptionDto saveOption(OptionDto optionDto) {
        Option option = optionMapper.optionDtoToEntity(optionDto);
        optionRepository.save(option);
        return optionDto;
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
}
