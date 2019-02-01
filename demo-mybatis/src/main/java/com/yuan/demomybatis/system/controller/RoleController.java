package com.yuan.demomybatis.system.controller;

import com.yuan.demomybatis.commons.controller.BaseController;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;
import com.yuan.demomybatis.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("index")
    public DeferredResult index() {
        return result("role/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysRoleDto dto) {
        return result(roleService.selectPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("role/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("role/edit", Collections.singletonMap("role", roleService.get(id)));
    }

    @RequestMapping("save")
    @ResponseBody
    public DeferredResult save(SysRole role) {
        return result(roleService.save(role) > 0);
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        return result(roleService.delete(id.split(",")) > 0);
    }

    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(SysRole role) {
        return result(roleService.update(role) > 0);
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysRole role) {
        return result(roleService.get(role));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysRole role) {
        return result(roleService.list(role));
    }
}
