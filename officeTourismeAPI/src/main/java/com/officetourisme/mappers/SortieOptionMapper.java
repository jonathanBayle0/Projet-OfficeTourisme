package com.officetourisme.mappers;

import com.officetourisme.dtos.SortieOptionDto;
import com.officetourisme.entities.SortieOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortieOptionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SortieOptionDto sortieOptionEntityToDto(SortieOption sortieOption) {
        return modelMapper.map(sortieOption, SortieOptionDto.class);
    }

    public SortieOption sortieOptionDtoToEntity(SortieOptionDto sortieOptionDto) {
        return modelMapper.map(sortieOptionDto, SortieOption.class);
    }
}
