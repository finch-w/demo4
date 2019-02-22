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
        return result(sysRoleService.selectPage(dto));
    }

    @RequestMapping("data2")
    @ResponseBody
    public DeferredResult data2(SysRoleDto dto) {
        return result(sysRoleService.selectPageSQL(dto));
    }

    @RequestMapping("data3")
    @ResponseBody
    public DeferredResult data3(SysRoleDto dto) {
        return result(sysRoleService.selectPageJPQL(dto));
    }

    @RequestMapping("data4")
    @ResponseBody
    public DeferredResult data4(SysRoleDto dto) {
        return result(sysRoleService.selectPageDSL(dto));
    }


    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysRole role) {
        return result(sysRoleService.getByExample(role));
    }

    @RequestMapping("getByDto")
    @ResponseBody
    public DeferredResult getByDto(SysRoleDto dto) {
        return result(sysRoleService.selectOne(dto));
    }

    @RequestMapping("getByDto2")
    @ResponseBody
    public DeferredResult getByDto2(SysRoleDto dto) {
        return result(sysRoleService.selectOneSQL(dto));
    }

    @RequestMapping("getByDto3")
    @ResponseBody
    public DeferredResult getByDto3(SysRoleDto dto) {
        return result(sysRoleService.selectOneJPQL(dto));
    }

    @RequestMapping("getByDto4")
    @ResponseBody
    public DeferredResult getByDto4(SysRoleDto dto) {
        return result(sysRoleService.selectOneDSL(dto));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysRole role) {
        return result(sysRoleService.listByExample(role));
    }

    @RequestMapping("listByDto")
    @ResponseBody
    public DeferredResult listByDto(SysRoleDto dto) {
        return result(sysRoleService.selectList(dto));
    }

    @RequestMapping("listByDto2")
    @ResponseBody
    public DeferredResult listByDto2(SysRoleDto dto) {
        return result(sysRoleService.selectListSQL(dto));
    }

    @RequestMapping("listByDto3")
    @ResponseBody
    public DeferredResult listByDto3(SysRoleDto dto) {
        return result(sysRoleService.selectListJPQL(dto));
    }

    @RequestMapping("listByDto4")
    @ResponseBody
    public DeferredResult listByDto4(SysRoleDto dto) {
        return result(sysRoleService.selectListDSL(dto));
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
    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    public DeferredResult insert(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(result.getFieldError().getDefaultMessage()).build());
        } else if (sysRoleService.checkInsert(role)) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(EXIST_MESSAGE).build());
        } else {
            sysRoleService.insert(role);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @RequestMapping("update")
    @ResponseBody
    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    public DeferredResult update(@RequestBody @Valid SysRole role, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(result.getFieldError().getDefaultMessage()).build());
        } else {
            sysRoleService.update(role);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        sysRoleService.delete(id.split(","));
        return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
    }


}
