package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.LogDto;
import com.yuan.demojpa.system.pojo.Log;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LogService extends BaseService<Log, String> {
    Page<Map> data(LogDto dto);

    List<Map> list(LogDto dto);
}
