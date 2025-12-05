package com.codingshuttle.springbootJava.advices;

import com.codingshuttle.springbootJava.excepations.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message(exception.getMessage().toString())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternlServerError(Exception exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
       List<String> errors=exception.
               getBindingResult().
               getAllErrors().
               stream().
               map(error->error.getDefaultMessage()).
               collect(Collectors.toList());

        ApiError apiError=ApiError.builder()
                .status(HttpStatus.BAD_GATEWAY)
                .statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message("Input Validation failed")
                .errorLog(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}


