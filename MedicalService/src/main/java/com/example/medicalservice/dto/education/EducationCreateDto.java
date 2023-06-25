package com.example.medicalservice.dto.education;

import jakarta.persistence.Column;
import lombok.*;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationCreateDto {

    private String educationType;

    private String education_institution;

    private String faculty;

    private String additionally;

    private String specialization;

    private Duration periodOfStudy;
}
