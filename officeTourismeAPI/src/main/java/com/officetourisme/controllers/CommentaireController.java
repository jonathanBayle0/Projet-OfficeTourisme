package com.officetourisme.controllers;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.services.impl.CommentaireServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {
    private final CommentaireServiceImpl commentaireService;

    public CommentaireController(CommentaireServiceImpl commentaireService) {
        this.commentaireService = commentaireService;
    }

    @GetMapping
    public List<CommentaireDto> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentaire(@PathVariable String id) {
        CommentaireDto commentaireDto;
        try {
            commentaireDto = commentaireService.getCommentaireById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Erreur : Message inexistant", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentaireDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCommentaire(final @RequestBody CommentaireDto commentaireDto) {
         try {
             commentaireService.saveCommentaire(commentaireDto);
         } catch (IllegalArgumentException e) {
             return new ResponseEntity<>("Erreur : L'utilisateur doit avoir participé à l'activité pour commenter", HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(commentaireDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCommentaire(@PathVariable String id) {
        return commentaireService.deleteCommentaire(id);
    }
}
