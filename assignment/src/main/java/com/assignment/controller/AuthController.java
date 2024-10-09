package com.assignment.controller;

import com.assignment.dto.LoginDto;
import com.assignment.dto.SignUpDto;
import com.assignment.dto.UserDto;
import com.assignment.service.AuthService;
import com.assignment.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    /**
     * Sign up response entity.
     *
     * @param signUpDto the sign up dto
     * @return the response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Login response entity.
     *
     * @param loginDto the login dto
     * @param request  the request
     * @param response the response
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                        HttpServletResponse response) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);

        return ResponseEntity.ok(token);
    }

}

