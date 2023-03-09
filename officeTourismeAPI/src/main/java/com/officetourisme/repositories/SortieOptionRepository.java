package com.officetourisme.repositories;

import com.officetourisme.entities.SortieOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortieOptionRepository extends JpaRepository<SortieOption, Long> {
}
