package com.tech2java.ems.exception;

import com.tech2java.ems.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    //this is for User Defined Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        log.info("Inside handleResourceNotFoundException");
        Response response=new Response();
        response.setStatusCode("400");
        response.setStatusMessage("Resource NotFound");

        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    //this is for global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handledGlobalExceptions(Exception exception, WebRequest webRequest){
        log.info("Inside handledGlobalExceptions");
        Response response=new Response();
        response.setStatusCode("500");
        response.setStatusMessage(exception.getMessage());

        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

            log.info("Inside handleMethodArgumentNotValid method:: ");
            Map<String,String> validationErrors=new HashMap<>();
            List< ObjectError> list =ex.getBindingResult().getAllErrors();

            list.forEach(error->{
                String fieldName=((FieldError)error).getField();
                String errorValue=error.getDefaultMessage();
                validationErrors.put(fieldName,errorValue);
            });

        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
}
