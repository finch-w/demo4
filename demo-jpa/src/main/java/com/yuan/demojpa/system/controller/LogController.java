package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.LogDto;
import com.yuan.demojpa.system.pojo.Log;
import com.yuan.demojpa.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("log")
public class LogController extends BaseController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping
    public Object index() {
        return "log/index";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(LogDto dto) {
        return logService.data(dto);
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(LogDto dto) {
        return logService.list(dto);
    }

    @RequestMapping("get")
    @ResponseBody
    public Object get(Log log) {
        return logService.getByExample(log);
    }

    @RequestMapping("add")
    public Object add() {
        return "log/add";
    }

    @RequestMapping("edit")
    public Object edit(String id) {
        return new ModelAndView("log/edit", Collections.singletonMap("log", logService.getById(id)));
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(@RequestBody Log log) {
        try {
            logService.save(log);
            return new AjaxResult(Status.SUUCESS, "操作成功");
        } catch (Exception e) {
            return new AjaxResult(Status.ERRROR, "操作失败");
        }
    }

    @RequestMapping("check")
    @ResponseBody
    public Object check(Log log) {
        return logService.exist(log);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id) {
        try {
            logService.delete(id.split(","));
            return new AjaxResult(Status.SUUCESS, "操作成功");
        } catch (Exception e) {
            return new AjaxResult(Status.ERRROR, "操作失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(Log log) {
        try {
            logService.update(log);
            return new AjaxResult(Status.SUUCESS, "操作成功");
        } catch (Exception e) {
            return new AjaxResult(Status.ERRROR, e.getMessage());
        }
    }
}
