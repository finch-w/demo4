package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.commons.utils.BeanUtils;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public DeferredResult index() {
        return deferredResult("user/index");
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public DeferredResult dataPage(UserDto userDto) {
        Page<Map<String, Object>> users = userService.page(userDto);
        Map<String, Object> map = new HashMap<>();
        map.put("data", users.getContent());
        map.put("total", users.getTotalElements());
        return deferredResult(map);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public DeferredResult dataList(UserDto userDto) {
        List<Map<String, Object>> users = userService.list(userDto);
        return deferredResult(users);
    }

    @RequestMapping(params = "add")
    public DeferredResult save() {
        return deferredResult("user/save");
    }

    @RequestMapping(params = "edit")
    public DeferredResult edit(String id) {
        Optional<User> user = userService.get(id);
        return deferredResult("user/edit", Collections.singletonMap("user", user));
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public DeferredResult insert(User user) {
        try {
            userService.insert(user);
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public DeferredResult saveEdit(@RequestBody User user) {
        try {
            userService.update(user, BeanUtils.IgnoreMode.NULL);
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        try {
            userService.delete(id.split(","));
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public DeferredResult check(User user) {
        Long size = userService.check(user);
        return deferredResult(size);
    }
}
