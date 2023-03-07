package com.officetourisme.services;

import com.officetourisme.dtos.CommentaireDto;

import java.util.List;

public interface CommentaireService {
    CommentaireDto saveCommentaire(CommentaireDto commentaireDto);
    CommentaireDto getCommentaireById(Integer commentaireId);
    boolean deleteCommentaire(Integer commentaireId);
    List<CommentaireDto> getAllCommentaires();
}
