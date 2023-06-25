package com.example.medicalservice.dto.scientificAchievements;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScientificAchievementsCreateDto {

    private String qualification;

    private String academicDegree;

    private String ranks;
}
