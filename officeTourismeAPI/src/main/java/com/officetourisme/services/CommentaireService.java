package com.officetourisme.services;

import com.officetourisme.dtos.CommentaireDto;

import java.util.List;

public interface CommentaireService {
    CommentaireDto saveCommentaire(CommentaireDto commentaireDto);
    CommentaireDto getCommentaireById(String commentaireId);
    boolean deleteCommentaire(String commentaireId);
    List<CommentaireDto> getAllCommentaires();
}
