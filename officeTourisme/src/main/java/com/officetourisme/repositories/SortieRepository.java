package com.officetourisme.repositories;


import com.officetourisme.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortieRepository extends JpaRepository<Sortie, Integer> {
}
