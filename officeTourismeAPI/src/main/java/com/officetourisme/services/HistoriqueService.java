package com.officetourisme.services;

import com.officetourisme.dtos.HistoriqueDto;

import java.util.List;

public interface HistoriqueService {
    HistoriqueDto saveHistorique(HistoriqueDto historiqueDto);
    HistoriqueDto getHistoriqueById(Long historiqueId);
    boolean deleteHistorique(Long historiqueId);
    List<HistoriqueDto> getAllHistoriques();
}
