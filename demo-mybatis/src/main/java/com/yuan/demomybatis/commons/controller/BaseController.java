package com.yuan.demomybatis.commons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class BaseController {

    private DeferredResult<Object> result = new DeferredResult<>();
    private ModelAndView modelAndView = new ModelAndView();

    protected DeferredResult result(String view) {
        return getDeferredResult(view);
    }

    private DeferredResult getDeferredResult(Object object) {
        result.setResultHandler(result -> {
            log.info(result.toString());
        });
        result.onCompletion(() -> log.info("completion"));
        result.onTimeout(() -> log.info("timeout"));
        result.onError(Throwable::printStackTrace);
        result.setResult(object);
        return result;
    }

    protected DeferredResult result(Object object) {
        return getDeferredResult(object);
    }

    protected DeferredResult result(String view, Map<String, Object> map) {
        modelAndView.setViewName(view);
        modelAndView.addAllObjects(map);
        return getDeferredResult(modelAndView);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DeferredResult handler(Exception e) {
        e.printStackTrace();
        return getDeferredResult(e.getCause() + " " + e.getMessage());
    }
}
