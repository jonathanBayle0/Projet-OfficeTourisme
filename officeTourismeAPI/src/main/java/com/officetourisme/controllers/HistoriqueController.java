package com.officetourisme.controllers;

import com.officetourisme.dtos.HistoriqueDto;
import com.officetourisme.dtos.SortieDto;
import com.officetourisme.services.impl.HistoriqueServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historiques")
public class HistoriqueController {
    private final HistoriqueServiceImpl historiqueService;

    public HistoriqueController(HistoriqueServiceImpl historiqueService) {
        this.historiqueService = historiqueService;
    }

    @GetMapping
    public List<HistoriqueDto> getAllHistoriques() {
        return historiqueService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    public HistoriqueDto getHistorique(@PathVariable Long id) {
        return historiqueService.getHistoriqueById(id);
    }

    /**
     * Retourne l'ensemble des sorties de l'historique d'un compte
     * @param compteId
     * @return
     */
    @GetMapping("/sorties/{compteId}")
    public List<SortieDto> getSortieFromCompte(@PathVariable Long compteId) {
        return historiqueService.getSortieFromCompte(compteId);
    }

    @PostMapping
    public HistoriqueDto saveHistorique(final @RequestBody HistoriqueDto historiqueDto) {
        return historiqueService.saveHistorique(historiqueDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteHistorique(@PathVariable Long id) {
        return historiqueService.deleteHistorique(id);
    }
}
