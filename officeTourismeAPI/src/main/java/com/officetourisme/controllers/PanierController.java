package com.officetourisme.controllers;

import com.officetourisme.dtos.PanierDto;
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

    @GetMapping("/{id}")
    public PanierDto getPanier(@PathVariable Long id) {
        return panierService.getPanierById(id);
    }

    @PostMapping
    public PanierDto savePanier(final @RequestBody PanierDto panierDto) {
        return panierService.savePanier(panierDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePanier(@PathVariable Long id) {
        return panierService.deletePanier(id);
    }
}
