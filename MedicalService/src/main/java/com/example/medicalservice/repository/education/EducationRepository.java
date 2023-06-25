package com.example.medicalservice.repository.education;

import com.example.medicalservice.entity.doctorInformations.education.EducationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {

    List<EducationEntity> findEducationEntitiesBy(Pageable page, UUID doctorId);
}
