package com.star.reactivewebflux.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleOtherException(Throwable e) {
        log.info("system current error info={}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

}
