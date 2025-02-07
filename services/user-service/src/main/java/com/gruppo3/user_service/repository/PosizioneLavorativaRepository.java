package com.gruppo3.user_service.repository;

import com.gruppo3.user_service.entity.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa, Long> {
}
