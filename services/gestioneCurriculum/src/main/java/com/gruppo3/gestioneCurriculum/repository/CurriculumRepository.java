package com.gruppo3.gestioneCurriculum.repository;

import com.gruppo3.gestioneCurriculum.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Optional<Curriculum> findByIdUtente(Long idUtente);
}
