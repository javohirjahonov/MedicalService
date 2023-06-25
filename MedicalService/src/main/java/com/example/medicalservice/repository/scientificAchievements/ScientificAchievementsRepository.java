package com.example.medicalservice.repository.scientificAchievements;

import com.example.medicalservice.entity.doctorInformations.scientificAchievements.ScientificAchievementsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScientificAchievementsRepository extends JpaRepository<ScientificAchievementsEntity, UUID> {
    List<ScientificAchievementsEntity> findScientificAchievementsEntitiesBy(Pageable page, UUID scientificAchievementsId);
}
