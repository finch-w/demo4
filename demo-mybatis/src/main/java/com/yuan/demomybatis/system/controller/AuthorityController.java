package com.yuan.demomybatis.system.controller;

import com.yuan.demomybatis.commons.controller.BaseController;
import com.yuan.demomybatis.system.dto.SysAuthorityDto;
import com.yuan.demomybatis.system.pojo.SysAuthority;
import com.yuan.demomybatis.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;

@Controller
@RequestMapping("authority")
public class AuthorityController extends BaseController {
    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping("index")
    public DeferredResult index() {
        return result("authority/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysAuthorityDto dto) {
        return result(authorityService.selectPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("authority/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("authority/edit", Collections.singletonMap("authority", authorityService.get(id)));
    }

    @RequestMapping("save")
    @ResponseBody
    public DeferredResult save(SysAuthority authority) {
        return result(authorityService.save(authority) > 0);
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        return result(authorityService.delete(id.split(",")) > 0);
    }

    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(SysAuthority authority) {
        return result(authorityService.update(authority) > 0);
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysAuthority authority) {
        return result(authorityService.get(authority));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysAuthority authority) {
        return result(authorityService.list(authority));
    }
}
