package com.gruppo3.gestionePersonale.controllers;

import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.TimbraturaSchedulataRequest;
import com.gruppo3.gestionePersonale.services.TimbraturaSchedulataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Timbratura-schedulata")
public class TimbraturaSchedulataController {
    @Autowired
    TimbraturaSchedulataService timbraturaSchedulataService;

    @PostMapping
    public ResponseEntity<EntityIdResponse> createTimbraturaSchedulata(TimbraturaSchedulataRequest request) throws Exception{
        return new ResponseEntity<>(timbraturaSchedulataService.createTimbraturaScheduled(request), HttpStatus.CREATED);
    }
}
