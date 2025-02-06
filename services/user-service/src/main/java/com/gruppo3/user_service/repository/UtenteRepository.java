package com.gruppo3.user_service.repository;

import com.gruppo3.user_service.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
