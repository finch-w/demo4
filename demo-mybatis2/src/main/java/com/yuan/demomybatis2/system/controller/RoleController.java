package com.yuan.demomybatis2.system.controller;

import com.yuan.demomybatis2.commons.controller.BaseController;
import com.yuan.demomybatis2.system.dto.SysRoleDto;
import com.yuan.demomybatis2.system.pojo.SysRole;
import com.yuan.demomybatis2.system.service.RoleService;
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

    @SuppressWarnings("ConstantConditions")
    @RequestMapping("insert")
    @ResponseBody
    public DeferredResult insert(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(result.getFieldError().getDefaultMessage());
        } else if (roleService.check(role) > 0) {
            return result("此角色已存在");
        } else {
            return result(roleService.save(role) > 0);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        return result(roleService.delete(id.split(",")) > 0);
    }

    @SuppressWarnings("ConstantConditions")
    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(result.getFieldError().getDefaultMessage());
        } else {
            return result(roleService.update(role) > 0);
        }
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysRole role) {
        return result(roleService.get(role));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysRole role) {
        return result(roleService.selectList(role));
    }
}
