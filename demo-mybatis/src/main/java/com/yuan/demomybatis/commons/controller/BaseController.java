package com.yuan.demomybatis.commons.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class BaseController {

    private DeferredResult<Object> result = new DeferredResult<>();
    private ModelAndView modelAndView = new ModelAndView();

    public DeferredResult result(String view) {
        result.setResult(view);
        return result;
    }

    public DeferredResult result(Object object) {
        result.setResult(object);
        return result;
    }

    public DeferredResult result(String view, Map<String, Object> map) {
        modelAndView.setViewName(view);
        modelAndView.addAllObjects(map);
        result.setResult(modelAndView);
        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DeferredResult handler(Exception e) {
        e.printStackTrace();
        result.setResult(e.getMessage());

        return result;
    }
}
