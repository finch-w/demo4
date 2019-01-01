package com.yuan.demojooqjpa.commons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
public class BaseController {
    private DeferredResult<Object> deferredResult = new DeferredResult<>();
    private ModelAndView modelAndView = new ModelAndView();

    public DeferredResult deferredResult(String view) {
        deferredResult.setResult(view);
        return deferredResult;
    }

    public DeferredResult deferredResult(Object object) {
        deferredResult.setResult(object);
        deferredResult.onCompletion(() -> {
            Object result = deferredResult.getResult();
            log.info(result.toString());
        });
        return deferredResult;
    }

    public DeferredResult deferredResult(String view, Map<String, Object> map) {
        modelAndView.setViewName(view);
        modelAndView.addAllObjects(map);
        deferredResult.setResult(modelAndView);
        return deferredResult;
    }
}
