package com.example.medicalservice.entity.doctor;

import com.example.medicalservice.entity.BaseEntity;
import com.example.medicalservice.entity.doctorInformations.education.EducationEntity;
import com.example.medicalservice.entity.doctorInformations.scientificAchievements.ScientificAchievementsEntity;
import com.example.medicalservice.entity.doctorInformations.workExperience.WorkExperienceEntity;
import com.example.medicalservice.entity.user.UserEntity;
import com.example.medicalservice.entity.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoctorEntity extends BaseEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String imoLink;

    @Column(nullable = false)
    private String instagramLink;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserEntity> doctorClients;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<EducationEntity> doctorEducationInformation;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ScientificAchievementsEntity> doctorsScientificAchievements;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<WorkExperienceEntity> doctorsWorkExperience;

}
