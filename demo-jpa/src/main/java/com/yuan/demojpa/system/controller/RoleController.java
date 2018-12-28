package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import com.yuan.demojpa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public DeferredResult index() {
        return deferredResult("role/index");
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public DeferredResult dataPage(RoleDto roleDto) {
        Page<Map<String, Object>> maps = roleService.data(roleDto);
        Map<String, Object> map = new HashMap<>();
        map.put("data", maps.getContent());
        map.put("total", maps.getTotalElements());
        return deferredResult(map);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public DeferredResult dataList(RoleDto roleDto) {
        List<Map<String, Object>> maps = roleService.list(roleDto);
        return deferredResult(maps);
    }

    @RequestMapping(params = "add")
    public DeferredResult add() {
        return deferredResult("role/add");
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public DeferredResult save(@RequestBody Role role) {
        try {
            roleService.insert(role);
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "edit")
    public DeferredResult edit(String id) {
        Optional<Role> role = roleService.get(id);
        return deferredResult("role/edit", Collections.singletonMap("role", role));
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public DeferredResult update(@RequestBody Role role){
        try {
            roleService.update(role);
            return deferredResult("success");
        }catch (Exception e){
            return deferredResult("failure");
        }
    }

    public DeferredResult delete(String id){
        try {
            roleService.delete(id.split(","));
            return deferredResult("success");
        }catch (Exception e){
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public DeferredResult check(Role role){
        return deferredResult(roleService.check(role));
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public DeferredResult get(Role role){
        return deferredResult(roleService.get(role));
    }

}
