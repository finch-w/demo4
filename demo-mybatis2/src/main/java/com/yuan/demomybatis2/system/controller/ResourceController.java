package com.yuan.demomybatis2.system.controller;

import com.yuan.demomybatis2.commons.controller.BaseController;
import com.yuan.demomybatis2.system.dto.SysResourceDto;
import com.yuan.demomybatis2.system.pojo.SysResource;
import com.yuan.demomybatis2.system.service.ResourceService;
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

    @SuppressWarnings("ConstantConditions")
    @RequestMapping("insert")
    @ResponseBody
    public DeferredResult insert(@RequestBody @Valid SysResource resource, BindingResult result) {
        if (result.hasErrors()) {
            return result(result.getFieldError().getDefaultMessage());
        } else if (resourceService.check(resource) > 0) {
            return result("资源已存在");
        } else {
            return result(resourceService.save(resource));
        }
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
        return result(resourceService.selectList(resource));
    }
}
