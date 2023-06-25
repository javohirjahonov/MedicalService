package com.example.medicalservice.controller;

import com.example.medicalservice.dto.workExperience.WorkExperienceCreateDto;
import com.example.medicalservice.entity.doctorInformations.workExperience.WorkExperienceEntity;
import com.example.medicalservice.service.workExperience.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/workExperience")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('DOCTOR')")
    public ResponseEntity<WorkExperienceEntity> addEducationInformation(
            @RequestBody WorkExperienceCreateDto workExperienceCreateDto
    ){
        return ResponseEntity.ok(workExperienceService.add(workExperienceCreateDto));
    }
    @PutMapping("/update")
    @PreAuthorize(value = "hasRole('DOCTOR')")
    public ResponseEntity<String> updateEducationInformation(
            @RequestBody WorkExperienceCreateDto workExperienceCreateDto,
            @RequestParam(value = "id") UUID id
    ){
        workExperienceService.update(workExperienceCreateDto, id);
        return ResponseEntity.ok("Data successfully updated");
    }
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('DOCTOR')")
    public ResponseEntity<String> delete(
            @RequestParam(value = "id") UUID id
    ){
        workExperienceService.delete(id);
        return ResponseEntity.ok("Data successfully deleted");
    }



}
