package com.sm.shopmore.exception;

import com.cloudinary.Api;
import com.sm.shopmore.dto.response.auth.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> invalid(InvalidCredentialsException e){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }
}
