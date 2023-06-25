package com.example.medicalservice.controller;

import com.example.medicalservice.dto.scientificAchievements.ScientificAchievementsCreateDto;
import com.example.medicalservice.entity.doctorInformations.scientificAchievements.ScientificAchievementsEntity;
import com.example.medicalservice.service.scientificAchievements.ScientificAchievementsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/scientificAchievements")
@RequiredArgsConstructor
public class scientificAchievementsController {

    private final ScientificAchievementsService scientificAchievementsService;
    private final ModelMapper modelMapper;
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<ScientificAchievementsEntity> addScientificAchievements (
            @RequestBody ScientificAchievementsCreateDto scientificAchievementsCreateDto
    ){
        return ResponseEntity.ok(scientificAchievementsService.add(scientificAchievementsCreateDto));
    }
    @PutMapping("/update")
    @PreAuthorize(value = "hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<String> updateScientificAchievements (
            @RequestBody ScientificAchievementsCreateDto scientificAchievementsCreateDto,
            @RequestParam(value = "id") UUID id
    ){
        scientificAchievementsService.update(scientificAchievementsCreateDto, id);
        return ResponseEntity.ok("Data successfully updated");
    }
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<String> delete(
            @RequestParam(value = "id") UUID id
    ){
        scientificAchievementsService.delete(id);
        return ResponseEntity.ok("Data successfully deleted");
    }


}
