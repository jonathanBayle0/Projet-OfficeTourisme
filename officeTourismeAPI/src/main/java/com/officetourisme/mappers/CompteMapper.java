package com.officetourisme.mappers;

import com.officetourisme.dtos.CompteDto;
import com.officetourisme.entities.Compte;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CompteDto compteEntityToDto(Compte compte) {
        CompteDto compteDto = new CompteDto();
        compteDto = modelMapper.map(compte, CompteDto.class);
        return compteDto;
    }

    public Compte compteDtoToEntity(CompteDto compteDto) {
        Compte compte = new Compte();
        compte = modelMapper.map(compteDto, Compte.class);
        return compte;
    }
}
