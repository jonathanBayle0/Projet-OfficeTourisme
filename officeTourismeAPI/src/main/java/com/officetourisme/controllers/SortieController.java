package com.officetourisme.controllers;

import com.officetourisme.dtos.SortieDto;
import com.officetourisme.services.impl.SortieServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorties")
public class SortieController {
    private final SortieServiceImpl sortieService;

    public SortieController(SortieServiceImpl sortieService) {
        this.sortieService = sortieService;
    }

    @GetMapping
    public List<SortieDto> getAllSorties() {
        return sortieService.getAllSorties();
    }

    @GetMapping("/{id}")
    public SortieDto getSortie(@PathVariable Long id) {
        return sortieService.getSortieById(id);
    }

    @PostMapping
    public SortieDto saveSortie(final @RequestBody SortieDto sortieDto) {
        return sortieService.saveSortie(sortieDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSortie(@PathVariable Long id) {
        return sortieService.deleteSortie(id);
    }
}
