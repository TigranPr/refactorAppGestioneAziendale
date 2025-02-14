package com.gruppo3.gestioneCurriculum.repository;

import com.gruppo3.gestioneCurriculum.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
