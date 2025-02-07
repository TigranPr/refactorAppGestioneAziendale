package com.gruppo3.gestionePersonale.repository;

import com.gruppo3.gestionePersonale.entity.Timbratura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimbraturaRepository extends JpaRepository<Timbratura, Long> {
}
