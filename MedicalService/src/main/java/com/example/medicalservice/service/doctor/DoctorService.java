package com.example.medicalservice.service.doctor;

import com.example.medicalservice.dto.reponse.JwtResponse;
import com.example.medicalservice.dto.request.LoginRequestDto;
import com.example.medicalservice.dto.request.UserRequestDto;
import com.example.medicalservice.entity.doctor.DoctorEntity;
import com.example.medicalservice.entity.user.UserEntity;
import com.example.medicalservice.entity.user.UserRole;
import com.example.medicalservice.exception.AuthenticationFailedException;
import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.exception.UniqueObjectException;
import com.example.medicalservice.repository.doctor.DoctorRepository;
import com.example.medicalservice.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final ModelMapper modelMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public DoctorEntity saveDoctor(UserRequestDto userRequestDto){
        DoctorEntity doctorEntity = modelMapper.map(userRequestDto, DoctorEntity.class);
        if(doctorRepository.findDoctorEntityByPhoneNumber(doctorEntity.getPhoneNumber()).isEmpty()){
            doctorEntity.setPassword(passwordEncoder.encode(doctorEntity.getPassword()));
            doctorEntity.setRole(UserRole.DOCTOR);
            return doctorRepository.save(doctorEntity);
        }
        throw new UniqueObjectException("UserName or phone number already exists");
    }

    public JwtResponse signIn(LoginRequestDto loginRequestDto){
        UserEntity userEntity = doctorRepository.findDoctorEntityByPhoneNumber(loginRequestDto.getPhoneNumber())
                .orElseThrow(() -> new DataNotFoundException("Incorrect phoneNumber or password"));
        if(passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword())){
            String accessToken = jwtService.generateAccessToken(userEntity);
            String refreshToken = jwtService.generateRefreshToken(userEntity);
            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new AuthenticationFailedException("Incorrect phoneNumber or password");
    }

    public DoctorEntity update(UserRequestDto userRequestDto, Principal principal){
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByPhoneNumber(principal.getName())
                .orElseThrow(()-> new AuthenticationFailedException("Your access has expired"));
        if(!doctorEntity.getPhoneNumber().equals(userRequestDto.getPhoneNumber())){
            Optional<DoctorEntity> doctorEntityByPhoneNumber = doctorRepository.findDoctorEntityByPhoneNumber(userRequestDto.getPhoneNumber());
            if(doctorEntityByPhoneNumber.isPresent()){
                throw new UniqueObjectException("PhoneNumber already exists");
            }
        }
        if(userRequestDto.getPhoneNumber() != null){
            doctorEntity.setPhoneNumber(userRequestDto.getPhoneNumber());
        }
        if(userRequestDto.getPassword() != null){
            doctorEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        if(userRequestDto.getFullName() != null){
            doctorEntity.setFullName(userRequestDto.getFullName());
        }
        if(userRequestDto.getCity() != null){
            doctorEntity.setPhoneNumber(userRequestDto.getPhoneNumber());
        }
        if(userRequestDto.getCountry() != null){
            doctorEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        if(userRequestDto.getBirthDate() != null){
            doctorEntity.setFullName(userRequestDto.getFullName());
        }
        if(userRequestDto.getImoLink() != null){
            doctorEntity.setPhoneNumber(userRequestDto.getPhoneNumber());
        }
        if(userRequestDto.getInstagramLink() != null){
            doctorEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        doctorEntity.setUpdatedDate(LocalDateTime.now());
        return doctorRepository.save(doctorEntity);
    }

    public void delete(Principal principal){
        DoctorEntity userEntity = doctorRepository.findDoctorEntityByPhoneNumber(principal.getName())
                .orElseThrow(() -> new AuthenticationFailedException("Your access has expired"));
        doctorRepository.delete(userEntity);
    }

    public JwtResponse getNewAccessToken(Principal principal) {
        DoctorEntity userEntity = doctorRepository.findDoctorEntityByPhoneNumber(principal.getName())
                .orElseThrow(() -> new DataNotFoundException("doctor not found"));
        String accessToken = jwtService.generateAccessToken(userEntity);
        return JwtResponse.builder().accessToken(accessToken).build();
    }
}
