package com.yuan.demomybatis.system.controller;

import com.yuan.demomybatis.commons.controller.BaseController;
import com.yuan.demomybatis.system.dto.SysResourceDto;
import com.yuan.demomybatis.system.pojo.SysResource;
import com.yuan.demomybatis.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;

@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping("index")
    public DeferredResult index() {
        return result("resource/index");
    }

    @RequestMapping("data")
    @ResponseBody
    public DeferredResult data(SysResourceDto dto) {
        return result(resourceService.selectPage(dto));
    }

    @RequestMapping("add")
    public DeferredResult add() {
        return result("resource/add");
    }

    @RequestMapping("edit")
    public DeferredResult edit(String id) {
        return result("resource/edit", Collections.singletonMap("resource", resourceService.get(id)));
    }

    @RequestMapping("save")
    @ResponseBody
    public DeferredResult save(SysResource resource) {
        return result(resourceService.save(resource));
    }

    @RequestMapping("delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        return result(resourceService.delete(id.split(",")));
    }

    @RequestMapping("update")
    @ResponseBody
    public DeferredResult update(SysResource resource) {
        return result(resourceService.update(resource));
    }

    @RequestMapping("get")
    @ResponseBody
    public DeferredResult get(SysResource resource) {
        return result(resourceService.get(resource));
    }

    @RequestMapping("list")
    @ResponseBody
    public DeferredResult list(SysResource resource) {
        return result(resourceService.list(resource));
    }
}
