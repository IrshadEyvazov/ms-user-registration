package com.example.registration.service;

import com.example.registration.dto.ChangeUserInfoDto;
import com.example.registration.dto.UserInfoDto;
import com.example.registration.dto.response.GenericResponseDto;
import com.example.registration.entity.UserEntity;
import com.example.registration.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    ModelMapper modelMapper;
    //PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Transactional
    public ChangeUserInfoDto changeUserInfoDto(ChangeUserInfoDto changeUserInfoDto) {
        String username = changeUserInfoDto.getUsername();
        String password = changeUserInfoDto.getPassword();
        UserEntity userEntity = userRepository.findUserEntityByUsernameAndPassword(username,password);
        userEntity.setName(changeUserInfoDto.getName());
        userEntity.setSurname(changeUserInfoDto.getSurname());
        userEntity.setBirthday(changeUserInfoDto.getBirthday());
        userRepository.save(userEntity);
        return changeUserInfoDto;
    }

    public GenericResponseDto<Object> getUserInfo(String username){
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        UserInfoDto userInfoDto = modelMapper.map(userEntity,UserInfoDto.class);
        return GenericResponseDto.ofSuccess(userInfoDto);
    }
}
