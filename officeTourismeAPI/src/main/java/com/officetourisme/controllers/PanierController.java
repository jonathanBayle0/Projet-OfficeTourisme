package com.officetourisme.controllers;

import com.officetourisme.dtos.PanierDto;
import com.officetourisme.dtos.SortieDto;
import com.officetourisme.services.impl.PanierServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paniers")
public class PanierController {
    private final PanierServiceImpl panierService;

    public PanierController(PanierServiceImpl panierService) {
        this.panierService = panierService;
    }

    @GetMapping
    public List<PanierDto> getAllPaniers() {
        return panierService.getAllPaniers();
    }

    /**
     * Retourne l'ensemble des sorties du panier d'un compte
     * @param compteId
     * @return
     */
    @GetMapping("/sorties/{compteId}")
    public List<SortieDto> getSortieFromCompte(@PathVariable Long compteId) {
        return panierService.getSortieFromCompte(compteId);
    }

    @GetMapping("/{id}")
    public PanierDto getPanier(@PathVariable Long id) {
        return panierService.getPanierById(id);
    }

    @PostMapping
    public PanierDto savePanier(final @RequestBody PanierDto panierDto) {
        return panierService.savePanier(panierDto);
    }

    /**
     * Transfert des elements du panier vers l'historique
     * @param compteId
     * @return
     */
    @PostMapping("/historique/{compteId}")
    public Boolean transfertPanierToHistorique(@PathVariable Long compteId) {
        return panierService.transfertPanierToHistorique(compteId);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePanier(@PathVariable Long id) {
        return panierService.deletePanier(id);
    }
}
