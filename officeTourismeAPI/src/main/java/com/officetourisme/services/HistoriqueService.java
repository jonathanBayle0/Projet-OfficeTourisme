package com.officetourisme.services;

import com.officetourisme.dtos.HistoriqueDto;

import java.util.List;

public interface HistoriqueService {
    HistoriqueDto saveHistorique(HistoriqueDto historiqueDto);
    HistoriqueDto getHistoriqueById(Integer historiqueId);
    boolean deleteHistorique(Integer historiqueId);
    List<HistoriqueDto> getAllHistoriques();
}
