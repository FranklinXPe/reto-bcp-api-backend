package com.bcp.tipocambio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class TipoCambioExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TipoCambioNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(Exception exception,WebRequest request){

        GeneralExceptionResponse response = new GeneralExceptionResponse( exception.getMessage(),
                                                                        request.getDescription(false),
                                                                        HttpStatus.NOT_FOUND,
                                                                        LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
