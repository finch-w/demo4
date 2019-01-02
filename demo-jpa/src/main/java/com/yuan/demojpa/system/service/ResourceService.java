package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.ResourceDto;
import com.yuan.demojpa.system.pojo.Resource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResourceService extends BaseService<Resource, String> {
    Page<Resource> data(ResourceDto dto);

    Page<Resource> data2(ResourceDto dto);

    Page<Resource> data3(ResourceDto dto);

    List<Resource> list(ResourceDto dto);

    List<Resource> list2(ResourceDto dto);

    List<Resource> list3(ResourceDto dto);
}
