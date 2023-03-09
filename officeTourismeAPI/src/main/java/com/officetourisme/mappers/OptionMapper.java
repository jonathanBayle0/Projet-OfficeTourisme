package com.officetourisme.mappers;

import com.officetourisme.dtos.OptionDto;
import com.officetourisme.entities.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OptionDto optionEntityToDto(Option option) {
        return modelMapper.map(option, OptionDto.class);
    }

    public Option optionDtoToEntity(OptionDto optionDto) {
        return modelMapper.map(optionDto, Option.class);
    }
}
