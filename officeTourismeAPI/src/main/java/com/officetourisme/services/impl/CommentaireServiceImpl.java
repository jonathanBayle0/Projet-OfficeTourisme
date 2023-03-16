package com.officetourisme.services.impl;

import com.officetourisme.dtos.CommentaireDto;
import com.officetourisme.dtos.CompteDto;
import com.officetourisme.dtos.HistoriqueDto;
import com.officetourisme.dtos.SortieDto;
import com.officetourisme.entities.Commentaire;
import com.officetourisme.mappers.CommentaireMapper;
import com.officetourisme.repositories.CommentaireRepository;
import com.officetourisme.services.CommentaireService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("CommentaireService")
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final CommentaireMapper commentaireMapper;
    private final SortieServiceImpl sortieService;
    private final CompteServiceImpl compteService;

    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, CommentaireMapper commentaireMapper, SortieServiceImpl sortieService, CompteServiceImpl compteService) {
        this.commentaireRepository = commentaireRepository;
        this.commentaireMapper = commentaireMapper;
        this.sortieService = sortieService;
        this.compteService = compteService;
    }


    @Override
    public CommentaireDto saveCommentaire(CommentaireDto commentaireDto) {
        Long compteId = Long.parseLong(commentaireDto.getCompteId());
        Long sortieId = Long.parseLong(commentaireDto.getSortieId());
        // if (! isParticipant(compteId, sortieId)) throw new IllegalArgumentException("L'utilisateur n'a pas effectué l'activité");
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

    public List<CommentaireDto> getAllCommentairesBySortie(Long sortieId) {
        List<CommentaireDto> commentairesDto = this.getAllCommentaires();
        List<CommentaireDto> commentairesSortie = new ArrayList<>();

        for (CommentaireDto comm : commentairesDto) {
            Long sortie = Long.parseLong(comm.getSortieId());
            if (sortie == sortieId) {
                commentairesSortie.add(comm);
            }
        }

        return commentairesSortie;
    }

    /**
     * Verifie si l'utilisateur a participé à l'activité et que l'activité est passée
     * @param compteId
     * @param sortieId
     * @return true si l'utilisateur a effectué l'activité, false sinon
     */
    private boolean isParticipant(Long compteId, Long sortieId) {
        // Verification que l'activité est passée
        Timestamp maintenant = new Timestamp(System.currentTimeMillis());
        SortieDto sortieDto = sortieService.getSortieById(sortieId);
        if (sortieDto.getDateFin().after(maintenant)) return false;

        // Verification de la participation de l'utilisateur
        CompteDto compte = compteService.getCompteById(compteId);
        Set<HistoriqueDto> historiques = compte.getHistorique();
        for (HistoriqueDto his : historiques) {
            if (his.getSortieId() == sortieId) return true;
        }

        return false;
    }
}
