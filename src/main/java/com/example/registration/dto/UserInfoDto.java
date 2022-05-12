package com.example.registration.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDto {
    String name;
    String surname;
    String username;
    String email;
    LocalDate birthday;
}
