package com.yuan.demojpa.base.controller;

import com.yuan.demojpa.base.dto.BaseCustomerDto;
import com.yuan.demojpa.base.pojo.BaseCustomer;
import com.yuan.demojpa.base.service.BaseCustomerService;
import com.yuan.demojpa.commons.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("base/customer")
public class BaseCustomerController extends BaseController {
    @Autowired
    private BaseCustomerService baseCustomerService;

    @RequestMapping
    public Object index() {
        return "base/customer/index";
    }

    @RequestMapping("data")
    public Object data(BaseCustomerDto dto, Integer mode) {
        AjaxResult ajaxResult = new AjaxResult();
        switch (mode) {
            default:
                ajaxResult.setData(baseCustomerService.data(dto));
                break;
            case 1:
                ajaxResult.setData(baseCustomerService.data(dto));
            case 2:
                ajaxResult.setData(baseCustomerService.dataJPQL(dto));
            case 3:
                ajaxResult.setData(baseCustomerService.dataSQL(dto));
        }
        ajaxResult.setStatus(Status.SUUCESS);
        return ajaxResult;
    }

    @RequestMapping("list")
    public Object list(BaseCustomerDto dto, Integer mode) {
        AjaxResult ajaxResult = new AjaxResult();
        switch (mode) {
            default:
                ajaxResult.setData(baseCustomerService.list(dto));
                break;
            case 1:
                ajaxResult.setData(baseCustomerService.list(dto));
            case 2:
                ajaxResult.setData(baseCustomerService.listJPQL(dto));
            case 3:
                ajaxResult.setData(baseCustomerService.listSQL(dto));
        }
        ajaxResult.setStatus(Status.SUUCESS);
        return ajaxResult;
    }

    @RequestMapping("get")
    @ResponseBody
    public Object get(BaseCustomer customer) {
        return new AjaxResult(Status.SUUCESS, baseCustomerService.getByExample(customer));
    }

    @RequestMapping("add")
    public Object add() {
        return "base/customer/add";
    }

    @RequestMapping("edit")
    public Object edit(String id) {
        Map<String, Optional<BaseCustomer>> customer = Collections.singletonMap("customer", baseCustomerService.getById(id));
        return new ModelAndView("base/customer/edit", customer);
    }

    @RequestMapping("check")
    @ResponseBody
    public Object check(BaseCustomer customer) {
        return new AjaxResult(Status.SUUCESS, baseCustomerService.exist(customer));

    }


    @RequestMapping("save")
    @ResponseBody
    public Object save(BaseCustomer customer) {
        baseCustomerService.save(customer);
        return new AjaxResult(Status.SUUCESS, "操作成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id) {
        baseCustomerService.delete(id.split(","));
        return new AjaxResult(Status.SUUCESS, "操作成功");
    }

    public Object update(BaseCustomer baseCustomer) {
        baseCustomerService.update(baseCustomer);
        return new AjaxResult(Status.SUUCESS, "操作成功");
    }
}
