package com.assignment.service;

import com.assignment.dto.LoginDto;
import com.assignment.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * The type Auth service.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Login string.
     *
     * @param loginDto the login dto
     * @return the string
     */
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return jwtService.generateAccessToken(user);
    }
}

