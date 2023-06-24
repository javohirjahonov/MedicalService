package com.example.medicalservice.service.auth;

import com.example.medicalservice.exception.DataNotFoundException;
import com.example.medicalservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findUserEntityByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException("User not found")
                );
    }
}
