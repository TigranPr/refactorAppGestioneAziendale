package com.gruppo3.user_service.controllers;

import com.gruppo3.user_service.dto.request.PosizioneLavorativaRequest;
import com.gruppo3.user_service.dto.request.UpdatePosizioneLavorativaRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.PosizioneLavorativa;
import com.gruppo3.user_service.services.PosizioneLavorativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posizionelavorativa")
public class PosizioneLavorativaController {
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PosizioneLavorativa> getById(@PathVariable Long id) {
        return new ResponseEntity<>(posizioneLavorativaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PosizioneLavorativa>> getAll() {
        return new ResponseEntity<>(posizioneLavorativaService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid PosizioneLavorativaRequest request) {
        return new ResponseEntity<>(posizioneLavorativaService.createPosizione(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody UpdatePosizioneLavorativaRequest request) {
        return new ResponseEntity<>(posizioneLavorativaService.updatePosizione(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        posizioneLavorativaService.deletePosizione(id);
        return ResponseEntity.noContent().build();
    }
}