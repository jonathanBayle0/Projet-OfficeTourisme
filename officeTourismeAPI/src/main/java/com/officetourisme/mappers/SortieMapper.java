package com.officetourisme.mappers;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.dtos.SortieDto;
import com.officetourisme.entities.Sortie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SortieMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SortieDto sortieEntityToDto(Sortie sortie) {
        return modelMapper.map(sortie, SortieDto.class);
    }

    public Sortie sortieDtoToEntity(SortieDto sortieDto) {
        return modelMapper.map(sortieDto, Sortie.class);
    }
}
