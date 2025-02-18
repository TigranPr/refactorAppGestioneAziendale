package com.gruppo3.gestionePersonale.repository;

import com.gruppo3.gestionePersonale.entity.Timbratura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimbraturaRepository extends JpaRepository<Timbratura, Long> {
    @Query("SELECT t FROM Timbratura t WHERE t.ingresso BETWEEN :startOfDay AND :endOfDay")
    List<Timbratura> findTimbratureByDateRange(@Param("startOfDay") LocalDateTime startOfDay,
                                               @Param("endOfDay") LocalDateTime endOfDay);
}
