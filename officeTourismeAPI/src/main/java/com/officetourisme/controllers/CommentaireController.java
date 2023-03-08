package com.officetourisme.controllers;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.services.impl.CommentaireServiceImpl;
import org.springframework.web.bind.annotation.*;

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
    public CommentaireDto getCommentaire(@PathVariable Long id) {
        return commentaireService.getCommentaireById(id);
    }

    @PostMapping
    public CommentaireDto saveCommentaire(final @RequestBody CommentaireDto commentaireDto) {
        return commentaireService.saveCommentaire(commentaireDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCommentaire(@PathVariable Long id) {
        return commentaireService.deleteCommentaire(id);
    }
}
