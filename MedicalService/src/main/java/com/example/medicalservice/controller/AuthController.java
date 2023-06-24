package com.example.medicalservice.controller;

import com.example.medicalservice.dto.reponse.JwtResponse;
import com.example.medicalservice.dto.request.LoginRequestDto;
import com.example.medicalservice.dto.request.UserRequestDto;
import com.example.medicalservice.entity.user.UserEntity;
import com.example.medicalservice.exception.RequestValidationException;
import com.example.medicalservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-Up")
    public ResponseEntity<UserEntity> signUp(
            @Valid @RequestBody UserRequestDto userDto,
            BindingResult bindingResult
    ) throws RequestValidationException {
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/sign-In")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody LoginRequestDto loginDto
    ){
        return ResponseEntity.ok(userService.signIn(loginDto));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshAccessToken(
            Principal principal
    ){
        return ResponseEntity.ok(userService.getNewAccessToken(principal));
    }

}
