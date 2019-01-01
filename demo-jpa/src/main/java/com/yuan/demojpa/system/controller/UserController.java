package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("system/user")
@Slf4j
public class UserController extends BaseController {
    private final UserService userService;
    private final String baseUrl = "system/user";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public Object index() {
        return baseUrl + "/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(UserDto dto) {
        log.info(dto.toString());
        return userService.data(dto);
    }

    @RequestMapping(params = "data2")
    @ResponseBody
    public Object data2(UserDto dto) {
        log.info(dto.toString());
        return userService.data2(dto);
    }

    @RequestMapping(params = "data3")
    @ResponseBody
    public Object data3(UserDto dto) {
        log.info(dto.toString());
        return userService.data3(dto);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(UserDto dto) {
        log.info(dto.toString());
        return userService.list(dto);
    }

    @RequestMapping(params = "list2")
    @ResponseBody
    public Object list2(UserDto dto) {
        log.info(dto.toString());
        return userService.list2(dto);
    }

    @RequestMapping(params = "list3")
    @ResponseBody
    public Object list3(UserDto dto) {
        return userService.list3(dto);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return baseUrl + "/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        return new ModelAndView(baseUrl + "/edit", Collections.singletonMap("user", userService.getById(id)));
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public Object save(User user) {
        try {
            userService.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "delete")
    public Object delete(String id) {
        try {
            userService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public Object update(User user) {
        try {
            userService.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
