package com.example.medicalservice.service.workExperience;

import com.example.medicalservice.dto.workExperience.WorkExperienceCreateDto;
import com.example.medicalservice.entity.doctorInformations.workExperience.WorkExperienceEntity;
import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.repository.user.UserRepository;
import com.example.medicalservice.repository.workExperience.WorkExperienceRepository;
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
public class WorkExperienceService {
    private final WorkExperienceRepository workExperienceRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WorkExperienceEntity add(WorkExperienceCreateDto workExperienceCreateDto) {
        WorkExperienceEntity workExperienceInformation = modelMapper.map(workExperienceCreateDto, WorkExperienceEntity.class);
        workExperienceRepository.save(workExperienceInformation);
        return workExperienceInformation;
    }

    public WorkExperienceEntity update(WorkExperienceCreateDto workExperienceCreateDto, UUID workExperienceId) {
        WorkExperienceEntity workExperienceInformationForUpdate = workExperienceRepository.findById(workExperienceId)
                .orElseThrow(() -> new DataNotFoundException("This WorkExperience information not found"));


        if (workExperienceCreateDto.getOrganization() != null) {
            workExperienceCreateDto.setOrganization(workExperienceInformationForUpdate.getOrganization());
        }
        if (workExperienceCreateDto.getJob_title() != null) {
            workExperienceCreateDto.setJob_title(workExperienceInformationForUpdate.getJob_title());
        }
        if (workExperienceCreateDto.getAddress() != null) {
            workExperienceCreateDto.setAddress(workExperienceInformationForUpdate.getAddress());
        }
        workExperienceInformationForUpdate.setUpdatedDate(LocalDateTime.now());
        return workExperienceRepository.save(workExperienceInformationForUpdate);
    }

    public void delete(UUID id) {
        workExperienceRepository.deleteById(id);
    }

    public WorkExperienceEntity getById(UUID id) {
        return workExperienceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This WorkExperience not found"));
    }

    public List<WorkExperienceEntity> getUserOrders(int page, int size, UUID workExperienceId) {
        Pageable pageable = PageRequest.of(page, size);
        return workExperienceRepository.findWorkExperienceEntitiesBy(pageable, workExperienceId);
    }

    public List<WorkExperienceEntity> getAll() {
        return workExperienceRepository.findAll();
    }

}
