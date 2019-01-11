package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController extends BaseController {
    @RequestMapping
    @ResponseBody
    public String index() {
        return "text";
    }
}
