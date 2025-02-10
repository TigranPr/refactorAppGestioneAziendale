package com.gruppo3.gestionePersonale.controllers;

import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.TimbraturaRequest;
import com.gruppo3.gestionePersonale.entity.Timbratura;
import com.gruppo3.gestionePersonale.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestionePersonale.services.TimbraturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timbrature")
public class TimbraturaController {
    @Autowired
    private TimbraturaService timbraturaService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Timbratura> getTimbraturaById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(timbraturaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Timbratura>> getAllTimbrature() {
        return new ResponseEntity<>(timbraturaService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createTimbratura(@RequestBody TimbraturaRequest request) {
        return new ResponseEntity<>(timbraturaService.createTimbratura(request), HttpStatus.CREATED);
    }

    @PostMapping("/update/{idTimbratura}")
    public ResponseEntity<Timbratura> updateTimbratura(@PathVariable Long idTimbratura, @RequestBody TimbraturaRequest request) {
        return new ResponseEntity<>(timbraturaService.updateTimbratura(idTimbratura, request), HttpStatus.CREATED);
    }
}
