package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseService<User, String> {
    Page<User> data(UserDto dto);

    Page<User> data2(UserDto userDto);

    Page<User> data3(UserDto dto);

    List<User> list(UserDto dto);

    List<User> list2(UserDto dto);

    List<User> list3(UserDto dto);
}
