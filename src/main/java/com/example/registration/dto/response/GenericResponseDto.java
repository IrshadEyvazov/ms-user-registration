package com.example.registration.dto.response;

import com.example.registration.constant.ResponseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenericResponseDto<T> {
    final int code;
    final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public static <T> GenericResponseDto ofSuccess(T data) {
        GenericResponseDto genericResponseDto = GenericResponseDto.of(HttpStatus.OK.value(), ResponseMessage.SUCCESS.getMessage());
        genericResponseDto.data = data;
        return genericResponseDto;

    }
}
