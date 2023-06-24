package com.example.medicalservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDto {
    @NotBlank(message = "PhoneNumber cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
