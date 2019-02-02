package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

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

    public DeferredResult data(RoleDto dto) {
        return result(roleService.selectPage(dto));
    }
}
