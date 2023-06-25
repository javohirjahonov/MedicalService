package com.example.medicalservice.entity.doctorInformations.education;

import com.example.medicalservice.entity.BaseEntity;
import com.example.medicalservice.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Entity(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationEntity extends BaseEntity {

    @Column(nullable = false)
    private String educationType;

    @Column(nullable = false)
    private String education_institution;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private String additionally;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private Duration periodOfStudy;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserEntity> userEducations;
}
