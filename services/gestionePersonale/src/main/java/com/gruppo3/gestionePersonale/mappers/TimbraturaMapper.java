package com.gruppo3.gestionePersonale.mappers;

import com.gruppo3.gestionePersonale.dto.TimbraturaRequest;
import com.gruppo3.gestionePersonale.entity.Timbratura;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {

    public Timbratura fromTimbraturaRequest(TimbraturaRequest request) {
        if (request.ingresso() == null && request.inizioPausa() == null && request.finePausa() == null && request.uscita() == null)
            System.out.println("Ma che richiesta mi stai facendo?");

        return Timbratura.builder()
                .ingresso(LocalDateTime.now())
                .uscita(request.uscita())
                .inizioPausaPranzo(request.inizioPausa())
                .finePausa(request.finePausa())
                .dipendenteId(request.idDipendente())
                .build();
    }
}
