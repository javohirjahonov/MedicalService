package com.example.medicalservice.dto.workExperience;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkExperienceCreateDto {

    private String organization;

    private String address;

    private String job_title;

    private String work_period;
}
