package com.example.registration.controller;


import com.example.registration.dto.ChangeUserInfoDto;
import com.example.registration.dto.UserInfoDto;
import com.example.registration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUserInfo(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.getUserInfo(username));
    }

    @PutMapping
    public ResponseEntity<Object> editUserInfo(@RequestBody ChangeUserInfoDto request) {
        return ResponseEntity.ok(userService.changeUserInfoDto(request));
    }

}
