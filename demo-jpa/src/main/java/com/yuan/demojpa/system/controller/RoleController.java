package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import com.yuan.demojpa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController {
    private final RoleService roleService;
    private final String baseUrl = "system/role";

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping
    public Object index() {
        return baseUrl + "/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(RoleDto dto) {
        return roleService.data(dto);
    }

    @RequestMapping(params = "data2")
    @ResponseBody
    public Object data2(RoleDto dto) {
        return roleService.data2(dto);
    }

    @RequestMapping(params = "data3")
    @ResponseBody
    public Object data3(RoleDto dto) {
        return roleService.data2(dto);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(RoleDto dto) {
        return roleService.list(dto);
    }

    @RequestMapping(params = "list2")
    @ResponseBody
    public Object list2(RoleDto dto) {
        return roleService.list2(dto);
    }

    @RequestMapping(params = "list3")
    @ResponseBody
    public Object list3(RoleDto dto) {
        return roleService.list3(dto);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return "system/role/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        return new ModelAndView("system/role/edit", Collections.singletonMap("role", roleService.getById(id)));
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public Object check(Role role) {
        return roleService.exist(role);
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public Object save(@RequestBody Role role) {
        try {
            roleService.save(role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "delete")
    @ResponseBody
    public Object delete(String id) {
        try {
            roleService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public Object update(Role role) {
        try {
            roleService.update(role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


