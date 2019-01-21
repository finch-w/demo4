package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.LogDao;
import com.yuan.demojpa.system.dto.LogDto;
import com.yuan.demojpa.system.pojo.Log;
import com.yuan.demojpa.system.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log, String, LogDao> implements LogService {
    private LogDao logDao;

    @Override
    public LogDao getBaseRepository() {
        return logDao;
    }

    public Map<String, Object> tableDate(LogDto dto) {
        return null;
    }

    @Override
    public Page<Map> data(LogDto dto) {
        return null;
    }

    @Override
    public List<Map> list(LogDto dto) {
        return null;
    }
}
