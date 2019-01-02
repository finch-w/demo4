package com.yuan.demojpa.base.controller;

import com.yuan.demojpa.base.dto.BaseCustomerTypeDto;
import com.yuan.demojpa.base.pojo.BaseCustomerType;
import com.yuan.demojpa.base.service.BaseCustomerTypeService;
import com.yuan.demojpa.commons.controller.BaseController;
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
@RequestMapping("base/customer/type")
public class BaseCustomerTypeController extends BaseController {
    @Autowired
    private BaseCustomerTypeService baseCustomerTypeService;

    @RequestMapping
    public Object index() {
        return "base/customer/type/index";
    }

    @RequestMapping(params = "data")
    @ResponseBody
    public Object data(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.data(dto);
    }

    @RequestMapping(params = "data2")
    @ResponseBody
    public Object data2(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.data2(dto);
    }

    @RequestMapping(params = "data3")
    @ResponseBody
    public Object data3(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.data3(dto);
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public Object list(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.list(dto);
    }

    @RequestMapping(params = "list2")
    @ResponseBody
    public Object list2(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.list2(dto);
    }

    @RequestMapping(params = "list3")
    @ResponseBody
    public Object list3(BaseCustomerTypeDto dto) {
        return baseCustomerTypeService.list3(dto);
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public Object get(BaseCustomerType baseCustomerType) {
        return baseCustomerTypeService.getByExample(baseCustomerType);
    }

    @RequestMapping(params = "add")
    public Object add() {
        return "base/customer/type/add";
    }

    @RequestMapping(params = "edit")
    public Object edit(String id) {
        Map<String, Optional<BaseCustomerType>> type = Collections.singletonMap("type", baseCustomerTypeService.getById(id));
        return new ModelAndView("base/customer/type/edit", type);
    }

    @RequestMapping(params = "check")
    @ResponseBody
    public Object check(BaseCustomerType baseCustomerType) {
        return baseCustomerTypeService.exist(baseCustomerType);
    }

    @RequestMapping(params = "save")
    @ResponseBody
    public Object save(@RequestBody BaseCustomerType baseCustomerType) {
        try {
            baseCustomerTypeService.save(baseCustomerType);
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
            baseCustomerTypeService.delete(id.split(","));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(params = "update")
    @ResponseBody
    public Object update(BaseCustomerType baseCustomerType) {
        try {
            baseCustomerTypeService.update(baseCustomerType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
