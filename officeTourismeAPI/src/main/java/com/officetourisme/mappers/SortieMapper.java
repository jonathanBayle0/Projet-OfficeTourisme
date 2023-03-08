package com.officetourisme.mappers;

import com.officetourisme.dtos.SortieDto;
import com.officetourisme.entities.Sortie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SortieMapper {
    private ModelMapper modelMapper;

    public SortieDto sortieEntityToDto(Sortie Sortie) {
        return modelMapper.map(Sortie, SortieDto.class);
    }

    public Sortie sortieDtoToEntity(SortieDto SortieDto) {
        return modelMapper.map(SortieDto, Sortie.class);
    }
}
