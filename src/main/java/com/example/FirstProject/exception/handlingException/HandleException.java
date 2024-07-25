package com.example.FirstProject.exception.handlingException;

import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.exception.errorResponse.ErrorResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class HandleException {
    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<Object> userAlreadyExists(CustomerAlreadyExistsException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> userNotFound(CustomerNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> authenticationExceptionHandling (AuthenticationException exception, WebRequest request){
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(),HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
//    {
//        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
//    }

}
