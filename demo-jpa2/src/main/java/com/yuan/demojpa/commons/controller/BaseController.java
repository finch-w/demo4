package com.yuan.demojpa.commons.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public final String EXIST_MESSAGE = "已存在";
    public final String SUCCESS_MESSAGE = "操作成功";

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
        modelAndView.setViewName(view);
        modelAndView.addAllObjects(map);
        return getDeferredResult(modelAndView);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DeferredResult handler(Exception e) {
        return result(AjaxResult.builder().status(Status.ERRROR).message(e.getMessage()));

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    protected class AjaxResult {
        private Status status;
        private String message;
        private Object data;
    }

}
