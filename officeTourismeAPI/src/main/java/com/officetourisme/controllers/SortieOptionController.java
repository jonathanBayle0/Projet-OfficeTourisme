package com.officetourisme.controllers;

import com.officetourisme.dtos.SortieOptionDto;
import com.officetourisme.services.impl.SortieOptionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sortieOptions")
public class SortieOptionController {
    private final SortieOptionServiceImpl sortieOptionService;

    public SortieOptionController(SortieOptionServiceImpl sortieOptionService) {
        this.sortieOptionService = sortieOptionService;
    }

    @GetMapping
    public List<SortieOptionDto> getAllSortieOptions() {
        return sortieOptionService.getAllSortieOptions();
    }



    @GetMapping("/{id}")
    public SortieOptionDto getSortieOption(@PathVariable Long id) {
        return sortieOptionService.getSortieOptionById(id);
    }

    @PostMapping
    public SortieOptionDto saveSortieOption(final @RequestBody SortieOptionDto sortieOptionDto) {
        return sortieOptionService.saveSortieOption(sortieOptionDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSortieOption(@PathVariable Long id) {
        return sortieOptionService.deleteSortieOption(id);
    }
    @DeleteMapping("/sortie")
    public Boolean deleteSortieOption(final @RequestBody SortieOptionDto sortieOptionDto) {
        return sortieOptionService.deleteSortieOption(sortieOptionDto);
    }
}
