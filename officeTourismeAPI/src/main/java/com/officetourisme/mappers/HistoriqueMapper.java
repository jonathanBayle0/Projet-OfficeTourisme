package com.officetourisme.mappers;

import com.officetourisme.dtos.HistoriqueDto;
import com.officetourisme.entities.Historique;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueMapper {
    @Autowired
    private ModelMapper modelMapper;

    public HistoriqueDto historiqueEntityToDto(Historique historique) {
        return modelMapper.map(historique, HistoriqueDto.class);
    }

    public Historique historiqueDtoToEntity(HistoriqueDto historiqueDto) {
        return modelMapper.map(historiqueDto, Historique.class);
    }
}
