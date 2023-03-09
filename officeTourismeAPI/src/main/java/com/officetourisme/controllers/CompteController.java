package com.officetourisme.controllers;

import com.officetourisme.dtos.CompteDto;
import com.officetourisme.services.impl.CompteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<?> getCompte(@PathVariable Long id) {
        CompteDto compteDto;
        try {
            compteDto = compteService.getCompteById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Erreur : Compte inexistant", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(compteDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCompte(final @RequestBody CompteDto compteDto) {
        try {
            compteService.saveCompte(compteDto);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("Erreur : le mail est déjà associé à un utilisateur", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(compteDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCompte(@PathVariable Long id) {
        return compteService.deleteCompte(id);
    }
}
