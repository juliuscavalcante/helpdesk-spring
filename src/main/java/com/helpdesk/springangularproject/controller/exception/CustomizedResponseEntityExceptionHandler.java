package com.helpdesk.springangularproject.controller.exception;

import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler({ ObjectNotFoundException.class })
    public ResponseEntity<StandardError> standardErrorResponseEntity (ObjectNotFoundException objectNotFoundException,
                                                                      HttpServletRequest httpServletRequest) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object not found", objectNotFoundException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



}
