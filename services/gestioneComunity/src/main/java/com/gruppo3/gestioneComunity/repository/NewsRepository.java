package com.gruppo3.gestioneComunity.repository;

import com.gruppo3.gestioneComunity.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}