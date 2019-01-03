package com.yuan.demojpa.base.controller;

import com.yuan.demojpa.base.dto.BaseCustomerGroupDto;
import com.yuan.demojpa.base.pojo.BaseCustomerGroup;
import com.yuan.demojpa.base.service.BaseCustomerGroupService;
import com.yuan.demojpa.commons.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("base/cusustomer/group")
public class BaseCustomerGroupController extends BaseController {
    @Autowired
    private BaseCustomerGroupService baseCustomerGroupService;

    @RequestMapping
    public Object index() {
        return "base/cusustomer/group/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(BaseCustomerGroupDto dto, Integer mode) {
        switch (mode) {
            case 1:
                return baseCustomerGroupService.data(dto);
            case 2:
                return baseCustomerGroupService.data2(dto);
            case 3:
                return baseCustomerGroupService.data3(dto);
            default:
                return baseCustomerGroupService.data(dto);
        }
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(BaseCustomerGroupDto dto, Integer mode) {
        switch (mode) {
            case 1:
                return baseCustomerGroupService.list(dto);
            case 2:
                return baseCustomerGroupService.list2(dto);
            case 3:
                return baseCustomerGroupService.list3(dto);
            default:
                return baseCustomerGroupService.list(dto);
        }
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public Object get(BaseCustomerGroup dto) {
        return baseCustomerGroupService.getByExample(dto);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return "base/cusustomer/group/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        return "base/cusustomer/group/edit";
    }

    @RequestMapping(params = "exsist")
    @ResponseBody
    public Object check(BaseCustomerGroup dto) {
        return baseCustomerGroupService.exist(dto);
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public Object save(BaseCustomerGroup group) {
        try {
            baseCustomerGroupService.save(group);
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
            baseCustomerGroupService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public Object update(BaseCustomerGroup group) {
        try {
            baseCustomerGroupService.update(group);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
