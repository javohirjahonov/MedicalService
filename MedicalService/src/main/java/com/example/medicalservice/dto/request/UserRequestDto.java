package com.example.medicalservice.dto.request;

import com.example.medicalservice.entity.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDto {
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;
    @NotBlank(message = "Username cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,}$",
            message = "password should contain at least 1 big and 1 small letter 1 symbol and 1 digit and length more than 8"
    )
    private String password;

    private LocalDate birthDate;

    private String country;

    private String city;

    private String imoLink;

    private String instagramLink;

}
