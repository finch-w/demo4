package com.yuan.demojpa.base.service;

import com.yuan.demojpa.base.dto.BaseCustomerGroupDto;
import com.yuan.demojpa.base.pojo.BaseCustomerGroup;
import com.yuan.demojpa.commons.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseCustomerGroupService extends BaseService<BaseCustomerGroup, String> {
    Page<BaseCustomerGroup> data(BaseCustomerGroupDto dto);

    List<BaseCustomerGroup> list(BaseCustomerGroupDto dto);

    Page<BaseCustomerGroup> data2(BaseCustomerGroupDto dto);

    Page<BaseCustomerGroup> data3(BaseCustomerGroupDto dto);

    List<BaseCustomerGroup> list2(BaseCustomerGroupDto dto);

    List<BaseCustomerGroup> list3(BaseCustomerGroupDto dto);
}
