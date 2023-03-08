package com.officetourisme.controllers;

import com.officetourisme.dtos.CompteDto;
import com.officetourisme.services.impl.CompteServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    private final CompteServiceImpl compteService;

    public CompteController(CompteServiceImpl compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<CompteDto> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public CompteDto getCompte(@PathVariable Long id) {
        return compteService.getCompteById(id);
    }

    @PostMapping
    public CompteDto saveCompte(final @RequestBody CompteDto compteDto) {
        return compteService.saveCompte(compteDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCompte(@PathVariable Long id) {
        return compteService.deleteCompte(id);
    }
}
