package com.example.medicalservice.controller;

import com.example.medicalservice.dto.education.EducationCreateDto;
import com.example.medicalservice.entity.doctorInformations.education.EducationEntity;
import com.example.medicalservice.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;
    @PostMapping("/add")
    public ResponseEntity<EducationEntity> addEducationInformation(
            @RequestBody EducationCreateDto educationCreateDto
    ){
        return ResponseEntity.ok(educationService.add(educationCreateDto));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateEducationInformation(
            @RequestBody EducationCreateDto educationCreateDto,
            @RequestParam(value = "id") UUID id
    ){
        educationService.update(educationCreateDto, id);
        return ResponseEntity.ok("Data successfully updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestParam(value = "id") UUID id
    ){
        educationService.delete(id);
        return ResponseEntity.ok("Data successfully deleted");
    }




}
