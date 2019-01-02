package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.ResourceDto;
import com.yuan.demojpa.system.pojo.Resource;
import com.yuan.demojpa.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("system/resource")
public class ResourceController extends BaseController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping
    public Object index() {
        return "system/resource/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(ResourceDto dto) {
        return resourceService.data(dto);
    }

    @RequestMapping(params = "data2")
    @ResponseBody
    public Object date2(ResourceDto dto) {
        return resourceService.data2(dto);
    }

    @RequestMapping(params = "data3")
    @ResponseBody
    public Object data3(ResourceDto dto) {
        return resourceService.data3(dto);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(ResourceDto dto) {
        return resourceService.list(dto);
    }

    @RequestMapping(params = "list2")
    @ResponseBody
    public Object list2(ResourceDto dto) {
        return resourceService.list2(dto);
    }

    @RequestMapping(params = "list3")
    @ResponseBody
    public Object list3(ResourceDto dto) {
        return resourceService.list3(dto);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return "system/resource/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        Map<String, Optional<Resource>> resource = Collections.singletonMap("resource", resourceService.getById(id));
        return new ModelAndView("system/resource/edit", resource);
    }


    @RequestMapping(params = "check")
    @ResponseBody
    public Object check(Resource resource) {
        return resourceService.exist(resource);
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public Object save(@RequestBody Resource resource) {
        try {
            resourceService.save(resource);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "delete")
    @ResponseBody
    public Object delete(String id) {
        try {
            resourceService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public Object update(Resource resource) {
        try {
            resourceService.update(resource);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
