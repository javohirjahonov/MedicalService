package com.example.medicalservice.entity.doctorInformations.scientificAchievements;

import com.example.medicalservice.entity.BaseEntity;
import com.example.medicalservice.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity(name = "scientific_achievements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScientificAchievementsEntity extends BaseEntity {

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private String academicDegree;

    @Column(nullable = false)
    private String ranks;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserEntity> userScientificAchievements;
}
