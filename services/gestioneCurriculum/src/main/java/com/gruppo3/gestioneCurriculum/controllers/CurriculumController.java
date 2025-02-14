package com.gruppo3.gestioneCurriculum.controllers;

import com.gruppo3.gestioneCurriculum.dto.EntityRespone;
import com.gruppo3.gestioneCurriculum.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;

    @PostMapping("/{idUtente}")
    public EntityRespone upload(@PathVariable Long idUtente, @RequestParam("cv") MultipartFile cv) {
        return this.curriculumService.upload(idUtente, cv);
    }
}
