package com.yuan.demojpa.system.controller;

import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.SysRoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import com.yuan.demojpa.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@Controller
@RequestMapping("sys/role")
public class SysRoleController extends BaseController {
    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @RequestMapping
    public DeferredResult index() {
        return result("sys/role/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysRoleDto dto) {
        return result(sysRoleService.selectPageSQL(dto));
    }

    @RequestMapping("data2")
    @ResponseBody
    public DeferredResult data2(SysRoleDto dto) {
        return result(sysRoleService.selectPageJPQL(dto));
    }

    @RequestMapping("data3")
    @ResponseBody
    public DeferredResult data3(SysRoleDto dto) {
        return result(sysRoleService.selectPageDSL(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("sys/role/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("sys/role/edit", ImmutableMap.of("role", sysRoleService.getById(id)));
    }

    @RequestMapping("insert")
    @ResponseBody
    @SuppressWarnings("ConstantConditions")
    public DeferredResult insert(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(new AjaxResult(Status.ERRROR, result.getFieldError().getDefaultMessage()));
        } else if (sysRoleService.checkInsert(role)) {
            return result(new AjaxResult(Status.ERRROR, "已存在"));
        } else {
            sysRoleService.insert(role);
            return result(new AjaxResult(Status.SUUCESS, "操作成功"));
        }
    }

    @RequestMapping("update")
    @ResponseBody
    @SuppressWarnings("ConstantConditions")
    public DeferredResult update(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(new AjaxResult(Status.ERRROR, result.getFieldError().getDefaultMessage()));
        } else {
            sysRoleService.update(role);
            return result(new AjaxResult(Status.SUUCESS, "操作成功"));
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        sysRoleService.delete(id.split(","));
        return result(new AjaxResult(Status.SUUCESS, "操作成功"));
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysRole role) {
        return result(sysRoleService.getByExample(role));
    }

    @RequestMapping("getByDto")
    @ResponseBody
    public DeferredResult getByDto(SysRoleDto dto) {
        return result(sysRoleService.selectOneSQL(dto));
    }

    @RequestMapping("getByDto2")
    @ResponseBody
    public DeferredResult getByDto2(SysRoleDto dto) {
        return result(sysRoleService.selectOneJPQL(dto));
    }

    @RequestMapping("getByDto3")
    @ResponseBody
    public DeferredResult getByDto3(SysRoleDto dto) {
        return result(sysRoleService.selectOneDSL(dto));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysRole role) {
        return result(sysRoleService.listByExample(role));
    }
}
