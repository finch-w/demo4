package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public DeferredResult index() {
        return deferredResult("user/index");
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public DeferredResult data(UserDto dto) {
        return deferredResult(userService.data(dto));
    }
}
