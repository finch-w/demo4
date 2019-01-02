package com.yuan.demojpa.base.service;

import com.yuan.demojpa.base.dto.BaseCustomerTypeDto;
import com.yuan.demojpa.base.pojo.BaseCustomerType;
import com.yuan.demojpa.commons.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseCustomerTypeService extends BaseService<BaseCustomerType, String> {
    Page<BaseCustomerType> data(BaseCustomerTypeDto dto);

    Page<BaseCustomerType> data2(BaseCustomerTypeDto dto);

    List<BaseCustomerType> list2(BaseCustomerTypeDto dto);

    Page<BaseCustomerType> data3(BaseCustomerTypeDto dto);

    List<BaseCustomerType> list(BaseCustomerTypeDto dto);

    List<BaseCustomerType> list3(BaseCustomerTypeDto dto);
}
