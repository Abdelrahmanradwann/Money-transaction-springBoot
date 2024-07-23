package com.example.FirstProject.exception.handlingException;

import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.exception.errorResponse.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.validation.beanvalidation.MethodValidationExcludeFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> userArgumentViolation(MethodArgumentNotValidException exception) {
//
//    }
//
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public ResponseEntity<Object> userConstraintViolation(ConstraintViolationException exception) {
//        ErrorResponse error = new ErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}
