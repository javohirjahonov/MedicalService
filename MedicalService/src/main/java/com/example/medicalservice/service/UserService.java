package com.example.medicalservice.service;

import com.example.medicalservice.dto.request.LoginRequestDto;
import com.example.medicalservice.dto.request.UserRequestDto;
import com.example.medicalservice.entity.user.UserEntity;
import com.example.medicalservice.entity.user.UserRole;
import com.example.medicalservice.exception.AuthenticationFailedException;
import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.exception.UniqueObjectException;
import com.example.medicalservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class    UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserEntity save(UserRequestDto userRequestDto){
        UserEntity userEntity = modelMapper.map(userRequestDto, UserEntity.class);
        if(userRepository.findUserEntityByPhoneNumber(userEntity.getPhoneNumber()).isEmpty()){
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(UserRole.USER);
            return userRepository.save(userEntity);
        }
        throw new UniqueObjectException("username or phone number already exists");
    }
    public JwtResponse signIn(LoginRequestDto loginRequestDto){
        UserEntity userEntity = userRepository.findUserEntityByPhoneNumber(loginRequestDto.getPhoneNumber())
                .orElseThrow(() -> new DataNotFoundException("Incorrect username or password"));
        if(passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword())){
            String accessToken = jwtService.generateAccessToken(userEntity);
            String refreshToken = jwtService.generateRefreshToken(userEntity);
            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new AuthenticationFailedException("Incorrect username or password");
    }

    public UserEntity update(UserRequestDto userRequestDto, Principal principal){
        UserEntity userEntity = userRepository.findUserEntityByPhoneNumber(principal.getName())
                .orElseThrow(()-> new AuthenticationFailedException("Your access has expired"));
        if(!userEntity.getPhoneNumber().equals(userRequestDto.getPhoneNumber())){
            Optional<UserEntity> userEntityByUsername = userRepository.findUserEntityByPhoneNumber(userRequestDto.getPhoneNumber());
            if(userEntityByUsername.isPresent()){
                throw new UniqueObjectException("Username already exists");
            }
        }
        if(userRequestDto.getPhoneNumber() != null){
            userEntity.setPhoneNumber(userRequestDto.getPhoneNumber());
        }
        if(userRequestDto.getPassword() != null){
            userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        if(userRequestDto.getFullName() != null){
            userEntity.setFullName(userRequestDto.getFullName());
        }
        userEntity.setUpdatedDate(LocalDateTime.now());
        return userRepository.save(userEntity);
    }
    public void delete(Principal principal){
        UserEntity userEntity = userRepository.findUserEntityByPhoneNumber(principal.getName())
                .orElseThrow(() -> new AuthenticationFailedException("Your access has expired"));
        userRepository.delete(userEntity);
    }
    public JwtResponse getNewAccessToken(Principal principal) {
        UserEntity userEntity = userRepository.findUserEntityByPhoneNumber(principal.getName())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        String accessToken = jwtService.generateAccessToken(userEntity);
        return JwtResponse.builder().accessToken(accessToken).build();
    }

}
