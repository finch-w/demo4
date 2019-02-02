package com.yuan.demomybatis2.system.controller;

import com.yuan.demomybatis2.commons.controller.BaseController;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;
import com.yuan.demomybatis2.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("index")
    @ResponseBody
    public DeferredResult index() {
        return result("user/index");
    }

    @RequestMapping("data")
    public DeferredResult data(SysUserDto dto) {
        return result(userService.selectPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("user/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.get(id));
        return result("user/edit", map);
    }

    @RequestMapping("save")
    @ResponseBody
    public DeferredResult save(@RequestBody SysUser user) {
        return result(userService.save(user) > 0);
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        return result(userService.delete(id.split(",")) > 0);
    }

    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(SysUser user) {
        return result(userService.update(user) > 0);
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysUser user) {
        return result(userService.get(user));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysUser user) {
        return result(userService.selectList(user));
    }


}
