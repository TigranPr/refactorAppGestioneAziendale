package com.gruppo3.gestioneComunity.repository;

import com.gruppo3.gestioneComunity.entity.Like;
import com.gruppo3.gestioneComunity.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    //verifico se un dipendente ha già messo like a una news
    boolean existsByNewsAndIdDipendente(News news, Long dipendente);

}
