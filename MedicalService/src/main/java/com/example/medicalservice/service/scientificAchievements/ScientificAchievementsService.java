package com.example.medicalservice.service.scientificAchievements;

import com.example.medicalservice.dto.scientificAchievements.ScientificAchievementsCreateDto;
import com.example.medicalservice.entity.doctorInformations.scientificAchievements.ScientificAchievementsEntity;
import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.repository.scientificAchievements.ScientificAchievementsRepository;
import com.example.medicalservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScientificAchievementsService {

    private final ScientificAchievementsRepository scientificAchievementsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ScientificAchievementsEntity add(ScientificAchievementsCreateDto scientificAchievementsCreateDto) {
        ScientificAchievementsEntity scientificAchievementsInformation = modelMapper.map(scientificAchievementsCreateDto, ScientificAchievementsEntity.class);
        scientificAchievementsRepository.save(scientificAchievementsInformation);
        return scientificAchievementsInformation;
    }

    public ScientificAchievementsEntity update(ScientificAchievementsCreateDto scientificAchievementsCreateDto, UUID scientificAchievementsInformationId) {
        ScientificAchievementsEntity scientificAchievementsInformationForUpdate = scientificAchievementsRepository.findById(scientificAchievementsInformationId)
                .orElseThrow(() -> new DataNotFoundException("This ScientificAchievements information not found"));


        if (scientificAchievementsCreateDto.getQualification() != null) {
            scientificAchievementsCreateDto.setQualification(scientificAchievementsInformationForUpdate.getQualification());
        }
        if (scientificAchievementsCreateDto.getAcademicDegree() != null) {
            scientificAchievementsCreateDto.setAcademicDegree(scientificAchievementsInformationForUpdate.getAcademicDegree());
        }
        if (scientificAchievementsCreateDto.getRanks() != null) {
            scientificAchievementsCreateDto.setRanks(scientificAchievementsInformationForUpdate.getRanks());
        }
        scientificAchievementsInformationForUpdate.setUpdatedDate(LocalDateTime.now());
        return scientificAchievementsRepository.save(scientificAchievementsInformationForUpdate);
    }

    public void delete(UUID id) {
        scientificAchievementsRepository.deleteById(id);
    }

    public ScientificAchievementsEntity getById(UUID id) {
        return scientificAchievementsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This ScientificAchievements not found"));
    }

    public List<ScientificAchievementsEntity> getUserOrders(int page, int size, UUID scientificAchievementsId) {
        Pageable pageable = PageRequest.of(page, size);
        return scientificAchievementsRepository.findScientificAchievementsEntitiesBy(pageable, scientificAchievementsId);
    }

    public List<ScientificAchievementsEntity> getAll() {
        return scientificAchievementsRepository.findAll();
    }

}
