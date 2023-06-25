package com.example.medicalservice.entity.doctorInformations.workExperience;

import com.example.medicalservice.entity.BaseEntity;
import com.example.medicalservice.entity.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity(name = "doctor_informations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkExperienceEntity extends BaseEntity {

    @Column(nullable = false)
    private String organization;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String job_title;

    @Column(nullable = false)
    private String work_period;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserEntity> userWorkExperience;

}
