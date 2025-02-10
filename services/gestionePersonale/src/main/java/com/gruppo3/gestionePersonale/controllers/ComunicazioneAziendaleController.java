package com.gruppo3.gestionePersonale.controllers;

import com.gruppo3.gestionePersonale.dto.ComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.UpdateComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.entity.ComunicazioneAziendale;
import com.gruppo3.gestionePersonale.services.ComunicazioneAziendaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunicazioniAziendali")
public class ComunicazioneAziendaleController {
    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    @PostMapping("/create/{idDipendente}")
    public ResponseEntity<EntityIdResponse> createComunicazioneAziendale(
            @PathVariable Long idDipendente,
            @Valid @RequestBody ComunicazioneAziendaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comunicazioneAziendaleService.createComunicazioneAziendale(idDipendente, request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ComunicazioneAziendale> getById(@PathVariable Long id) {
        return new ResponseEntity<>(comunicazioneAziendaleService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComunicazioneAziendale>> getAll() {
        return new ResponseEntity<>(comunicazioneAziendaleService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateComunicazioneAziendale(
            @PathVariable Long id,
            @Valid @RequestBody UpdateComunicazioneAziendaleRequest response) {
        return new ResponseEntity<>(comunicazioneAziendaleService.updateComunicazioneAziendale(id, response), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        comunicazioneAziendaleService.deleteById(id);
    }
}