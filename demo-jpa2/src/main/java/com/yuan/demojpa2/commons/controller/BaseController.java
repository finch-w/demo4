package com.yuan.demojpa2.commons.controller;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class BaseController {
    private DeferredResult<Object> deferredResult = new DeferredResult<>();
    private ModelAndView modelAndView = new ModelAndView();

    public DeferredResult deferredResult(String view) {
        deferredResult.setResult(view);
        return deferredResult;
    }

    public DeferredResult deferredResult(String view, Map<String, Object> model) {
        modelAndView.setViewName(view);
        modelAndView.addAllObjects(model);
        deferredResult.setResult(modelAndView);
        return deferredResult;
    }

    public DeferredResult deferredResult(Object object) {
        deferredResult.setResult(object);
        return deferredResult;
    }
}
