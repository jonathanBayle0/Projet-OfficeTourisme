package com.officetourisme.mappers;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.entities.Commentaire;
import org.modelmapper.ModelMapper;

public class CommentaireMapper {
    private ModelMapper modelMapper;

    public CommentaireDto commentaireEntityToDto(Commentaire commentaire) {
        return modelMapper.map(commentaire, CommentaireDto.class);
    }

    public Commentaire commentaireDtoToEntity(CommentaireDto commentaireDto) {
        return modelMapper.map(commentaireDto, Commentaire.class);
    }
}
