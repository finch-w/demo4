package com.yuan.demojpa.commons.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
//@RestControllerAdvice
public class BaseController {
//    private DeferredResult<Object> deferredResult = new DeferredResult<>();
//    private ModelAndView modelAndView = new ModelAndView();
//
//    public DeferredResult deferredResult(String view) {
//        deferredResult.setResult(view);
//        return deferredResult;
//    }
//
//    public DeferredResult deferredResult(Object object) {
//        deferredResult.setResult(object);
//        deferredResult.onCompletion(() -> {
//            Object result = deferredResult.getResult();
//            log.info(result.toString());
//        });
//        return deferredResult;
//    }
//
//    public DeferredResult deferredResult(String view, Map<String, Object> map) {
//        modelAndView.setViewName(view);
//        modelAndView.addAllObjects(map);
//        deferredResult.setResult(modelAndView);
//        return deferredResult;
//    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public DeferredResult handler(Exception e){
//        e.printStackTrace();
//        return deferredResult(e.getMessage());
//    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult handler(Exception e) {
        return new AjaxResult(Status.ERRROR, e.getMessage());

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
