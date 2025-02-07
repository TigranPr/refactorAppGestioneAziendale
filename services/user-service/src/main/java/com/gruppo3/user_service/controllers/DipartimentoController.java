package com.gruppo3.user_service.controllers;

import com.gruppo3.user_service.dto.request.DipartimentoRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.Dipartimento;
import com.gruppo3.user_service.services.DipartimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipartimenti")
public class DipartimentoController {
    @Autowired
    private DipartimentoService dipartimentoService;


    @GetMapping("/all")
    public ResponseEntity<List<Dipartimento>> getAll() {
        return ResponseEntity.ok(dipartimentoService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Dipartimento> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dipartimentoService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createDipartimento(@Valid @RequestBody DipartimentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dipartimentoService.createDipartimento(request));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateDipartimento(@PathVariable Long id, @RequestBody DipartimentoRequest request) {
        return ResponseEntity.ok(dipartimentoService.updateDipartimento(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        dipartimentoService.deleteDipartimento(id);
        return ResponseEntity.noContent().build();
    }

}