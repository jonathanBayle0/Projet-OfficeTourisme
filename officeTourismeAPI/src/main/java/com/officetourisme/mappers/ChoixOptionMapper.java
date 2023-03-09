package com.officetourisme.mappers;

import com.officetourisme.dtos.ChoixOptionDto;
import com.officetourisme.entities.ChoixOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoixOptionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ChoixOptionDto choixOptionEntityToDto(ChoixOption choixOption) {
        return modelMapper.map(choixOption, ChoixOptionDto.class);
    }

    public ChoixOption choixOptionDtoToEntity(ChoixOptionDto choixOptionDto) {
        return modelMapper.map(choixOptionDto, ChoixOption.class);
    }
}
