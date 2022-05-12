package com.example.registration.controller;

import com.example.registration.dto.LoginRequestDto;
import com.example.registration.dto.RegistrationRequestDto;
import com.example.registration.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return ResponseEntity.ok(authService.registration(registrationRequestDto));
    }

}
