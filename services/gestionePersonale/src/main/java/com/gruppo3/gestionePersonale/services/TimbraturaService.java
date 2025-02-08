package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.entity.Timbratura;
import com.gruppo3.gestionePersonale.repository.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbraturaService {
    @Autowired
    private TimbraturaRepository timbraturaRepository;

    public Timbratura getById(Long id) {
        return timbraturaRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("la timbratura con id " + id + " non esiste"));
    }

    public List<Timbratura> findAll() {
        return timbraturaRepository.findAll();
    }
}
