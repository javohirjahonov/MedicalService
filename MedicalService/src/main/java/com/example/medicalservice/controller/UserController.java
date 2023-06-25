package com.example.medicalservice.controller;

import com.example.medicalservice.dto.request.UserRequestDto;
import com.example.medicalservice.entity.user.UserEntity;
import com.example.medicalservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/add-client")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<UserEntity> addClient(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return ResponseEntity.ok(userService.saveClient(userRequestDto));
    }

    @PutMapping("/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<UserEntity> update(
            @RequestBody UserRequestDto userRequestDto,
            Principal principal
    ){
        return ResponseEntity.ok(userService.update(userRequestDto,principal));
    }
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<String> delete(
            Principal principal
    ){
        userService.delete(principal);
        return ResponseEntity.ok("Successfully deleted");
    }

}
