package com.yuan.demojpa.system.controller;

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
import java.util.Collections;

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
        return result(sysUserService.selectPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("sys/user/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("sys/user/edit", Collections.singletonMap("user", sysUserService.getById(id)));
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    @RequestMapping("insert")
    @ResponseBody
    public DeferredResult insert(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(new AjaxResult(Status.ERRROR, result.getFieldError().getDefaultMessage()));
        } else if (sysUserService.checkInsert(user)) {
            return result(new AjaxResult(Status.ERRROR, "用户已存在"));
        } else {
            sysUserService.insert(user);
            return result(new AjaxResult(Status.SUUCESS, "操作成功"));
        }
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(@RequestBody @Valid SysUser user, BindingResult result) {
        if (result.hasErrors()) {
            return result(new AjaxResult(Status.ERRROR, result.getFieldError().getDefaultMessage()));
        } else {
            sysUserService.update(user);
            return result(new AjaxResult(Status.SUUCESS, "操作成功"));
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        sysUserService.delete(id.split(","));
        return result(new AjaxResult(Status.SUUCESS, "操作成功"));
    }
}
