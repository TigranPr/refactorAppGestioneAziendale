package com.gruppo3.gestioneComunity.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_dipendente", nullable = false)
    private Long idDipendente;

    @ManyToOne
    @JoinColumn(name = "id_news", nullable = false)
    private News news;
}
