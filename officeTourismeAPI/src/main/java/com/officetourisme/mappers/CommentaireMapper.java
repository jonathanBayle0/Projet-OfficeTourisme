package com.officetourisme.mappers;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.entities.Commentaire;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CommentaireDto commentaireEntityToDto(Commentaire commentaire) {
        return modelMapper.map(commentaire, CommentaireDto.class);
    }

    public Commentaire commentaireDtoToEntity(CommentaireDto commentaireDto) {
        return modelMapper.map(commentaireDto, Commentaire.class);
    }
}
