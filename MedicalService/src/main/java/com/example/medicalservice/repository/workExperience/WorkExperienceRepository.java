package com.example.medicalservice.repository.workExperience;

import com.example.medicalservice.entity.doctorInformations.workExperience.WorkExperienceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceEntity, UUID> {

    List<WorkExperienceEntity> findWorkExperienceEntitiesBy(Pageable page, UUID workExperienceId);
}
