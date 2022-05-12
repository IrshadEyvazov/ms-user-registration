package com.example.registration.exception;

import com.example.registration.constant.ErrorMessage;
import com.example.registration.constant.ResponseMessage;
import com.example.registration.dto.response.ErrorResponse;
import com.example.registration.dto.response.GenericResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleInternalServerError(NotFoundException ex, WebRequest webRequest){
        log.error("InternalServerError - {}",ex);
        ErrorResponse errorResponse = generateErrorResponse(ErrorMessage.NOT_FOUND);
        final var code = HttpStatus.NOT_FOUND.value();
        final var message = ResponseMessage.FAIL.getMessage();
        GenericResponseDto<Object> genericResponseDto = GenericResponseDto.of(code,message,errorResponse);
        return handleExceptionInternal(ex,genericResponseDto,null,HttpStatus.NOT_FOUND,webRequest);
    }


    @ExceptionHandler(CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCustomerAlreadyException(CustomerAlreadyExistException ex, WebRequest webRequest){
        log.error("CustomerAlreadyExistException - {}",ex);
        ErrorResponse errorResponse = generateErrorResponse(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        final var code = HttpStatus.BAD_REQUEST.value();
        final var message = ResponseMessage.FAIL.getMessage();
        GenericResponseDto<Object> genericResponseDto = GenericResponseDto.of(code,message,errorResponse);
        return handleExceptionInternal(ex,genericResponseDto,null,HttpStatus.BAD_REQUEST,webRequest);
    }


    private ErrorResponse generateErrorResponse(ErrorMessage errorMessage){
       return ErrorResponse.of(errorMessage.getCode(),errorMessage.getMessage());
    }
}
