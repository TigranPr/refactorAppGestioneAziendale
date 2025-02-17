package com.gruppo3.gestionePersonale.repository;

import com.gruppo3.gestionePersonale.entity.ComunicazioneAziendale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicazioneAziendaleRepository extends JpaRepository<ComunicazioneAziendale, Long> {
}
