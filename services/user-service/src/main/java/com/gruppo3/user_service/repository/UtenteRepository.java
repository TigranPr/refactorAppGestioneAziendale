package com.gruppo3.user_service.repository;

import com.gruppo3.user_service.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByRegistrationToken(String token);
}
