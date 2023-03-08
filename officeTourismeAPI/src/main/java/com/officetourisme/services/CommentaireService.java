package com.officetourisme.services;

import com.officetourisme.dtos.CommentaireDto;

import java.util.List;

public interface CommentaireService {
    CommentaireDto saveCommentaire(CommentaireDto commentaireDto);
    CommentaireDto getCommentaireById(Long commentaireId);
    boolean deleteCommentaire(Long commentaireId);
    List<CommentaireDto> getAllCommentaires();
}
