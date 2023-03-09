package com.officetourisme.repositories;

import com.officetourisme.entities.ChoixOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoixOptionRepository extends JpaRepository<ChoixOption, Long> {
}
