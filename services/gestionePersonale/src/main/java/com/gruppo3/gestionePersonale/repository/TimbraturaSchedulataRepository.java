package com.gruppo3.gestionePersonale.repository;

import com.gruppo3.gestionePersonale.entity.TimbraturaSchedulata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimbraturaSchedulataRepository extends JpaRepository<TimbraturaSchedulata, Long> {
}
