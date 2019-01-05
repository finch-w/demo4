package com.yuan.demotest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
@Aspect
public class BaseController {
    private DeferredResult<Object> deferredResult = new DeferredResult<>();

    public DeferredResult deferredResult(Object object) {
        deferredResult.setResult(object);
        return deferredResult;
    }

    public DeferredResult deferredResult(String view) {
        deferredResult.setResult(view);
        return deferredResult;
    }

    public DeferredResult deferredResult(String view, Map<String, Object> map) {
        ModelAndViewContainer container = new ModelAndViewContainer();
        container.setViewName(view);
        container.addAllAttributes(map);
        deferredResult.setResult(container);
        return deferredResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DeferredResult<Object> handler(Exception e) {
        deferredResult.setResult(e.getMessage());

        return deferredResult;
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aspect() {
    }

    @Before(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void before(JoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        log.info("before" + name);
    }
}
