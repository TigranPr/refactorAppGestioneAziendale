package com.gruppo3.user_service.repository;

import com.gruppo3.user_service.entity.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
}
