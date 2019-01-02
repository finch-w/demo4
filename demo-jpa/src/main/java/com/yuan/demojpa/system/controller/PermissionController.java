package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import com.yuan.demojpa.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("system/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public Object index() {
        return "system/permission/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(PermissionDto dto) {
        return permissionService.data(dto);
    }

    @RequestMapping(params = "data2")
    @ResponseBody
    public Object data2(PermissionDto dto) {
        return permissionService.data2(dto);
    }

    @RequestMapping(params = "data3")
    @ResponseBody
    public Object data3(PermissionDto dto) {
        return permissionService.data3(dto);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(PermissionDto dto) {
        return permissionService.list(dto);
    }

    @RequestMapping(params = "list2")
    @ResponseBody
    public Object list2(PermissionDto dto) {
        return permissionService.list2(dto);
    }

    @RequestMapping(params = "list3")
    @ResponseBody
    public Object list3(PermissionDto dto) {
        return permissionService.list3(dto);
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public Object get(Permission permission) {
        return permissionService.getByExample(permission);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return "system/permission/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        return new ModelAndView("system/permission/edit", Collections.singletonMap("permission", permissionService.getById(id)));
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public Object check(Permission permission) {
        return permissionService.exist(permission);
    }

    @RequestMapping(params = "save")
    public Object save(@RequestBody Permission permission) {
        try {
            permissionService.save(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "delete")
    public Object delete(String id) {
        try {
            permissionService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(params = "update")
    public Object update(Permission permission) {
        try {
            permissionService.update(permission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
