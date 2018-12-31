package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface UserService extends BaseService<User, String> {
    Page<Map> data(UserDto dto);

    Page<User> data2(UserDto userDto);
}
