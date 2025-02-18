package com.gruppo3.gestionePersonale.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record TimbraturaSchedulataRequest(
         LocalDateTime ingresso,
         LocalDateTime uscita,
         LocalDateTime inizioPausaPranzo,
         LocalDateTime finePausa,
         Long dipendenteId
) {
}
