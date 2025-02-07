package com.gruppo3.gestioneComunity.repository;

import com.gruppo3.gestioneComunity.entity.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentoRepository extends JpaRepository<Commento, Long> {
}