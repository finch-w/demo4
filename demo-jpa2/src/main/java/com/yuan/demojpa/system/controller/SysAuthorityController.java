package com.yuan.demojpa.system.controller;

import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.SysAuthorityDto;
import com.yuan.demojpa.system.pojo.SysAuthority;
import com.yuan.demojpa.system.service.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@Controller
@RequestMapping("sys/authority")
public class SysAuthorityController extends BaseController {
    private final SysAuthorityService sysAuthorityService;

    @Autowired
    public SysAuthorityController(SysAuthorityService sysAuthorityService) {
        this.sysAuthorityService = sysAuthorityService;
    }

    @RequestMapping
    public DeferredResult index() {
        return result("sys/authority/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectPage(dto));
    }

    @RequestMapping("data2")
    @ResponseBody
    public DeferredResult data2(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectPageSQL(dto));
    }

    @RequestMapping("data3")
    @ResponseBody
    public DeferredResult data3(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectPageJPQL(dto));
    }

    @RequestMapping("data4")
    @ResponseBody
    public DeferredResult data4(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectPageDSL(dto));
    }


    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysAuthority authority) {
        return result(sysAuthorityService.listByExample(authority));
    }

    @RequestMapping("listByDto")
    @ResponseBody
    public DeferredResult listByDto(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectList(dto));
    }

    @RequestMapping("listByDto2")
    @ResponseBody
    public DeferredResult listByDto2(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListSQL(dto));
    }

    @RequestMapping("listByDto3")
    @ResponseBody
    public DeferredResult listByDto3(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListJPQL(dto));
    }

    @RequestMapping("listByDto4")
    @ResponseBody
    public DeferredResult listByDto4(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListDSL(dto));
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysAuthority authority) {
        return result(sysAuthorityService.getByExample(authority));
    }

    @RequestMapping("getByDto")
    @ResponseBody
    public DeferredResult getByDto(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectList(dto));
    }

    @RequestMapping("getByDto2")
    @ResponseBody
    public DeferredResult getByDto2(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListSQL(dto));
    }

    @RequestMapping("getByDto3")
    @ResponseBody
    public DeferredResult getByDto3(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListJPQL(dto));
    }

    @RequestMapping("getByDto4")
    @ResponseBody
    public DeferredResult getByDto4(SysAuthorityDto dto) {
        return result(sysAuthorityService.selectListDSL(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("sys/authority/add");
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    public DeferredResult insert(@RequestBody @Valid SysAuthority authority, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(result.getFieldError().getDefaultMessage()).build());
        } else if (sysAuthorityService.checkInsert(authority)) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(SUCCESS_MESSAGE).build());
        } else {
            sysAuthorityService.insert(authority);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("sys/authority/edit", ImmutableMap.of("authority", sysAuthorityService.getById(id)));
    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(@RequestBody @Valid SysAuthority authority, BindingResult result) {
        if (result.hasErrors()) {
            return result(AjaxResult.builder().status(Status.ERRROR).message(result.getFieldError().getDefaultMessage()).build());
        } else {
            sysAuthorityService.update(authority);
            return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        sysAuthorityService.delete(id.split(","));
        return result(AjaxResult.builder().status(Status.SUUCESS).message(SUCCESS_MESSAGE).build());
    }
}
