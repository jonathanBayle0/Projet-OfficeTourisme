package com.officetourisme.services;

import com.officetourisme.dtos.SortieDto;

import java.util.List;

public interface SortieService {
    SortieDto saveSortie(SortieDto sortieDto);
    SortieDto getSortieById(Integer sortieId);
    boolean deleteSortie(Integer sortieId);
    List<SortieDto> getAllSorties();
}
