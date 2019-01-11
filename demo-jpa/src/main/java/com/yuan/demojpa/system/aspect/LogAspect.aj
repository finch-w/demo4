package com.yuan.demojpa.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
@Slf4j
public aspect LogAspect {

    @Pointcut(value = " execution(public * com.yuan.demojpa.*.controller.*.*(..))")
    public void log() {
    }

    @Before(value = "log()", argNames = "joinPoint")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("URL=" + request.getRequestURI());
        log.info("METHOD=" + request.getMethod());
        log.info("ARGS=" + request.getAttributeNames().toString());
        System.out.println(request.getRequestURI());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {

    }
}
