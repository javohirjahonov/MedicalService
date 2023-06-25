package com.example.medicalservice.repository.doctor;

import com.example.medicalservice.entity.doctor.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {
    Optional<DoctorEntity> findDoctorEntityByPhoneNumber(String phoneNumber);

}
