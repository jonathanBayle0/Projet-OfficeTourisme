package com.officetourisme.mappers;

import com.officetourisme.dtos.CompteDto;
import com.officetourisme.entities.Compte;
import org.modelmapper.ModelMapper;

public class CompteMapper {
    private ModelMapper modelMapper;

    public CompteDto compteEntityToDto(Compte compte) {
        return modelMapper.map(compte, CompteDto.class);
    }

    public Compte compteDtoToEntity(CompteDto compteDto) {
        return modelMapper.map(compteDto, Compte.class);
    }
}
