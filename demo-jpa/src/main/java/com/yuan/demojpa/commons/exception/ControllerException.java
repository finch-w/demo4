package com.yuan.demojpa.commons.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerException extends Exception {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handler(Exception e) {
        return e.getMessage();
    }
}
