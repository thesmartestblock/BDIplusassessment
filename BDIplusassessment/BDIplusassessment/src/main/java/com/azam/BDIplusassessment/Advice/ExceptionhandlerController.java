package com.azam.BDIplusassessment.Advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.azam.BDIplusassessment.Exception.NoTaskFoundException;
import com.azam.BDIplusassessment.Exception.TaskNotFoundException;

@RestControllerAdvice
public class ExceptionhandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),error.getDefaultMessage()));
        return errorMap;
    }

    @ExceptionHandler(NoTaskFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> noTaskFoundExceptionHandler(NoTaskFoundException ex){
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("NoTaskFoundException",ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> taskNotFoundExceptionHandler(TaskNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("TaskNotFoundException",ex.getMessage());
        return errorMap;
    }


}
