package com.yuan.demojpa.system.controller;

import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@Controller
@RequestMapping("sys/user")
public class SysUserController extends BaseController {
    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequestMapping
    public DeferredResult index() {
        return result("sys/user/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysUserDto dto) {
        return result(sysUserService.selectPageSQL(dto));
    }

    @RequestMapping("data2")
    @ResponseBody
    public DeferredResult data2(SysUserDto dto) {
        return result(sysUserService.selectPageJPQL(dto));
    }

    @RequestMapping("data3")
    @ResponseBody
    public DeferredResult data3(SysUserDto dto) {
        return result(sysUserService.selectPageDSL(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("sys/user/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("sys/user/edit", ImmutableMap.of("user", sysUserService.getById(id)));
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    @RequestMapping("insert")
    @ResponseBody
    public DeferredResult insert(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(result.getFieldError().getDefaultMessage()).build());
        } else if (sysUserService.checkInsert(user)) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(EXIST_MESSAGE).build());
        } else {
            sysUserService.insert(user);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(EXIST_MESSAGE).build());
        } else {
            sysUserService.update(user);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        sysUserService.delete(id.split(","));
        return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysUser user) {
        return result(sysUserService.getByExample(user));
    }

    @RequestMapping("getByDto")
    @ResponseBody
    public DeferredResult getByDto(SysUserDto dto) {
        return result(sysUserService.selectOneSQL(dto));
    }

    @RequestMapping("getByDto1")
    @ResponseBody
    public DeferredResult getByDto1(SysUserDto dto) {
        return result(sysUserService.selectListJPQL(dto));
    }

    @RequestMapping("getByDto2")
    @ResponseBody
    public DeferredResult getByDto2(SysUserDto dto) {
        return result(sysUserService.selectOneDSL(dto));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysUser user) {
        return result(sysUserService.listByExample(user));
    }

    @RequestMapping("listByDto")
    @ResponseBody
    public DeferredResult listByDto(SysUserDto dto) {
        return result(sysUserService.selectListSQL(dto));
    }


    @RequestMapping("listByDto1")
    @ResponseBody
    public DeferredResult listByDto1(SysUserDto dto) {
        return result(sysUserService.selectListJPQL(dto));
    }

    @RequestMapping("listByDto2")
    @ResponseBody
    public DeferredResult listByDto2(SysUserDto dto) {
        return result(sysUserService.selectListDSL(dto));
    }
}
