package com.officetourisme.services;

import com.officetourisme.dtos.OptionDto;

import java.util.List;

public interface OptionService {
    OptionDto saveOption(OptionDto optionDto);
    OptionDto getOptionById(Long optionId);
    boolean deleteOption(Long optionId);
    List<OptionDto> getAllOptions();
}
