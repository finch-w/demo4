package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("index")
    public DeferredResult index() {
        return result("user/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(UserDto dto) {
        return result(userService.findPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("user/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("user/edit", Collections.singletonMap("user", userService.getById(id)));
    }

    @SuppressWarnings("ConstantConditions")
    @RequestMapping("insert")
    @ResponseBody
    public DeferredResult insert(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(result.getFieldError().getDefaultMessage());
        } else if (userService.check(user) > 0) {
            return result("用户已存在");
        } else
            userService.insert(user);
        return result(true);
    }

    @SuppressWarnings("ConstantConditions")
    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(result.getFieldError().getDefaultMessage());
        } else {
            userService.update(user);
            return result(true);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        userService.delete(id.split(","));
        return result(true);
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysUser user) {
        return result(userService.getByExample(user));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysUser user) {
        return result(userService.findByExample(user));
    }
}
