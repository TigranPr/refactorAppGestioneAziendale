package com.gruppo3.user_service.controllers;

import com.gruppo3.user_service.dto.request.UpdateUtenteRequest;
import com.gruppo3.user_service.dto.request.UtenteRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.Utente;
import com.gruppo3.user_service.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService dipendenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Utente> getById(@PathVariable Long id) {
        return new ResponseEntity<>(dipendenteService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getAll() {
        return new ResponseEntity<>(dipendenteService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid UtenteRequest request) {
        return new ResponseEntity<>(dipendenteService.createUtente(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody UpdateUtenteRequest request) {
        return new ResponseEntity<>(dipendenteService.updateDipendente(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        dipendenteService.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}