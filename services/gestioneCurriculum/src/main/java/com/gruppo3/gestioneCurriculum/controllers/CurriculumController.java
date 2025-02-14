package com.gruppo3.gestioneCurriculum.controllers;

import com.gruppo3.gestioneCurriculum.dto.EntityRespone;
import com.gruppo3.gestioneCurriculum.dto.GenericResponse;
import com.gruppo3.gestioneCurriculum.entity.Curriculum;
import com.gruppo3.gestioneCurriculum.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Curriculum> getById(@PathVariable Long id) {
        return new ResponseEntity<>(curriculumService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Curriculum>> getAll() {
        return new ResponseEntity<>(curriculumService.all(), HttpStatus.OK);
    }

    @PostMapping("/upload/{idUtente}")
    public EntityRespone upload(@PathVariable Long idUtente, @RequestParam("cv") MultipartFile cv) {
        return this.curriculumService.upload(idUtente, cv);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        curriculumService.delete(id);
        return new ResponseEntity<>(
                new GenericResponse("Commento con id " + id + " eliminato correttamente"), HttpStatus.OK);
    }

    @GetMapping("/donwload/{idUtente}")
    public ResponseEntity<Resource> download(@PathVariable Long idUtente) {
        return curriculumService.download(idUtente);
    }
}
