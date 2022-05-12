package com.example.registration.service;

import com.example.registration.constant.ResponseMessage;
import com.example.registration.dto.LoginRequestDto;
import com.example.registration.dto.RegistrationRequestDto;
import com.example.registration.dto.response.GenericResponseDto;
import com.example.registration.entity.UserEntity;
import com.example.registration.exception.CustomerAlreadyExistException;
import com.example.registration.exception.NotFoundException;
import com.example.registration.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    ModelMapper modelMapper;
    UserRepository userRepository;
    //PasswordEncoder passwordEncoder;

    @Transactional
    public GenericResponseDto registration(RegistrationRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        if (userEntity != null) {
            throw new CustomerAlreadyExistException(String.format("Customer already exist: username - %s", username));
        }
        userEntity = modelMapper.map(signUpRequestDto, UserEntity.class);
        userRepository.save(userEntity);
        return GenericResponseDto.of(HttpStatus.CREATED.value(), ResponseMessage.SUCCESS.getMessage());
    }

    @Transactional
    public GenericResponseDto login(LoginRequestDto signInRequestDto) {
        String username = signInRequestDto.getUsername();
        String password = signInRequestDto.getPassword();
        UserEntity userEntity = userRepository.findUserEntityByUsernameAndPassword(username, password);
        if (userEntity == null) {
            throw new NotFoundException(String.format("User not found: username - %s", username));
        }
        return GenericResponseDto.of(HttpStatus.OK.value(), ResponseMessage.SUCCESS.getMessage());
    }
}
