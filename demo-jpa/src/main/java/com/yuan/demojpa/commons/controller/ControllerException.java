package com.yuan.demojpa.commons.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handler(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
