package com.officetourisme.services.impl;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.entities.Commentaire;
import com.officetourisme.mappers.CommentaireMapper;
import com.officetourisme.repositories.CommentaireRepository;
import com.officetourisme.services.CommentaireService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("CommentaireService")
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final CommentaireMapper commentaireMapper;

    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, CommentaireMapper commentaireMapper) {
        this.commentaireRepository = commentaireRepository;
        this.commentaireMapper = commentaireMapper;
    }


    @Override
    public CommentaireDto saveCommentaire(CommentaireDto commentaireDto) {
        Commentaire commentaire = commentaireMapper.commentaireDtoToEntity(commentaireDto);
        commentaireRepository.save(commentaire);
        return commentaireDto;
    }

    @Override
    public CommentaireDto getCommentaireById(String commentaireId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElseThrow(() -> new EntityNotFoundException("Commentaire inexistant"));
        return commentaireMapper.commentaireEntityToDto(commentaire);
    }

    @Override
    public boolean deleteCommentaire(String commentaireId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElseThrow(() -> new EntityNotFoundException("Commentaire inexistant"));
        commentaireRepository.deleteById(commentaireId);
        return true;
    }

    @Override
    public List<CommentaireDto> getAllCommentaires() {
        List<CommentaireDto> commentairesDto = new ArrayList<>();
        List<Commentaire> commentaires = commentaireRepository.findAll();
        // Transformation de l'ensemble de la liste des commentaires en dto
        commentaires.forEach(commentaire -> commentairesDto.add(commentaireMapper.commentaireEntityToDto(commentaire)));
        return commentairesDto;
    }
}
