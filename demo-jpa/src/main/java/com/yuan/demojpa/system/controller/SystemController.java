package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
    @Autowired
    private SystemService systemService;

    @RequestMapping("login")
    public Object login() {
        return "system/login";
    }

    @RequestMapping("doLogin")
    public Object doLogin() {
        return "system/main";
    }

    @RequestMapping("main")
    public Object main() {
        return "system/main";
    }

    @RequestMapping("head")
    public Object head() {
        return "system/head";
    }

    @RequestMapping("error")
    public Object error() {
        return "error";
    }
}
