package com.akademi11.library.exception;

import com.akademi11.library.common.BaseResponse;
import com.akademi11.library.exception.custom.EmailAlreadyTaken;
import com.akademi11.library.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<BaseResponse> emailAlreadyTaken(EmailAlreadyTaken e) {
        BaseResponse response = new BaseResponse("1001 ", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> resourceNotFound(ResourceNotFoundException e) {
        BaseResponse response = new BaseResponse("404", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

}
