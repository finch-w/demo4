package com.yuan.demojooqjpa.system.controller;

import com.yuan.demojooqjpa.commons.controller.BaseController;
import com.yuan.demojooqjpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(params = "system/user")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
