package com.yuan.demojpa2.commons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class BaseController {
    private DeferredResult<Object> deferredResult = new DeferredResult<Object>();
    private ModelAndView modelAndView = new ModelAndView();

    @SuppressWarnings("Duplicates")
    public DeferredResult getDeferredResult(Object object) {
        deferredResult.onError(Throwable::printStackTrace);
        deferredResult.onTimeout(() -> log.info("timeout"));
        deferredResult.onCompletion(() -> log.info("completion"));
        deferredResult.setResultHandler(result -> log.info(result.toString()));
        deferredResult.setResult(object);
        deferredResult.setErrorResult("错误");
        return deferredResult;
    }

    public DeferredResult result(Object object) {
        return getDeferredResult(object);
    }

    public DeferredResult result(String view) {
        return getDeferredResult(view);
    }

    public DeferredResult result(String view, Map<String, Object> map) {
        return getDeferredResult(new ModelAndView(view, map));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DeferredResult handler(Exception e) {
        return result(new AjaxResult(Status.ERRROR, e.getMessage()));

    }

    protected enum Status {
        SUUCESS("success"), ERRROR("error");
        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    protected class AjaxResult {
        private Status status;
        private String message;
        private Object data;

        public AjaxResult() {
        }

        public AjaxResult(Status status) {
            this.status = status;
        }

        public AjaxResult(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public AjaxResult(Status status, Object data) {
            this.status = status;
            this.data = data;
        }

        public AjaxResult(Status status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public AjaxResult invoker() {
            this.data = data;
            this.status = status;
            this.message = message;
            return this;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

}
