package com.gruppo3.gestioneComunity.controllers;

import com.gruppo3.gestioneComunity.dto.requests.CreateCommentoRequest;
import com.gruppo3.gestioneComunity.dto.requests.UpdateCommentoRequest;
import com.gruppo3.gestioneComunity.dto.response.EntityIdResponse;
import com.gruppo3.gestioneComunity.dto.response.GenericResponse;
import com.gruppo3.gestioneComunity.entity.Commento;
import com.gruppo3.gestioneComunity.services.CommentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commenti")
public class CommentoController {
    @Autowired
    private CommentoService commentoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Commento> getById(@PathVariable Long id) {
        return new ResponseEntity<>(commentoService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Commento>> getAll() {
        return new ResponseEntity<>(commentoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid CreateCommentoRequest request) {
        return new ResponseEntity<>(commentoService.createCommento(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateCommentoRequest request) {
        return new ResponseEntity<>(commentoService.updateCommento(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        commentoService.deleteCommento(id);
        return new ResponseEntity<>(
                new GenericResponse("Commento con id " + id + " eliminato correttamente"), HttpStatus.OK);
    }
}
