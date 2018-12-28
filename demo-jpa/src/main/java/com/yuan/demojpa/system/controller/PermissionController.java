package com.yuan.demojpa.system.controller;

import com.yuan.demojpa.commons.controller.BaseController;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import com.yuan.demojpa.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public DeferredResult index() {
        return deferredResult("permission/index");
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public DeferredResult dataPage(PermissionDto permissionDto) {
        Page<Map<String, Object>> data = permissionService.data(permissionDto);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data.getContent());
        map.put("total", data.getTotalElements());
        return deferredResult(map);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public DeferredResult dataList(PermissionDto permissionDto) {
        List<Map<String, Object>> list = permissionService.list(permissionDto);
        return deferredResult(list);
    }

    @RequestMapping(params = "add")
    public DeferredResult add() {
        return deferredResult("permission/add");
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public DeferredResult save(@RequestBody Permission permission) {
        try {
            permissionService.insert(permission);
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "edit")
    public DeferredResult edit(String id) {
        return deferredResult("permission/edit", Collections.singletonMap("permission", permissionService.get(id)));
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public DeferredResult update(@RequestBody Permission permission) {
        try {
            permissionService.update(permission);
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public DeferredResult check(Permission permission) {
        return deferredResult(permissionService.check(permission));
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public DeferredResult get(Permission permission) {
        return deferredResult(permissionService.get(permission));
    }

    @RequestMapping(params = "delete")
    @ResponseBody
    public DeferredResult delete(String id) {
        try {
            permissionService.delete(id.split(","));
            return deferredResult("success");
        } catch (Exception e) {
            return deferredResult("failure");
        }
    }
}
