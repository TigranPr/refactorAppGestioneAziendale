package com.gruppo3.user_service.repository;

import com.gruppo3.user_service.entity.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipartimentoRepository extends JpaRepository<Dipartimento, Long> {
}
