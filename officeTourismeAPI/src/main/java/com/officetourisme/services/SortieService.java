package com.officetourisme.services;

import com.officetourisme.dtos.SortieDto;

import java.util.List;

public interface SortieService {
    SortieDto saveSortie(SortieDto sortieDto);
    SortieDto getSortieById(Long sortieId);
    boolean deleteSortie(Long sortieId);
    List<SortieDto> getAllSorties();
}
