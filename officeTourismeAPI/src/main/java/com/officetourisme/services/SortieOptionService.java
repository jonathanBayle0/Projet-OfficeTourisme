package com.officetourisme.services;

import com.officetourisme.dtos.SortieOptionDto;

import java.util.List;

public interface SortieOptionService {
    SortieOptionDto saveSortieOption(SortieOptionDto sortieOptionDto);
    SortieOptionDto getSortieOptionById(Long sortieOptionId);
    boolean deleteSortieOption(Long sortieOptionId);
    List<SortieOptionDto> getAllSortieOptions();
}
