package com.example.medicalservice.service.education;

import com.example.medicalservice.dto.education.EducationCreateDto;
import com.example.medicalservice.entity.doctorInformations.education.EducationEntity;
import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.repository.education.EducationRepository;
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
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public EducationEntity add(EducationCreateDto educationCreateDto) {
        EducationEntity educationInformation = modelMapper.map(educationCreateDto, EducationEntity.class);
        educationRepository.save(educationInformation);
        return educationInformation;
    }

    public EducationEntity update(EducationCreateDto educationCreateDto, UUID educationInformationId) {
        EducationEntity educationInformation = educationRepository.findById(educationInformationId)
                .orElseThrow(() -> new DataNotFoundException("This education information not found"));


        if (educationCreateDto.getEducationType() != null) {
            educationCreateDto.setEducationType(educationInformation.getEducationType());
        }
        if (educationCreateDto.getEducation_institution() != null) {
            educationCreateDto.setEducation_institution(educationInformation.getEducation_institution());
        }
        if (educationCreateDto.getAdditionally() != null) {
            educationCreateDto.setAdditionally(educationInformation.getAdditionally());
        }
        if (educationCreateDto.getSpecialization() != null) {
            educationCreateDto.setSpecialization(educationInformation.getSpecialization());
        }
        if (educationCreateDto.getFaculty() != null) {
            educationCreateDto.setFaculty(educationInformation.getFaculty());
        }
        if (educationCreateDto.getAdditionally() != null) {
            educationCreateDto.setAdditionally(educationInformation.getAdditionally());
        }
        if (educationCreateDto.getPeriodOfStudy() != null) {
            educationCreateDto.setPeriodOfStudy(educationInformation.getPeriodOfStudy());
        }
        educationInformation.setUpdatedDate(LocalDateTime.now());
        return educationRepository.save(educationInformation);
    }

    public void delete(UUID id) {
        educationRepository.deleteById(id);
    }

    public EducationEntity getById(UUID id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This educationInformation not found"));
    }

    public List<EducationEntity> getUserOrders(int page, int size, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return educationRepository.findEducationEntitiesBy(pageable, userId);
    }

    public List<EducationEntity> getAll() {
        return educationRepository.findAll();
    }


}
