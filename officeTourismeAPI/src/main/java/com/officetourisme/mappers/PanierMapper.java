package com.officetourisme.mappers;

import com.officetourisme.dtos.PanierDto;
import com.officetourisme.entities.Panier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PanierDto panierEntityToDto(Panier panier) {
        return modelMapper.map(panier, PanierDto.class);
    }

    public Panier panierDtoToEntity(PanierDto panierDto) {
        return modelMapper.map(panierDto, Panier.class);
    }
}
