package com.modive.llm.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ModiveExceptionHandler {

    @ExceptionHandler(ModiveException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(ModiveException e){
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
}