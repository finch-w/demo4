package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping("login")
    public Object login() {
        return "system/login";
    }

    @RequestMapping("main")
    public Object main() {
        return "system/main";
    }

    @RequestMapping("logout")
    public Object logout() {
        return "system/login";
    }
}
