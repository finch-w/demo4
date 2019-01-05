package com.yuan.demojpa.base.service;

import com.yuan.demojpa.base.dto.BaseCustomerDto;
import com.yuan.demojpa.base.pojo.BaseCustomer;
import com.yuan.demojpa.commons.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseCustomerService extends BaseService<BaseCustomer, String> {
    Page<BaseCustomer> data(BaseCustomerDto dto);

    Page<BaseCustomer> dataJPQL(BaseCustomerDto dto);

    Page<BaseCustomer> dataSQL(BaseCustomerDto dto);

    List<BaseCustomer> list(BaseCustomerDto dto);

    List<BaseCustomer> listJPQL(BaseCustomerDto dto);

    List<BaseCustomer> listSQL(BaseCustomerDto dto);
}
